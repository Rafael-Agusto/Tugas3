package com.example.tugas3;

import com.example.tugas3.PurchaseHistoryContract;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.widget.RadioGroup;
import android.widget.RadioButton;

public class BayarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SelectedItemAdapter selectedItemAdapter;
    private TextView totalTextView;

    private ArrayList<SelectedMenuItem> selectedItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_pesanan);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.selectedItemsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize totalTextView
        totalTextView = findViewById(R.id.totalTextView);

        // Initialize ArrayList to hold selected items
        selectedItemsList = new ArrayList<>();

        // Get arrays of data from intent
        Intent intent = getIntent();
        if (intent != null) {
            String[] foodNames = intent.getStringArrayExtra("food_names");
            int[] foodPrices = intent.getIntArrayExtra("food_prices");
            int[] foodCounters = intent.getIntArrayExtra("food_counters");

            for (int i = 0; i < foodNames.length; i++) {
                selectedItemsList.add(new SelectedMenuItem(foodNames[i], foodPrices[i], foodCounters[i]));
            }

            // Calculate and display the total price
            int totalPrice = calculateTotalPrice();
            totalTextView.setText("Total: $" + totalPrice);
        }

        // Initialize and set the adapter for the selected items
        selectedItemAdapter = new SelectedItemAdapter(selectedItemsList);
        recyclerView.setAdapter(selectedItemAdapter); // Set the adapter here

        // Calculate and display the total price
        int totalPrice = calculateTotalPrice();
        totalTextView.setText("Total: $" + totalPrice);

        // Initialize and set click listener for saveButton
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalCounter = calculateTotalCounter();
                int totalPrice = calculateTotalPrice();
                String paymentMethod = getSelectedPaymentMethod();
                String currentDate = getCurrentDate();

                savePurchaseHistory(totalCounter, totalPrice, paymentMethod, currentDate);

                Intent menuIntent = new Intent(BayarActivity.this,Menu.class);
                startActivity(menuIntent);

                finish();
            }
        });

    }
    private String getSelectedPaymentMethod() {
        RadioGroup paymentRadioGroup = findViewById(R.id.paymentRadioGroup);
        int selectedId = paymentRadioGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            return selectedRadioButton.getText().toString();
        }

        return ""; // Default value if no radio button is selected
    }

    private int calculateTotalPrice() {
        int totalPrice = 0;
        for (SelectedMenuItem item : selectedItemsList) {
            totalPrice += (item.getPrice() * item.getCounter());
        }
        return totalPrice;
    }

    private void savePurchaseHistory(int totalCounter, int totalPrice, String paymentMethod, String currentDate) {
        PurchaseHistoryDbHelper dbHelper = new PurchaseHistoryDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        // Add data to the ContentValues, e.g., food names, prices, counters, etc.

        // Add the values for totalCounter, totalPrice, paymentMethod, and currentDate to the ContentValues
        values.put(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_TOTAL_COUNTER, totalCounter);
        values.put(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_TOTAL_PRICE, totalPrice);
        values.put(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_PAYMENT_METHOD, paymentMethod);
        values.put(PurchaseHistoryContract.PurchaseHistoryEntry.COLUMN_DATE, currentDate);

        long newRowId = db.insert(PurchaseHistoryContract.PurchaseHistoryEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Purchase history saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save purchase history", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    private int calculateTotalCounter() {
        int totalCounter = 0;
        for (SelectedMenuItem item : selectedItemsList) {
            totalCounter += item.getCounter();
        }
        return totalCounter;
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }
}
