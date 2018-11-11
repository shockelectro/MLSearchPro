package com.electroshock.mlsearch.alerta;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.electroshock.mlsearch.consulta.ConsultaActivity;
import com.electroshock.mlsearch.R;
import com.electroshock.mlsearch.addeditalerta.AddEditAlertaActivity;
import com.electroshock.mlsearch.alertadetail.AlertaDetailActivity;
import com.electroshock.mlsearch.data.AlertaContract;
import com.electroshock.mlsearch.data.ItemDbHelper;

public class AlertasFragment extends Fragment {
    public static final int REQUEST_UPDATE_DELETE_ALERT = 2;

    private ItemDbHelper mItemsDbHelper;

    private ListView mAlertasList;
    private AlertasCursorAdapter mAlertasAdapter;
    private FloatingActionButton mAddButton;


    public AlertasFragment() {
        // Required empty public constructor
    }

    public static AlertasFragment newInstance() {
        return new AlertasFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alertas, container, false);

        // Referencias UI
        mAlertasList = (ListView) root.findViewById(R.id.alertas_list);
        mAlertasAdapter = new AlertasCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        // Setup
        mAlertasList.setAdapter(mAlertasAdapter);

        // Eventos
        mAlertasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO Llamada de listaalertas
                Cursor currentAlerta = (Cursor) mAlertasAdapter.getItem(i);
                String currentAlertaId = currentAlerta.getString(
                        currentAlerta.getColumnIndex(AlertaContract.AlertaEntry._ID));

                //TODO cambiar showdetail por ConsultaActivity
                //showDetailScreen(currentAlertaId);
                Intent intent=new Intent(getActivity(),ConsultaActivity.class);
                if (intent!=null){
                    intent.putExtra("alertaId",currentAlertaId);
                    startActivity(intent);
                }
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddScreen();
            }
        });

        //TODO Elimina database
        getActivity().deleteDatabase(ItemDbHelper.DATABASE_NAME);

        // Instancia de helper
        mItemsDbHelper = new ItemDbHelper(getActivity());

        // Carga de datos
        loadAlertas();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case AddEditAlertaActivity.REQUEST_ADD_ALERTA:
                    showSuccessfullSavedMessage();
                    loadAlertas();
                    break;
                case REQUEST_UPDATE_DELETE_ALERT:
                    loadAlertas();
                    break;
            }
        }
    }

    private void loadAlertas() {
        new AlertasFragment.AlertasLoadTask(mItemsDbHelper,mAlertasAdapter).execute();
    }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                "Alerta guardada correctamente", Toast.LENGTH_SHORT).show();
    }

    private void showAddScreen() {
        Intent intent = new Intent(getActivity(), AddEditAlertaActivity.class);
        startActivityForResult(intent, AddEditAlertaActivity.REQUEST_ADD_ALERTA);
    }

    private void showDetailScreen(String alertaId) {
        Intent intent = new Intent(getActivity(), AlertaDetailActivity.class);
        intent.putExtra(AlertaActivity.EXTRA_ALERTA_ID, alertaId);
        startActivityForResult(intent, REQUEST_UPDATE_DELETE_ALERT);
    }

    private static class AlertasLoadTask extends AsyncTask<Void, Void, Cursor> {
        private ItemDbHelper mItemsDbHelper;
        private AlertasCursorAdapter mAlertasAdapter;

        public AlertasLoadTask(ItemDbHelper mItemsDbHelper, AlertasCursorAdapter mAlertasAdapter) {
            this.mItemsDbHelper = mItemsDbHelper;
            this.mAlertasAdapter = mAlertasAdapter;
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mItemsDbHelper.getAllAlertas();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mAlertasAdapter.swapCursor(cursor);
            } else {
                // Mostrar empty state
            }
        }
    }

}
