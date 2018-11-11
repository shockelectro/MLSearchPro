package com.electroshock.mlsearch.data;

import android.content.ContentValues;
import android.database.Cursor;

public class Alerta {
    public String id;
    public String tittle;
    public String category_id;
    public String status;
    public String start_time;
    public String stop_time;
    public String date_created;
    public String precio_inf;
    public String precio_sup;

    public Alerta(String tittle, String category_id, String status, String precio_inf, String precio_sup) {
        this.tittle = tittle;
        this.category_id = category_id;
        this.status = status;
        this.precio_inf = precio_inf;
        this.precio_sup = precio_sup;
    }

    public Alerta(String id, String tittle, String category_id, String status, String start_time, String stop_time, String date_created, String precio_inf, String precio_sup) {
        this.id = id;
        this.tittle = tittle;
        this.category_id = category_id;
        this.status = status;
        this.start_time = start_time;
        this.stop_time = stop_time;
        this.date_created = date_created;
        this.precio_inf = precio_inf;
        this.precio_sup = precio_sup;
    }

    public Alerta(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(AlertaContract.AlertaEntry._ID));
        tittle = cursor.getString(cursor.getColumnIndex(AlertaContract.AlertaEntry.TITTLE));
        status = cursor.getString(cursor.getColumnIndex(AlertaContract.AlertaEntry.STATUS));
        category_id = cursor.getString(cursor.getColumnIndex(AlertaContract.AlertaEntry.CATEGORY_ID));
        start_time = cursor.getString(cursor.getColumnIndex(AlertaContract.AlertaEntry.START_TIME));
        stop_time = cursor.getString(cursor.getColumnIndex(AlertaContract.AlertaEntry.STOP_TIME));
        date_created = cursor.getString(cursor.getColumnIndex(AlertaContract.AlertaEntry.DATE_CREATED));
        precio_inf = cursor.getString(cursor.getColumnIndex(AlertaContract.AlertaEntry.PRECIO_INF));
        precio_sup = cursor.getString(cursor.getColumnIndex(AlertaContract.AlertaEntry.PRECIO_SUP));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(AlertaContract.AlertaEntry._ID, id);
        values.put(AlertaContract.AlertaEntry.TITTLE, tittle);
        values.put(AlertaContract.AlertaEntry.STATUS, status);
        values.put(AlertaContract.AlertaEntry.CATEGORY_ID, category_id);
        values.put(AlertaContract.AlertaEntry.START_TIME, start_time);
        values.put(AlertaContract.AlertaEntry.STOP_TIME, stop_time);
        values.put(AlertaContract.AlertaEntry.DATE_CREATED, date_created);
        values.put(AlertaContract.AlertaEntry.PRECIO_INF, precio_inf);
        values.put(AlertaContract.AlertaEntry.PRECIO_SUP, precio_sup);
        return values;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getPrecio_inf() {
        return precio_inf;
    }

    public void setPrecio_inf(String precio_inf) {
        this.precio_inf = precio_inf;
    }

    public String getPrecio_sup() {
        return precio_sup;
    }

    public void setPrecio_sup(String precio_sup) {
        this.precio_sup = precio_sup;
    }
}
