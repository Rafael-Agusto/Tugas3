package com.example.tugas3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tugas3.PurchaseHistoryItem;
import com.example.tugas3.HistoryAdapter;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView historyRecyclerView;
    private HistoryAdapter historyAdapter;
    private ArrayList<PurchaseHistoryItem> purchaseHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyRecyclerView = findViewById(R.id.historyRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        historyRecyclerView.setLayoutManager(layoutManager);

        purchaseHistoryList = new ArrayList<>();
        PurchaseHistoryDbHelper dbHelper = new PurchaseHistoryDbHelper(this);

        purchaseHistoryList = dbHelper.fetchPurchaseHistory();
        Log.d("DatabaseDebug", "Fetched data: " + purchaseHistoryList.toString());

        historyAdapter = new HistoryAdapter(purchaseHistoryList);
        historyRecyclerView.setAdapter(historyAdapter);
    }


    private void ToMenu() {
        Intent menuIntent = new Intent(HistoryActivity.this, MenuRecyclerView.class);
        startActivity(menuIntent);
        finish(); // Optional: Close the history activity if needed
    }

    private ArrayList<PurchaseHistoryItem> fetchPurchaseHistoryFromDatabase() {
        ArrayList<PurchaseHistoryItem> historyList = new ArrayList<>();
        return historyList;
    }



}
