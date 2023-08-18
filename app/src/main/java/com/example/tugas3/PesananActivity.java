package com.example.tugas3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PesananActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_item_layout); // Use your layout name here

        Intent intent = getIntent();
        if (intent != null) {
            String foodName = intent.getStringExtra("food_name");
            int foodPrice = intent.getIntExtra("food_price", 0);
            int quantity = intent.getIntExtra("quantity", 0);

            TextView foodNameTextView = findViewById(R.id.selectedFoodNameTextView);
            TextView priceTextView = findViewById(R.id.selectedFoodPriceTextView);
            TextView quantityTextView = findViewById(R.id.selectedFoodCounterTextView);
            TextView totalPriceTextView = findViewById(R.id.selectedTotalPriceTextView); // Find the total price view

            foodNameTextView.setText("Food Name: " + foodName);
            priceTextView.setText("Price: " + getString(R.string.price_format, foodPrice));
            quantityTextView.setText("Quantity: " + quantity);

            int totalPrice = foodPrice * quantity;
            totalPriceTextView.setText("Total " + getString(R.string.price_format, totalPrice));
        }
    }
}
