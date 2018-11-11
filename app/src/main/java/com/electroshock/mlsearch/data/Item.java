package com.electroshock.mlsearch.data;

import android.content.ContentValues;
import android.database.Cursor;
import com.electroshock.mlsearch.data.ItemContract.ItemEntry;

public class Item {
    public String id;
    public String tittle;
    public String prices;
    public String status;
    public String seller_id;
    public String category_id;
    public String start_time;
    public String stop_time;
    public String last_update;
    public String date_created;
    public String geolat;
    public String geolon;


    public Item(String tittle, String category_id, String seller_id, String status){
        this.tittle=tittle;
        this.category_id=category_id;
        this.seller_id=seller_id;
        this.status=status;
    }

    public Item(String id, String tittle, String prices, String status, String seller_id, String category_id, String start_time, String stop_time, String last_update, String date_created, String geolat, String geolon) {
        this.id = id;
        this.tittle = tittle;
        this.prices = prices;
        this.status = status;
        this.seller_id = seller_id;
        this.category_id = category_id;
        this.start_time = start_time;
        this.stop_time = stop_time;
        this.last_update = last_update;
        this.date_created = date_created;
        this.geolat = geolat;
        this.geolon = geolon;
    }

    public Item(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(ItemEntry.ID));
        tittle = cursor.getString(cursor.getColumnIndex(ItemEntry.TITTLE));
        status = cursor.getString(cursor.getColumnIndex(ItemEntry.STATUS));
        seller_id = cursor.getString(cursor.getColumnIndex(ItemEntry.SELLER_ID));
        category_id = cursor.getString(cursor.getColumnIndex(ItemEntry.CATEGORY_ID));
        start_time = cursor.getString(cursor.getColumnIndex(ItemEntry.START_TIME));
        stop_time = cursor.getString(cursor.getColumnIndex(ItemEntry.STOP_TIME));
        last_update = cursor.getString(cursor.getColumnIndex(ItemEntry.LAST_UPDATE));
        date_created = cursor.getString(cursor.getColumnIndex(ItemEntry.DATE_CREATED));
        geolat = cursor.getString(cursor.getColumnIndex(ItemEntry.GEOLAT));
        geolon = cursor.getString(cursor.getColumnIndex(ItemEntry.GEOLON));
        prices = cursor.getString(cursor.getColumnIndex(ItemEntry.PRECIOS));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(ItemEntry.ID, id);
        values.put(ItemEntry.TITTLE, tittle);
        values.put(ItemEntry.STATUS, status);
        values.put(ItemEntry.SELLER_ID, seller_id);
        values.put(ItemEntry.CATEGORY_ID, category_id);
        values.put(ItemEntry.START_TIME, start_time);
        values.put(ItemEntry.STOP_TIME, stop_time);
        values.put(ItemEntry.LAST_UPDATE, last_update);
        values.put(ItemEntry.DATE_CREATED, date_created);
        values.put(ItemEntry.GEOLAT, geolat);
        values.put(ItemEntry.GEOLON, geolon);
        values.put(ItemEntry.PRECIOS, prices);
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

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
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

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getGeolat() {
        return geolat;
    }

    public void setGeolat(String geolat) {
        this.geolat = geolat;
    }

    public String getGeolon() {
        return geolon;
    }

    public void setGeolon(String geolon) {
        this.geolon = geolon;
    }
}
