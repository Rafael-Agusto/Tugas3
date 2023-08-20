package com.example.tugas3;

import com.example.tugas3.PurchaseHistoryItem;
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

        holder.totalCounterTextView.setText(String.valueOf(historyItem.getTotalCounter()));
        holder.totalPriceTextView.setText(String.valueOf(historyItem.getTotalPrice()));
        holder.paymentMethodTextView.setText(historyItem.getPaymentMethod());
        holder.dateTextView.setText(historyItem.getDate());
    }

    @Override
    public int getItemCount() {
        return purchaseHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView totalCounterTextView;
        TextView totalPriceTextView;
        TextView paymentMethodTextView;
        TextView dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views using itemView.findViewById()
            totalCounterTextView = itemView.findViewById(R.id.totalCounterTextView);
            totalPriceTextView = itemView.findViewById(R.id.totalPriceTextView);
            paymentMethodTextView = itemView.findViewById(R.id.paymentMethodTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}