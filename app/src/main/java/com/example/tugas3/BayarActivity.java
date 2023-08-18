package com.example.tugas3;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tugas3.R;
import java.util.ArrayList;

public class BayarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SelectedItemAdapter selectedItemAdapter;

    private ArrayList<SelectedMenuItem> selectedItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_pesanan);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.selectedItemsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize ArrayList to hold selected items
        selectedItemsList = new ArrayList<>();

        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            String foodName = intent.getStringExtra("food_name");
            int foodPrice = intent.getIntExtra("food_price", 0);
            int foodCounter = intent.getIntExtra("food_counter", 0);

            // Add selected item to the list
            selectedItemsList.add(new SelectedMenuItem(foodName, foodPrice, foodCounter));

            // Initialize and set the adapter for the selected items
            selectedItemAdapter = new SelectedItemAdapter(selectedItemsList);
            recyclerView.setAdapter(selectedItemAdapter);
        }
    }
}
