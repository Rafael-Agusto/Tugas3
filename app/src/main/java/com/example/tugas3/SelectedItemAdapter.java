package com.example.tugas3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectedItemAdapter extends RecyclerView.Adapter<SelectedItemAdapter.SelectedItemViewHolder> {

    private ArrayList<SelectedMenuItem> selectedItemsList;

    public SelectedItemAdapter(ArrayList<SelectedMenuItem> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    @NonNull
    @Override
    public SelectedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_item_layout, parent, false);
        return new SelectedItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedItemViewHolder holder, int position) {
        SelectedMenuItem selectedItem = selectedItemsList.get(position);
        holder.selectedFoodNameTextView.setText(selectedItem.getName());
        holder.selectedFoodPriceTextView.setText("Price: " + selectedItem.getPrice());
        holder.selectedFoodCounterTextView.setText("Qty : " + selectedItem.getCounter());
        holder.selectedTotalPriceTextView.setText("Total : " + (selectedItem.getPrice() * selectedItem.getCounter()));
    }

    @Override
    public int getItemCount() {
        return selectedItemsList.size();
    }

    public static class SelectedItemViewHolder extends RecyclerView.ViewHolder {
        TextView selectedFoodNameTextView;
        TextView selectedFoodPriceTextView;
        TextView selectedFoodCounterTextView;
        TextView selectedTotalPriceTextView;

        public SelectedItemViewHolder(@NonNull View itemView) {
            super(itemView);
            selectedFoodNameTextView = itemView.findViewById(R.id.selectedFoodNameTextView);
            selectedFoodPriceTextView = itemView.findViewById(R.id.selectedFoodPriceTextView);
            selectedFoodCounterTextView = itemView.findViewById(R.id.selectedFoodCounterTextView);
            selectedTotalPriceTextView = itemView.findViewById(R.id.selectedTotalPriceTextView);
        }
    }
}
