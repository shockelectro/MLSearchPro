package com.electroshock.mlsearch.addeditalerta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.electroshock.mlsearch.items.ItemsActivity;
import com.electroshock.mlsearch.R;

public class AddEditAlertaActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_ALERTA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String alertaId = getIntent().getStringExtra(ItemsActivity.EXTRA_ITEM_ID);

        setTitle(alertaId == null ? "Añadir alerta" : "Editar alerta");

        AddEditAlertaFragment addEditAlertaFragment = (AddEditAlertaFragment)
                getSupportFragmentManager().findFragmentById(R.id.add_edit_alerta_container);
        if (addEditAlertaFragment == null) {
            addEditAlertaFragment = AddEditAlertaFragment.newInstance(alertaId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_edit_alerta_container, addEditAlertaFragment)
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
