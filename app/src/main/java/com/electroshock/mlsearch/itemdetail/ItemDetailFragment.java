package com.electroshock.mlsearch.itemdetail;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

import com.bumptech.glide.Glide;
import com.electroshock.mlsearch.R;
import com.electroshock.mlsearch.addeditalerta.AddEditAlertaActivity;
import com.electroshock.mlsearch.data.Item;
import com.electroshock.mlsearch.data.ItemDbHelper;
import com.electroshock.mlsearch.items.ItemsActivity;
import com.electroshock.mlsearch.items.ItemsFragment;

/**
 * Vista para el detalle del abogado
 */
public class ItemDetailFragment extends Fragment {
    private static final String ARG_LAWYER_ID = "lawyerId";

    private String mItemId;

    private CollapsingToolbarLayout mCollapsingView;
    private ImageView mAvatar;
    private TextView mPhoneNumber;
    private TextView mSpecialty;
    private TextView mBio;

    private ItemDbHelper mItemsDbHelper;


    public ItemDetailFragment() {
        // Required empty public constructor
    }

    public static ItemDetailFragment newInstance(String lawyerId) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LAWYER_ID, lawyerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mItemId = getArguments().getString(ARG_LAWYER_ID);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item_detail, container, false);
        mCollapsingView = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
        mAvatar = (ImageView) getActivity().findViewById(R.id.iv_avatar);
        mPhoneNumber = (TextView) root.findViewById(R.id.tv_phone_number);
        mSpecialty = (TextView) root.findViewById(R.id.tv_estado);
        mBio = (TextView) root.findViewById(R.id.tv_bio);

        mItemsDbHelper = new ItemDbHelper(getActivity());

        loadLawyer();

        return root;
    }

    private void loadLawyer() {
        new GetLawyerByIdTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                showEditScreen();
                break;
            case R.id.action_delete:
                new DeleteItemTask().execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ItemsFragment.REQUEST_UPDATE_DELETE_ITEM) {
            if (resultCode == Activity.RESULT_OK) {
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        }
    }

    private void showLawyer(Item item) {
        mCollapsingView.setTitle(item.getTittle());
        Glide.with(this)
                .load(Uri.parse("file:///android_asset/" /* +
                         item.getAvatarUri() */))
                .centerCrop()
                .into(mAvatar);
        mPhoneNumber.setText(item.getSeller_id());
        mSpecialty.setText(item.getStatus());
        mBio.setText(item.getSeller_id());
    }

    private void showEditScreen() {
        Intent intent = new Intent(getActivity(), AddEditAlertaActivity.class);
        intent.putExtra(ItemsActivity.EXTRA_ITEM_ID, mItemId);
        startActivityForResult(intent, ItemsFragment.REQUEST_UPDATE_DELETE_ITEM);
    }

    private void showItemsScreen(boolean requery) {
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
                "Error al eliminar abogado", Toast.LENGTH_SHORT).show();
    }

    private class GetLawyerByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mItemsDbHelper.getItemById(mItemId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showLawyer(new Item(cursor));
            } else {
                showLoadError();
            }
        }

    }

    private class DeleteItemTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            return mItemsDbHelper.deleteItem(mItemId);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            showItemsScreen(integer > 0);
        }

    }

}
