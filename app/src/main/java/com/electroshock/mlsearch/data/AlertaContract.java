package com.electroshock.mlsearch.data;

import android.provider.BaseColumns;

public class AlertaContract {
    public static abstract class AlertaEntry implements BaseColumns {
        public static final String TABLE_NAME ="alerta";

        public static final String ID = "id";
        public static final String TITTLE = "tittle";
        public static final String CATEGORY_ID = "category_id";
        public static final String STATUS = "status";
        public static final String START_TIME = "start_time";
        public static final String STOP_TIME = "stop_time";
        public static final String DATE_CREATED = "date_created";
        public static final String PRECIO_INF = "precio_inf";
        public static final String PRECIO_SUP = "precio_sup";
    }

}
