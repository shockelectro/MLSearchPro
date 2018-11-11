package com.electroshock.mlsearch.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "";


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbSchema.CREAR_TABLA_ITEMS);
        db.execSQL(dbSchema.CREAR_TABLA_ITEM_PRECIOS);
        db.execSQL(dbSchema.CREAR_TABLA_BUSQUEDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL(dbSchema.BORRAR_TABLA_ITEMS);
        db.execSQL(dbSchema.BORRAR_TABLA_ITEMS_PRECIOS);
        db.execSQL(dbSchema.BORRAR_TABLA_BUSQUEDA);
        onCreate(db);
    }
}
