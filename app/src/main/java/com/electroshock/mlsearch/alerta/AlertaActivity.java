package com.electroshock.mlsearch.alerta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.electroshock.mlsearch.R;

public class AlertaActivity  extends AppCompatActivity {

        public static final String EXTRA_ALERTA_ID = "extra_alerta_id";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_alertas);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            AlertasFragment fragment = (AlertasFragment)
                    getSupportFragmentManager().findFragmentById(R.id.alertas_container);

            if (fragment == null) {
                fragment = AlertasFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.alertas_container, fragment)
                        .commit();
            }
        }
    }
