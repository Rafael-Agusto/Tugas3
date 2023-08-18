package com.example.tugas3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private ArrayList<Menu> menuList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onCounterChanged();
    }

    public MenuAdapter(ArrayList<Menu> menuList) {
        this.menuList = menuList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menu = menuList.get(position);

        holder.menuNameTextView.setText(menu.getName());
        holder.menuDescriptionTextView.setText(menu.getDescription());
        holder.menuPriceTextView.setText(String.valueOf(menu.getPrice()));
        holder.menuImageView.setImageResource(menu.getImageResource());

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.counter++;
                menu.setTotalPrice(menu.getPrice() * menu.counter);
                holder.textViewQuantity.setText(String.valueOf(menu.counter));
                holder.textViewTotalPrice.setText("Total: $" + menu.getTotalPrice());

                if (onItemClickListener != null) {
                    onItemClickListener.onCounterChanged(); // Notify activity for counter change
                }
            }
        });

        holder.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menu.counter > 0) {
                    menu.counter--;
                    menu.setTotalPrice(menu.getPrice() * menu.counter);
                    holder.textViewQuantity.setText(String.valueOf(menu.counter));
                    holder.textViewTotalPrice.setText("Total: $" + menu.getTotalPrice());

                    if (onItemClickListener != null) {
                        onItemClickListener.onCounterChanged(); // Notify activity for counter change
                    }
                }
            }
        });

        holder.textViewQuantity.setText(String.valueOf(menu.counter));
        holder.textViewTotalPrice.setText("Total: $" + menu.getTotalPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (onItemClickListener != null && clickedPosition != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(clickedPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView menuImageView;
        TextView menuNameTextView;
        TextView menuDescriptionTextView;
        TextView menuPriceTextView;
        Button buttonAdd;
        Button buttonSubtract;
        TextView textViewQuantity;
        TextView textViewTotalPrice;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            menuImageView = itemView.findViewById(R.id.imageViewFood);
            menuNameTextView = itemView.findViewById(R.id.textViewName);
            menuDescriptionTextView = itemView.findViewById(R.id.textViewDescription);
            menuPriceTextView = itemView.findViewById(R.id.textViewPrice);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);
            buttonSubtract = itemView.findViewById(R.id.buttonSubtract);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewTotalPrice = itemView.findViewById(R.id.textViewTotalPrice);
        }
    }
}
