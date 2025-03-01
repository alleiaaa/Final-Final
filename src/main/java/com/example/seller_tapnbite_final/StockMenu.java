package com.example.seller_tapnbite_final;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class StockMenu extends AppCompatActivity {

    private TextView tabStock, tabWaste;
    private RecyclerView recyclerInventory;
    private StockAdapter stockAdapter;
    private List<InventoryItem> stockList = new ArrayList<>();
    private List<InventoryItem> wasteList = new ArrayList<>();
    private boolean isStockTabSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_menu);

        tabStock = findViewById(R.id.tabStock);
        tabWaste = findViewById(R.id.tabWaste);
        recyclerInventory = findViewById(R.id.recyclerInventory);

        recyclerInventory.setLayoutManager(new LinearLayoutManager(this));
        stockAdapter = new StockAdapter(stockList);
        recyclerInventory.setAdapter(stockAdapter);

        // Sample Data
        populateSampleData();

        // Tab Click Listeners
        tabStock.setOnClickListener(view -> switchTab(true));
        tabWaste.setOnClickListener(view -> switchTab(false));
    }

    private void switchTab(boolean isStock) {
        isStockTabSelected = isStock;

        tabStock.setBackgroundColor(getResources().getColor(isStock ? R.color.primary : android.R.color.white));
        tabStock.setTextColor(getResources().getColor(isStock ? android.R.color.white : R.color.dark));

        tabWaste.setBackgroundColor(getResources().getColor(isStock ? android.R.color.white : R.color.primary));
        tabWaste.setTextColor(getResources().getColor(isStock ? R.color.dark : android.R.color.white));

        // Update RecyclerView data
        stockAdapter.updateList(isStock ? stockList : wasteList);
    }

    private void populateSampleData() {
        stockList.add(new InventoryItem("Fried Rice", "Rice Meal", 20));
        stockList.add(new InventoryItem("Iced Tea", "Drink", 15));
        stockList.add(new InventoryItem("Cookies", "Snack", 30));

        wasteList.add(new InventoryItem("Burnt Rice", "Rice Meal", 5));
        wasteList.add(new InventoryItem("Expired Soda", "Drink", 10));
    }

    // Static InventoryItem Class
    static class InventoryItem {
        String name, category;
        int quantity;

        public InventoryItem(String name, String category, int quantity) {
            this.name = name;
            this.category = category;
            this.quantity = quantity;
        }
    }

    // RecyclerView Adapter
    static class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {
        private List<InventoryItem> itemList;

        public StockAdapter(List<InventoryItem> itemList) {
            this.itemList = new ArrayList<>(itemList); // Prevent modifying original list
        }

        public void updateList(List<InventoryItem> newList) {
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
            InventoryItem item = itemList.get(position);
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
}
