package com.electroshock.mlsearch.data;

import android.provider.BaseColumns;

public class ItemContract {
    public static abstract class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME ="item";

        public static final String ID = "id";
        public static final String TITTLE = "tittle";
        public static final String STATUS = "status";
        public static final String SELLER_ID = "seller_id";
        public static final String CATEGORY_ID = "category_id";
        public static final String START_TIME = "start_time";
        public static final String STOP_TIME = "stop_time";
        public static final String LAST_UPDATE = "last_update";
        public static final String DATE_CREATED = "date_created";
        public static final String GEOLAT = "geolat";
        public static final String GEOLON = "geolon";
        public static final String PRECIOS = "prices";
    }
}
