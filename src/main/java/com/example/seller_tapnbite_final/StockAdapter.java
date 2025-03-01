package com.example.seller_tapnbite_final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {
    private List<StockMenu.InventoryItem> itemList;

    public StockAdapter(List<StockMenu.InventoryItem> itemList) {
        this.itemList = new ArrayList<>(itemList); // Prevent modifying original list
    }

    public void updateList(List<StockMenu.InventoryItem> newList) {
        itemList.clear();
        itemList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StockMenu.InventoryItem item = itemList.get(position);
        holder.itemName.setText(item.name);
        holder.category.setText(item.category);
        holder.quantity.setText(String.valueOf(item.quantity));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, category, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            category = itemView.findViewById(R.id.category);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }
}
