package com.electroshock.mlsearch;

import com.electroshock.mlsearch.model.NoteML;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GsonSearchParser {

    public List<NoteML> leerFlujoJson(InputStream in) throws IOException {
        Gson gson = new GsonBuilder().create();

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        //Gson gsonn = new GsonBuilder().setPrettyPrinting().create();
        //String jsonn = gsonn.toJson(reader);
        //System.out.println("salida:" + jsonn);
        List<NoteML> resultados = new ArrayList<NoteML>();
        NoteML noteml = gson.fromJson(reader, NoteML.class);
        /*
        reader.beginObject();
        while (reader.hasNext()){
            noteml = gson.fromJson(reader, NoteML.class);
            resultados.add(noteml);
        }

        reader.endArray();
        */
        reader.close();
        return resultados;

    }
}
