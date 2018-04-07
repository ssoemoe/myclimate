package com.bitcamp2018.myclimate.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shane on 4/7/2018.
 */

public class DatabaseAdapter extends SQLiteOpenHelper{
        private static final int database_version = 1;

        private String weather_table_create_query = "create table " +
                WeatherTable.TABLE_NAME + "(" +
                WeatherTable.DAYS + " text," +
                WeatherTable.HOUR + " real," +
                WeatherTable.LAT + " real," +
                WeatherTable.LONGT + " real," +
                WeatherTable.CEL + " real," +
                WeatherTable.FAH + " real," +
                WeatherTable.CONDITION + " text," +
                WeatherTable.HUMID + " real," +
                WeatherTable.PRECIP + " real," +
                WeatherTable.WIND + " real," +
                WeatherTable.PER_FAH + " real," +
                WeatherTable.PER_CEL + " real);";

    public DatabaseAdapter(Context context) {
        super(context, "climate", null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(weather_table_create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + WeatherTable.TABLE_NAME);
        onCreate(db);
    }

    //Add
    public boolean add(ContentValues colValues, String table_name) {
        SQLiteDatabase database = this.getWritableDatabase();
        long insertion_result = database.insert(table_name, null, colValues);
        database.close();
        if(insertion_result != -1) {
            return true;
        }
        return false;
    }


    /*Testing/Demoing*/
    public String getCurrentWeather() {
        String result = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + WeatherTable.TABLE_NAME;
        Cursor cursor      = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            result += cursor.getString(cursor.getColumnIndex(WeatherTable.DAYS)) + cursor.getDouble(cursor.getColumnIndex(WeatherTable.FAH));

        }
        cursor.close();
        db.close();

        return result;
    }
    public void loadWeatherData() {
        double fah = 40, cel = 4, humid = 0, precip = 0, wind = 10;
        double lat = 51.50, longt = -0.127;
        int hour = 0;
        String[] conditions = {"Sunny", "Partly Cloudy", "Cloudy"};
        String[] days = {"Mon", "Tue", "Wed", "Thurs", "Fri", "Sat", "Sun"};

        int k = 0;
        for(int i = 0; i < 7; i++) {
            String day = days[i];
            String condition = conditions[k];
            fah = 40;
            cel = 4;
            humid = 0;
            precip = 0;
            wind = 10;
            for(int j = 0; j < 24; j++) {
                hour = i + 1;
                ContentValues colValues = new ContentValues();
                colValues.put(WeatherTable.DAYS, day);
                colValues.put(WeatherTable.HOUR, hour);
                colValues.put(WeatherTable.LAT, lat);
                colValues.put(WeatherTable.LONGT, longt);
                colValues.put(WeatherTable.FAH, fah);
                colValues.put(WeatherTable.CEL, cel);
                colValues.put(WeatherTable.PER_CEL, 0);
                colValues.put(WeatherTable.PER_FAH, 0);
                colValues.put(WeatherTable.HUMID, humid);
                colValues.put(WeatherTable.WIND, wind);
                colValues.put(WeatherTable.PRECIP, precip);
                colValues.put(WeatherTable.CONDITION, condition);

                add(colValues, WeatherTable.TABLE_NAME);
                fah++;
                cel++;
            }

            //for condition index
            k++;
            if(k > 2)
                k = 0;
        }
    }
}
