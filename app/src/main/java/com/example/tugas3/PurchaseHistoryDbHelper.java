package com.example.tugas3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class PurchaseHistoryDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "purchase_history.db";
    private static final int DATABASE_VERSION = 1;

    public PurchaseHistoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Define your table creation SQL statement here
        String createTableQuery = "CREATE TABLE " +
                PurchaseHistoryContract.PurchaseHistoryEntry.TABLE_NAME + " (" +
                PurchaseHistoryContract.PurchaseHistoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_TOTAL_COUNTER + " INTEGER, " +
                PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_TOTAL_PRICE + " REAL, " +
                PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_PAYMENT_METHOD + " TEXT, " +
                PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_DATE + " TEXT);";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema upgrades here if needed
    }

    public long insertPurchaseHistory(int totalCounter, double totalPrice, String paymentMethod, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_TOTAL_COUNTER, totalCounter);
        values.put(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_TOTAL_PRICE, totalPrice);
        values.put(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_PAYMENT_METHOD, paymentMethod);
        values.put(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_DATE, date);
        return db.insert(PurchaseHistoryContract.PurchaseHistoryEntry.TABLE_NAME, null, values);
    }

    public ArrayList<PurchaseHistoryItem> fetchPurchaseHistory() {
        ArrayList<PurchaseHistoryItem> historyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                PurchaseHistoryContract.PurchaseHistoryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int totalCounter = cursor.getInt(cursor.getColumnIndex(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_TOTAL_COUNTER));
                double totalPrice = cursor.getDouble(cursor.getColumnIndex(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_TOTAL_PRICE));
                String paymentMethod = cursor.getString(cursor.getColumnIndex(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_PAYMENT_METHOD));
                String date = cursor.getString(cursor.getColumnIndex(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_DATE));

                PurchaseHistoryItem historyItem = new PurchaseHistoryItem(totalCounter, totalPrice, paymentMethod, date);
                historyList.add(historyItem);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return historyList;
    }
}
