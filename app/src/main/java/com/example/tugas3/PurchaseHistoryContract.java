package com.example.tugas3;

import android.provider.BaseColumns;

public class PurchaseHistoryContract {
    private PurchaseHistoryContract() {
        // Private constructor to prevent instantiation
    }

    public static class PurchaseHistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "purchase_history";
        public static final String COLUMN_TOTAL_COUNTER = "total_counter";
        public static final String COLUMN_TOTAL_PRICE = "total_price";
        public static final String COLUMN_PAYMENT_METHOD = "payment_method";
        public static final String COLUMN_DATE = "date";
    }
}
