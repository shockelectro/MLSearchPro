package com.electroshock.mlsearch.addeditalerta;

// Alerta
// Query=tittle, categoria, estado, rango precio, dia desde
import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.electroshock.mlsearch.R;
import com.electroshock.mlsearch.data.Alerta;
import com.electroshock.mlsearch.data.Item;
import com.electroshock.mlsearch.data.ItemDbHelper;

/**
 * Vista para creación/edición de un Alerta
 */
public class AddEditAlertaFragment extends Fragment {
    private static final String ARG_ALERTA_ID = "arg_alerta_id";

    private String mAlertaId;
    private ItemDbHelper mItemsDbHelper;
    private FloatingActionButton mSaveButton;
    private TextInputEditText mTittle;
    private TextInputEditText mCategoryId;
    private TextInputEditText mStatus;
    private TextInputEditText mPrecioSup;
    private TextInputEditText mPrecioInf;
    private TextInputLayout mTittleLabel;
    private TextInputLayout mCategoryIdLabel;
    private TextInputLayout mStatusLabel;
    private TextInputLayout mPrecioSupLabel;
    private TextInputLayout mPrecioInfLabel;


    public AddEditAlertaFragment() {
        // Required empty public constructor
    }

    public static AddEditAlertaFragment newInstance(String alertaId) {
        AddEditAlertaFragment fragment = new AddEditAlertaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ALERTA_ID, alertaId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAlertaId = getArguments().getString(ARG_ALERTA_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_item, container, false);

        // Referencias UI
        mSaveButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mTittle = (TextInputEditText) root.findViewById(R.id.et_tittle);
        mCategoryId = (TextInputEditText) root.findViewById(R.id.et_categoryId);
        mStatus = (TextInputEditText) root.findViewById(R.id.et_status);
        mPrecioInf= (TextInputEditText) root.findViewById(R.id.et_precioInf);
        mPrecioSup = (TextInputEditText) root.findViewById(R.id.et_precioSup);

        mTittleLabel = (TextInputLayout) root.findViewById(R.id.til_tittle);
        mCategoryIdLabel = (TextInputLayout) root.findViewById(R.id.til_categoryId);
        mStatusLabel = (TextInputLayout) root.findViewById(R.id.til_status);
        mPrecioInfLabel= (TextInputLayout) root.findViewById(R.id.til_precioInf);
        mPrecioSupLabel = (TextInputLayout) root.findViewById(R.id.til_precioSup);

        // Eventos
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditItem();
            }
        });

        mItemsDbHelper = new ItemDbHelper(getActivity());

        // Carga de datos
        if (mAlertaId != null) {
            loadItem();
        }

        return root;
    }

    private void loadItem() {
        new GetAlertaByIdTask().execute();
    }

    private void addEditItem() {
        boolean error = false;

        String tittle = mTittle.getText().toString();
        String categoryId = mCategoryId.getText().toString();
        String status = mStatus.getText().toString();
        String precioInf = mPrecioInf.getText().toString();
        String precioSup = mPrecioSup.getText().toString();

        if (TextUtils.isEmpty(tittle)) {
            mTittleLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(categoryId)) {
            mStatusLabel.setError(getString(R.string.field_error));
            error = true;
        }


        if (error) {
            return;
        }

        Alerta alerta = new Alerta(tittle, categoryId, status, precioInf,precioSup);

        new AddEditAlertaTask().execute(alerta);

    }

    private void showAlertaScreen(Boolean requery) {
        if (!requery) {
            showAddEditError();
            getActivity().setResult(Activity.RESULT_CANCELED);
        } else {
            getActivity().setResult(Activity.RESULT_OK);
        }

        getActivity().finish();
    }

    private void showAddEditError() {
        Toast.makeText(getActivity(),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

    private void showAlerta(Alerta alerta) {
        mTittle.setText(alerta.getTittle());
        mCategoryId.setText(alerta.getCategory_id());
        mStatus.setText(alerta.getStatus());
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al editar alerta", Toast.LENGTH_SHORT).show();
    }

    private class GetAlertaByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mItemsDbHelper.getItemById(mAlertaId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showAlerta(new Alerta(cursor));
            } else {
                showLoadError();
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
        }

    }

    private class AddEditAlertaTask extends AsyncTask<Alerta, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Alerta... alertas) {
            if (mAlertaId != null) {
                return mItemsDbHelper.updateAlerta(alertas[0], mAlertaId) > 0;

            } else {
                return mItemsDbHelper.saveAlerta(alertas[0]) > 0;
            }

        }

        @Override
        protected void onPostExecute(Boolean result) {
            showAlertaScreen(result);
        }

    }

}
