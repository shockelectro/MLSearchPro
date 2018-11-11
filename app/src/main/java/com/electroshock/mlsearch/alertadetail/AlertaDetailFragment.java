package com.electroshock.mlsearch.alertadetail;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.electroshock.mlsearch.R;
import com.electroshock.mlsearch.addeditalerta.AddEditAlertaActivity;
import com.electroshock.mlsearch.alerta.AlertaActivity;
import com.electroshock.mlsearch.alerta.AlertasFragment;
import com.electroshock.mlsearch.data.Alerta;
import com.electroshock.mlsearch.data.ItemDbHelper;

public class AlertaDetailFragment extends Fragment {
    private static final String ARG_ALERTA_ID = "alertaId";

    private String mItemId;

    private CollapsingToolbarLayout mCollapsingView;
    private ImageView mAvatar;
    private TextView mEstado;
    private TextView mCategoria;
    private TextView mPrecioInf;
    private TextView mPrecioSup;

    private ItemDbHelper mItemsDbHelper;

    public AlertaDetailFragment() {
        // Required empty public constructor
    }

    public static AlertaDetailFragment newInstance(String alertaId) {
        AlertaDetailFragment fragment = new AlertaDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ALERTA_ID, alertaId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mItemId = getArguments().getString(ARG_ALERTA_ID);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alerta_detail, container, false);
        mCollapsingView = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
        mAvatar = (ImageView) getActivity().findViewById(R.id.iv_avatar);
        mEstado = root.findViewById(R.id.tv_estado);
        mCategoria = root.findViewById(R.id.tv_categoria);
        mPrecioInf = root.findViewById(R.id.tv_precioInf);
        mPrecioSup = root.findViewById(R.id.tv_precioSup);

        mItemsDbHelper = new ItemDbHelper(getActivity());

        loadAlerta();

        return root;
    }

    private void loadAlerta() {
        new AlertaDetailFragment.GetAlertaByIdTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                showEditScreen();
                break;
            case R.id.action_delete:
                new AlertaDetailFragment.DeleteAlertaTask().execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AlertasFragment.REQUEST_UPDATE_DELETE_ALERT) {
            if (resultCode == Activity.RESULT_OK) {
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        }
    }

    private void showAlerta(Alerta alerta) {
        mCollapsingView.setTitle(alerta.getTittle());
        //Glide.with(this).load(Uri.parse("file:///android_asset/" +item.getAvatarUri())) .centerCrop() .into(mAvatar);
        mEstado.setText(alerta.getStatus());
        mCategoria.setText(alerta.getCategory_id());
        mPrecioInf.setText(alerta.getPrecio_inf());
        mPrecioSup.setText(alerta.getPrecio_sup());
    }

    private void showEditScreen() {
        Intent intent = new Intent(getActivity(), AddEditAlertaActivity.class);
        intent.putExtra(AlertaActivity.EXTRA_ALERTA_ID, mItemId);
        startActivityForResult(intent, AlertasFragment.REQUEST_UPDATE_DELETE_ALERT);
    }

    private void showAlertasScreen(boolean requery) {
        if (!requery) {
            showDeleteError();
        }
        getActivity().setResult(requery ? Activity.RESULT_OK : Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al cargar información", Toast.LENGTH_SHORT).show();
    }

    private void showDeleteError() {
        Toast.makeText(getActivity(),
                "Error al eliminar alerta", Toast.LENGTH_SHORT).show();
    }

    private class GetAlertaByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mItemsDbHelper.getAlertaById(mItemId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showAlerta(new Alerta(cursor));
            } else {
                showLoadError();
            }
        }

    }

    private class DeleteAlertaTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            return mItemsDbHelper.deleteAlerta(mItemId);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            showAlertasScreen(integer > 0);
        }

    }

}
