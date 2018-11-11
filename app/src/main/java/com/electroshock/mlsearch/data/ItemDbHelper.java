package com.electroshock.mlsearch.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.electroshock.mlsearch.data.ItemContract.ItemEntry;
import com.electroshock.mlsearch.data.AlertaContract.AlertaEntry;

public class ItemDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "itemsMA3.db";

    public ItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ItemEntry.TABLE_NAME + " ("
                + ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ItemEntry.ID + " TEXT NOT NULL,"
                + ItemEntry.TITTLE + " TEXT NOT NULL,"
                + ItemEntry.STATUS + " TEXT,"
                + ItemEntry.SELLER_ID + " INT,"
                + ItemEntry.CATEGORY_ID + " TEXT NOT NULL,"
                + ItemEntry.START_TIME + " DATETIME,"
                + ItemEntry.STOP_TIME + " DATETIME,"
                + ItemEntry.LAST_UPDATE + " DATETIME,"
                + ItemEntry.DATE_CREATED + " DATETIME,"
                + ItemEntry.GEOLAT + " POINT,"
                + ItemEntry.GEOLON + " POINT,"
                + ItemEntry.PRECIOS + " TEXT,"
                + "UNIQUE (" + ItemEntry.ID + "))");

        db.execSQL("CREATE TABLE " + AlertaEntry.TABLE_NAME + " ("
                + AlertaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AlertaEntry.TITTLE + " TEXT NOT NULL,"
                + AlertaEntry.STATUS + " TEXT,"
                + AlertaEntry.CATEGORY_ID + " TEXT NOT NULL,"
                + AlertaEntry.START_TIME + " DATETIME,"
                + AlertaEntry.STOP_TIME + " DATETIME,"
                + AlertaEntry.DATE_CREATED + " DATETIME,"
                + AlertaEntry.PRECIO_INF + " INTEGER,"
                + AlertaEntry.PRECIO_SUP + " INTEGER,"
                + "UNIQUE (" + ItemEntry._ID + "))");


        // Insertar datos ficticios para prueba inicial
        mockData(db);
        mockDataAlertas(db);
    }

    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockItem(sqLiteDatabase, new Item("7658765", "Toshiba n55",
                "12000", "used", "9873241","MLA399858","2018-09-18T03:25:36.000Z","2018-09-25T03:25:36.000Z",
                "2018-09-18T03:25:36.000Z", "2018-09-24T21:17:52.548Z",
                "-34.91102","-57.958046"));
        mockItem(sqLiteDatabase, new Item("5475745", "Lenovo y70",
                "18000", "used", "9873241","MLA399858","2018-09-18T03:25:36.000Z","2018-09-25T03:25:36.000Z",
                "2018-09-18T03:25:36.000Z", "2018-09-24T21:17:52.548Z",
                "-34.91102","-57.958046"));
    }

    private void mockDataAlertas(SQLiteDatabase sqLiteDatabase) {
        mockAlerta(sqLiteDatabase, new Alerta("i7","MLA399858", "nuevo","1000","20000"));
        mockAlerta(sqLiteDatabase, new Alerta("i3","MLA399858","usado", "0","10000"));
    }

    public long mockItem(SQLiteDatabase db, Item item) {
        return db.insert(
                ItemEntry.TABLE_NAME,
                null,
                item.toContentValues());
    }

    public long mockAlerta(SQLiteDatabase db, Alerta alerta) {
        return db.insert(
                AlertaEntry.TABLE_NAME,
                null,
                alerta.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveItem(Item item) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                ItemEntry.TABLE_NAME,
                null,
                item.toContentValues());
    }

    public long saveAlerta(Alerta alerta) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                AlertaEntry.TABLE_NAME,
                null,
                alerta.toContentValues());
    }

    public Cursor getAllItems() {
        return getReadableDatabase()
                .query(
                        ItemEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getAllAlertas() {
        return getReadableDatabase()
                .query(
                        AlertaEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getItemById(String itemId) {
        Cursor c = getReadableDatabase().query(
                ItemEntry.TABLE_NAME,
                null,
                ItemEntry.ID + " LIKE ?",
                new String[]{itemId},
                null,
                null,
                null);
        return c;
    }

    public Cursor getAlertaById(String alertaId) {
        Cursor c = getReadableDatabase().query(
                AlertaEntry.TABLE_NAME,
                null,
                AlertaEntry._ID + " LIKE ?",
                new String[]{alertaId},
                null,
                null,
                null);
        return c;
    }

    public int deleteItem(String itemId) {
        return getWritableDatabase().delete(
                ItemEntry.TABLE_NAME,
                ItemEntry.ID + " LIKE ?",
                new String[]{itemId});
    }

    public int deleteAlerta(String alertaId) {
        return getWritableDatabase().delete(
                AlertaEntry.TABLE_NAME,
                AlertaEntry._ID + " LIKE ?",
                new String[]{alertaId});
    }

    public int updateItem(Item item, String itemId) {
        return getWritableDatabase().update(
                ItemEntry.TABLE_NAME,
                item.toContentValues(),
                ItemEntry.ID + " LIKE ?",
                new String[]{itemId}
        );
    }
    public int updateAlerta(Alerta alerta, String alertaId) {
        return getWritableDatabase().update(
                AlertaEntry.TABLE_NAME,
                alerta.toContentValues(),
                AlertaEntry._ID + " LIKE ?",
                new String[]{alertaId}
        );
    }
}
