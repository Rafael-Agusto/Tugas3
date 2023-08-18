package com.example.tugas3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.view.View;

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

        generateMenu();

        MenuAdapter menuAdapter = new MenuAdapter(listMenu);
        menuAdapter.setOnItemClickListener(this);
        recMenu.setAdapter(menuAdapter);

        updateTotalValues(); // Update the initial total values
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

    @Override
    public void onItemClick(int position) {
        if (position >= 0 && position < listMenu.size()) {
            Menu selectedItem = listMenu.get(position);
            selectedItem.counter++;
            updateTotalValues();
            toPay(position);
        }
    }

    public void toPay(int position) {
        Menu selectedItem = listMenu.get(position);

        if (selectedItem.getQuantity() > 0) {
            Intent intent = new Intent(this, BayarActivity.class);
            intent.putExtra("food_name", selectedItem.getName());
            intent.putExtra("food_price", selectedItem.getPrice());
            intent.putExtra("food_counter", selectedItem.getQuantity());
            startActivity(intent);
        }
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
}
