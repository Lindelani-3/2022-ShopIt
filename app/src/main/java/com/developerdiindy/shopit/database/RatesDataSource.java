package com.developerdiindy.shopit.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.developerdiindy.shopit.business.models.Rate;

public class RatesDataSource {
    // Database fields
    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDbHelper;
    private String[] mAllColumns = {DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_COIN, DatabaseHelper.COLUMN_VALUE};

    public RatesDataSource(Context context) {
        mDbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Rate createRate(String coin, double value) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_COIN, coin);
        values.put(DatabaseHelper.COLUMN_VALUE, value);
        long insertId = mDatabase.insert(DatabaseHelper.TABLE_RATE, null,
                values);
        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_RATE,
                mAllColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Rate newComment = cursorToRate(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteRate(Rate comment) {
        long id = comment.getId();
        System.out.println("Rate deleted with id: " + id);
        mDatabase.delete(DatabaseHelper.TABLE_RATE, DatabaseHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Rate> getAllRates() {
        List<Rate> rates = new ArrayList<Rate>();

        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_RATE,
                mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Rate rate = cursorToRate(cursor);
            rates.add(rate);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return rates;
    }

    private Rate cursorToRate(Cursor cursor) {
        Rate rate = new Rate();
        rate.setId(cursor.getLong(0));
        rate.setCoin(cursor.getString(1));
        rate.setValue(cursor.getDouble(2));
        return rate;
    }

    public void deleteAll() {

        mDatabase.delete(DatabaseHelper.TABLE_RATE, null, null);
    }
}
