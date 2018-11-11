package com.electroshock.mlsearch.consulta;

//import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.electroshock.mlsearch.ProductAdapter;
import com.electroshock.mlsearch.R;
import com.electroshock.mlsearch.data.Alerta;
import com.electroshock.mlsearch.data.ItemDbHelper;
import com.electroshock.mlsearch.model.NoteML;
import com.electroshock.mlsearch.model.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {


    private  String URL_TO_HIT = "https://api.mercadolibre.com/sites/MLA/search?category=MLA1652&q=i7&limit=30&condition=used&since=today&price=*-24000.0";
    private ListView lvProductos;
    //private ProgressDialog dialog;
    private ItemDbHelper mItemsDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        String stopTime="2038-08-21T06:25:57.000Z";
        Date stopTimeDate;
        SimpleDateFormat formatter;

        formatter= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000Z");

        try {
            stopTimeDate = formatter.parse(stopTime.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
/*
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait..."); // showing a dialog for loading the data
        */
        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

        lvProductos = findViewById(R.id.lvProductos);

        mItemsDbHelper = new ItemDbHelper(this);

        Cursor cursor= mItemsDbHelper.getAlertaById(getIntent().getExtras().getString("alertaId"));

        Alerta alerta= new Alerta(cursor);
        // To start fetching the data when app start, uncomment below line to start the async task.
        new JsonTask(lvProductos,getApplicationContext()).execute(URL_TO_HIT);

    }

    public static class JsonTask extends AsyncTask<String,String, List<Producto>> {
        NoteML noteml;
        private HttpURLConnection con;
        private ListView lvProductos;
        private Context context;

        public JsonTask(ListView lvProductos, Context context) {
            this.lvProductos = lvProductos;
            this.context = context;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            dialog.show();
        }

        @Override
        protected List<Producto> doInBackground(String... params) {
            List<Producto> productos = null;

            try {
                // Establecer la conexión
                con = (HttpURLConnection) new URL(params[0]).openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);

                // Obtener el estado del recurso
                int statusCode = con.getResponseCode();

                if (statusCode == 401) {
  //                  dialog.setMessage("Token invalido");
                }else if (statusCode != 200){
                    productos = new ArrayList<>();
                    productos.add(new Producto());
                } else {
                    // Parsear el flujo con formato JSON
                    InputStream in = new BufferedInputStream(con.getInputStream());

                    Gson gson = new GsonBuilder().create();
                    JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
                    noteml = gson.fromJson(reader, NoteML.class);
                    productos=noteml.getResults();
                    reader.close();
                }

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                con.disconnect();
            }
            return productos;
        }


        @Override
        protected void onPostExecute(final List<Producto> result) {
            super.onPostExecute(result);
    //        dialog.dismiss();
            if(result != null) {
                ProductAdapter adapter = new ProductAdapter(context, R.layout.row, result);
                lvProductos.setAdapter(adapter);
                lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {  // list item click opens a new detailed activity
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Producto movieModel = result.get(position); // getting the model
                        //Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        //intent.putExtra("movieModel", new Gson().toJson(movieModel)); // converting model json into string type and sending it via intent
                        //startActivity(intent);
                    }
                });
            } else {
                Toast.makeText(context, "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
