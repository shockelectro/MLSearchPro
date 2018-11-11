package com.electroshock.mlsearch;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.electroshock.mlsearch.consulta.ConsultaActivity;
import com.electroshock.mlsearch.sqlite.DatabaseAdapter;


public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    DatabaseAdapter dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dba = new DatabaseAdapter(getApplicationContext());

        //ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_items",null,1);
        //db=conn.getWritableDatabase();

    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnConsultaLista:
                miIntent=new Intent(MainActivity.this,ConsultaActivity.class);
                break;
            case R.id.btnRegistroBusqueda:
                miIntent=new Intent(MainActivity.this,RegistroBusquedaActivity.class);
                break;

        }
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }

    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        // Close The Database
        dba.close();
    }

}
