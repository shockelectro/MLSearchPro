package com.electroshock.mlsearch.items;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.design.widget.FloatingActionButton;

import com.electroshock.mlsearch.addeditalerta.AddEditAlertaActivity;
import com.electroshock.mlsearch.data.ItemDbHelper;
import com.electroshock.mlsearch.data.ItemContract.ItemEntry;
import com.electroshock.mlsearch.itemdetail.ItemDetailActivity;

import com.electroshock.mlsearch.R;

public class ItemsFragment extends Fragment {
    public static final int REQUEST_UPDATE_DELETE_ITEM = 2;

    private ItemDbHelper mItemsDbHelper;

    private ListView mItemsList;
    private ItemsCursorAdapter mItemsAdapter;
    private FloatingActionButton mAddButton;


    public ItemsFragment() {
        // Required empty public constructor
    }

    public static ItemsFragment newInstance() {
        return new ItemsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_items, container, false);

        // Referencias UI
        mItemsList = (ListView) root.findViewById(R.id.items_list);
        mItemsAdapter = new ItemsCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        // Setup
        mItemsList.setAdapter(mItemsAdapter);

        // Eventos
        mItemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mItemsAdapter.getItem(i);
                String currentItemId = currentItem.getString(
                        currentItem.getColumnIndex(ItemEntry.ID));

                showDetailScreen(currentItemId);
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddScreen();
            }
        });

        //TODO Elimina database
        //getActivity().deleteDatabase(ItemDbHelper.DATABASE_NAME);

        // Instancia de helper
        mItemsDbHelper = new ItemDbHelper(getActivity());

        // Carga de datos
        loadItems();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case AddEditAlertaActivity.REQUEST_ADD_ALERTA:
                    showSuccessfullSavedMessage();
                    loadItems();
                    break;
                case REQUEST_UPDATE_DELETE_ITEM:
                    loadItems();
                    break;
            }
        }
    }

    private void loadItems() {
        new ItemsLoadTask().execute();
    }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                "Abogado guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    /**
     * Llama activity AddItem
     */
    private void showAddScreen() {
        Intent intent = new Intent(getActivity(), AddEditAlertaActivity.class);
        startActivityForResult(intent, AddEditAlertaActivity.REQUEST_ADD_ALERTA);
    }

    /**
     * Llama al activity detalle.
     * @param itemId Id del item
     */
    private void showDetailScreen(String itemId) {
        Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
        intent.putExtra(ItemsActivity.EXTRA_ITEM_ID, itemId);
        startActivityForResult(intent, REQUEST_UPDATE_DELETE_ITEM);
    }

    private class ItemsLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mItemsDbHelper.getAllItems();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mItemsAdapter.swapCursor(cursor);
            } else {
                // Mostrar empty state
            }
        }
    }

}
