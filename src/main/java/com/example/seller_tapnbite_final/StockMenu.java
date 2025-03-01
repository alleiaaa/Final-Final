package com.example.seller_tapnbite_final;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
        ImageButton addItemButton = findViewById(R.id.addItem);

        recyclerInventory.setLayoutManager(new LinearLayoutManager(this));

        // Load Sample Data BEFORE setting the adapter
        populateSampleData();

        stockAdapter = new StockAdapter(stockList);
        recyclerInventory.setAdapter(stockAdapter);

        // Ensure the correct tab is shown at launch
        switchTab(true);

        // Tab Click Listeners
        tabStock.setOnClickListener(view -> switchTab(true));
        tabWaste.setOnClickListener(view -> switchTab(false));

        // Add Item Button Click
        addItemButton.setOnClickListener(view -> showAddItemDialog());
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

    private void showAddItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_item, null);
        builder.setView(dialogView);

        EditText itemNameInput = dialogView.findViewById(R.id.itemNameInput);
        Spinner categorySpinner = dialogView.findViewById(R.id.categorySpinner);
        EditText quantityInput = dialogView.findViewById(R.id.quantityInput);
        RadioGroup radioGroupTab = dialogView.findViewById(R.id.radioGroupTab);
        Button addButton = dialogView.findViewById(R.id.addButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);

        AlertDialog dialog = builder.create();

        addButton.setOnClickListener(view -> {
            String name = itemNameInput.getText().toString();
            String category = categorySpinner.getSelectedItem().toString();
            int quantity = Integer.parseInt(quantityInput.getText().toString());

            if (radioGroupTab.getCheckedRadioButtonId() == R.id.radioStock) {
                stockList.add(new InventoryItem(name, category, quantity));
            } else {
                wasteList.add(new InventoryItem(name, category, quantity));
            }

            stockAdapter.notifyDataSetChanged();
            dialog.dismiss();
        });

        cancelButton.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
    }


    // Inventory Item Class
    static class InventoryItem {
        String name, category;
        int quantity;

        public InventoryItem(String name, String category, int quantity) {
            this.name = name;
            this.category = category;
            this.quantity = quantity;
        }
    }
}
