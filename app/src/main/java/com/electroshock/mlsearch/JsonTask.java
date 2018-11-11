package com.electroshock.mlsearch;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

import com.electroshock.mlsearch.model.NoteML;

public class JsonTask extends AsyncTask<URL, Void, List<NoteML>>{
    /*
        Cliente para la conexión al servidor
         */
    HttpURLConnection con;

    @Override
    protected List doInBackground(URL... urls) {
        List resultados = null;

        try {

            // Establecer la conexi�n
            con = (HttpURLConnection)urls[0].openConnection();
            con.setConnectTimeout(15000);
            con.setReadTimeout(10000);

            // Obtener el estado del recurso
            int statusCode = con.getResponseCode();

            if(statusCode!=200) {
                resultados = new ArrayList();
                resultados.add(new NoteML());

            }
            else{

            
            // Parsear el flujo con formato JSON                  
                InputStream in = new BufferedInputStream(con.getInputStream());

                // JsonAnimalParser parser = new JsonAnimalParser();
                GsonSearchParser parser = new GsonSearchParser();

                resultados = parser.leerFlujoJson(in);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            con.disconnect();
        }
        return resultados;
    }
/*
    @Override
    protected void onPostExecute(List<Animal> animales) {

        //   Asignar los objetos de Json parseados al adaptador
        if(animales!=null) {
            adaptador = new AdaptadorDeAnimales(getBaseContext(), animales);
            lista.setAdapter(adaptador);
        }else{
            Toast.makeText(
                    getBaseContext(),
                    "Ocurri� un error de Parsing Json",
                    Toast.LENGTH_SHORT)
            .show();
        }

    }

    */
}
