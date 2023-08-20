package com.example.tugas3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<PurchaseHistoryItem> purchaseHistoryList;

    public HistoryAdapter(ArrayList<PurchaseHistoryItem> purchaseHistoryList) {
        this.purchaseHistoryList = purchaseHistoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PurchaseHistoryItem historyItem = purchaseHistoryList.get(position);

        Log.d("AdapterDebug", "Binding data: " + historyItem.toString());

        holder.historyTotalQuantityTextView.setText(String.valueOf(historyItem.getTotalCounter()));
        holder.historyTotalPriceTextView.setText(String.valueOf(historyItem.getTotalPrice()));
        holder.historyPaymentMethodTextView.setText(historyItem.getPaymentMethod());
        holder.historyDateTextView.setText(historyItem.getDate());
    }

    @Override
    public int getItemCount() {
        return purchaseHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView historyTotalPriceTextView;
        TextView historyTotalQuantityTextView;
        TextView historyDateTextView;
        TextView historyPaymentMethodTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views using itemView.findViewById()
            historyTotalPriceTextView = itemView.findViewById(R.id.historyTotalPriceTextView);
            historyTotalQuantityTextView = itemView.findViewById(R.id.historyTotalQuantityTextView);
            historyDateTextView = itemView.findViewById(R.id.historyDateTextView);
            historyPaymentMethodTextView = itemView.findViewById(R.id.historyPaymentMethodTextView);
        }
    }
}
