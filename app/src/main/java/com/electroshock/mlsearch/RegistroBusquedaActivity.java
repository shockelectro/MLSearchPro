package com.electroshock.mlsearch;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.electroshock.mlsearch.sqlite.ConexionSQLiteHelper;
import com.electroshock.mlsearch.sqlite.dbSchema;

public class RegistroBusquedaActivity extends AppCompatActivity {

    EditText campoCategoria, campoQuery, campoPrecioMin, campoPrecioMax, campoEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_busqueda);

        campoCategoria = (EditText) findViewById(R.id.campoCategoria);
        campoQuery = (EditText) findViewById(R.id.campoQuery);
        campoPrecioMin = (EditText) findViewById(R.id.campoPrecioMin);
        campoPrecioMax = (EditText) findViewById(R.id.campoPrecioMax);
        campoEstado = (EditText) findViewById(R.id.campoEstado);

    }

    public void onClick(View view) {
        registrarBusqueda();
        //registrarUsuariosSql();
    }

    private void registrarUsuariosSql() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_items",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        //insert into usuario (id,nombre,telefono) values (123,'Cristian','85665223')



        String insert="INSERT INTO "+ dbSchema.Busquedas.TABLA_BUSQUEDA
                +" ( " +
                dbSchema.Busquedas.COLUM_CATEGORIA + "," +
                dbSchema.Busquedas.COLUM_QUERY +","+
                dbSchema.Busquedas.COLUM_PRECIO_MIN + ","+
                dbSchema.Busquedas.COLUM_PRECIO_MIN + ","+
                dbSchema.Busquedas.COLUM_ESTADO + ")" +
                " VALUES ('"+
                campoCategoria.getText().toString()+"', '"+
                campoQuery.getText().toString()+"','" +
                campoPrecioMin.getText().toString() +"','" +
                campoPrecioMax.getText().toString() +"','" +
                campoEstado.getText().toString() + "')";

        db.execSQL(insert);
        db.close();
    }


    private void registrarBusqueda() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_items",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(dbSchema.Busquedas.COLUM_CATEGORIA, campoCategoria.getText().toString());
        values.put(dbSchema.Busquedas.COLUM_QUERY, campoQuery.getText().toString());
        values.put(dbSchema.Busquedas.COLUM_PRECIO_MIN, campoPrecioMin.getText().toString());
        values.put(dbSchema.Busquedas.COLUM_PRECIO_MAX, campoPrecioMax.getText().toString());
        values.put(dbSchema.Busquedas.COLUM_ESTADO, campoEstado.getText().toString());

        Long idResultante=db.insert(dbSchema.Busquedas.TABLA_BUSQUEDA, dbSchema.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }
}