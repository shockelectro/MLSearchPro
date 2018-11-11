package com.electroshock.mlsearch.sqlite;

import android.provider.BaseColumns;

public class dbSchema implements BaseColumns {

    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_TELEFONO="telefono";

    public static final String CREAR_TABLA_USUARIO=
            "CREATE TABLE " + ""+ TABLA_USUARIO +" (" +
                    CAMPO_ID + " " + "INTEGER, " +
                    CAMPO_NOMBRE +" TEXT," +
                    CAMPO_TELEFONO +" TEXT )";


    public static final String BORRAR_TABLA_USUARIO=
            "DROP TABLE IF EXISTS " + TABLA_USUARIO;


    public static abstract class ListadoItems implements BaseColumns {
        //Constantes campos tabla items
        public static final String TABLA_ITEMS = "item";
        public static final String COLUM_ID_ITEM = "id_item";
        public static final String COLUM_ITEM_NOMBRE = "nombre_item";
        public static final String COLUM_ID_PRECIOS = "id_precios";
    }

    public static final String CREAR_TABLA_ITEMS =
            "CREATE TABLE " + "" + ListadoItems.TABLA_ITEMS + " (" +
                    ListadoItems.COLUM_ID_ITEM +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ListadoItems.COLUM_ITEM_NOMBRE + " TEXT, " +
                    ListadoItems.COLUM_ID_PRECIOS + " INTEGER)";

    public static final String BORRAR_TABLA_ITEMS=
            "DROP TABLE IF EXISTS " + ListadoItems.TABLA_ITEMS;


    public static abstract class PreciosHistoricos implements BaseColumns {
        //Constantes campos tabla items PRECIOS
        // PK idtem + date
        public static final String TABLA_ITEM_PRECIOS = "precios";
        public static final String COLUM_ID_ITEM = "id_item";
        public static final String COLUM_PRECIO_DATE = "precio_date";
        public static final String COLUM_PRECIO = "precio_valor";
    }

    public static final String CREAR_TABLA_ITEM_PRECIOS =
            "CREATE TABLE " + ""+ PreciosHistoricos.TABLA_ITEM_PRECIOS +" ("
                    + PreciosHistoricos.COLUM_ID_ITEM + " INTEGER NOT NULL, "
                    + PreciosHistoricos.COLUM_PRECIO_DATE + " DATE NOT NULL, "
                    + PreciosHistoricos.COLUM_PRECIO + " FLOAT, "
                    + "PRIMARY KEY (" + PreciosHistoricos.COLUM_ID_ITEM + "," + PreciosHistoricos.COLUM_PRECIO_DATE + "))";

    public static final String BORRAR_TABLA_ITEMS_PRECIOS=
            "DROP TABLE IF EXISTS " + PreciosHistoricos.TABLA_ITEM_PRECIOS;

    public static abstract class Busquedas implements BaseColumns {
        //Constantes campos tabla busqueda
        public static final String TABLA_BUSQUEDA = "busqueda";
        public static final String COLUM_ID_BUSQUEDA = "id_busqueda";
        public static final String COLUM_CATEGORIA = "categoria";
        public static final String COLUM_QUERY = "query";
        public static final String COLUM_PRECIO_MIN = "precio_min";
        public static final String COLUM_PRECIO_MAX = "precio_max";
        public static final String COLUM_ESTADO = "item_estado";
    }

    public static final String CREAR_TABLA_BUSQUEDA =
            "CREATE TABLE " + ""+ Busquedas.TABLA_BUSQUEDA + " (" +
                    Busquedas.COLUM_ID_BUSQUEDA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Busquedas.COLUM_CATEGORIA + " TEXT, " +
                    Busquedas.COLUM_QUERY + " TEXT, " +
                    Busquedas.COLUM_PRECIO_MIN + " INTEGER," +
                    Busquedas.COLUM_PRECIO_MAX + " INTEGER," +
                    Busquedas.COLUM_ESTADO + " TEXT)";

    public static final String BORRAR_TABLA_BUSQUEDA=
            "DROP TABLE IF EXISTS " + Busquedas.TABLA_BUSQUEDA;

}
