package com.electroshock.mlsearch.alertadetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.electroshock.mlsearch.R;
import com.electroshock.mlsearch.alerta.AlertaActivity;
import com.electroshock.mlsearch.itemdetail.ItemDetailFragment;
import com.electroshock.mlsearch.items.ItemsActivity;

public class AlertaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String id = getIntent().getStringExtra(AlertaActivity.EXTRA_ALERTA_ID);

        AlertaDetailFragment fragment = (AlertaDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.alerta_detail_container);
        if (fragment == null) {
            fragment = AlertaDetailFragment.newInstance(id);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.alerta_detail_container, fragment)
                    .commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alerta_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

