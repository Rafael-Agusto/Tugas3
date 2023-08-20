package com.example.tugas3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MenuRecyclerView extends AppCompatActivity implements MenuAdapter.OnItemClickListener {

    RecyclerView recMenu;
    ArrayList<Menu> listMenu;
    TextView totalItemCountTextView;
    TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        recMenu = findViewById(R.id.recMenu);
        totalItemCountTextView = findViewById(R.id.totalItemCount);
        totalPriceTextView = findViewById(R.id.totalPrice);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recMenu.setLayoutManager(layoutManager);

        generateMenu();  // Add your menu items here

        MenuAdapter menuAdapter = new MenuAdapter(listMenu);
        menuAdapter.setOnItemClickListener(this);
        recMenu.setAdapter(menuAdapter);

        Button bayarButton = findViewById(R.id.bayar);
        bayarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace 0 with the appropriate position of the selected item
                toPay();
            }
        });

        updateTotalValues();
    }

    private void generateMenu() {
        listMenu = new ArrayList<>();
        listMenu.add(new Menu("Pastel Tutup", 150000, "So-on dicampur dengan wortel, daging, jamur, lalu ditutup dengan mashed potato", R.drawable.pastel, 0));
        listMenu.add(new Menu("Macaroni Schotel", 150000, "Macaroni dimasak dengan susu dan keju, lalu dicampur dengan wortel, daging, dan sosis", R.drawable.macaroni, 0));
        listMenu.add(new Menu("Mie Frozen", 25000, "Mie ayam kecap dengan pangsit dan baksi frozen, yang dapat di masak sendiri dengan mudah", R.drawable.mie, 0));
        listMenu.add(new Menu("Misoa", 12000, "Misoa yang lembut dengan toping ayam, telur, dan ditaburi dengan daun bawang serta brambang goreng", R.drawable.misoa, 0));
        listMenu.add(new Menu("Asinan Bogor", 15000, "Berbagai macam sayur dan buah direndam dalam kuah dengan cita rasa manis, asam, yang disajikan bersama krupuk dan kacang.", R.drawable.asinan, 0));
        listMenu.add(new Menu("Mie Frozen", 25000, "Mie ayam kecap dengan pangsit dan baksi frozen, yang dapat di masak sendiri dengan mudah", R.drawable.mie, 0));
    }

    public void toPay() {
        ArrayList<Menu> selectedItems = new ArrayList<>();

        for (Menu menu : listMenu) {
            if (menu.getQuantity() > 0) {
                selectedItems.add(menu);
            }
        }

        if (!selectedItems.isEmpty()) {
            Intent intent = new Intent(this, BayarActivity.class);

            // Create arrays to hold the data
            String[] foodNames = new String[selectedItems.size()];
            int[] foodPrices = new int[selectedItems.size()];
            int[] foodCounters = new int[selectedItems.size()];

            // Fill the arrays with data from selectedItems
            for (int i = 0; i < selectedItems.size(); i++) {
                Menu item = selectedItems.get(i);
                foodNames[i] = item.getName();
                foodPrices[i] = item.getPrice();
                foodCounters[i] = item.getQuantity();
            }

            // Put arrays as extras in the intent
            intent.putExtra("food_names", foodNames);
            intent.putExtra("food_prices", foodPrices);
            intent.putExtra("food_counters", foodCounters);

            startActivity(intent);
        }
    }


    @Override
    public void onItemClick(int position) {
        // Implement the onItemClick method as needed
    }

    @Override
    public void onCounterChanged() {
        updateTotalValues();
    }

    private void updateTotalValues() {
        int totalItemCount = 0;
        int totalPrice = 0;

        for (Menu menu : listMenu) {
            totalItemCount += menu.getQuantity();
            totalPrice += menu.getTotalPrice();
        }

        totalItemCountTextView.setText("Total Items: " + totalItemCount);
        totalPriceTextView.setText("Total Price: $" + totalPrice);
    }

    public void openHistory(View view) {
        Intent historyIntent = new Intent(MenuRecyclerView.this, HistoryActivity.class);
        startActivity(historyIntent);
    }
}
