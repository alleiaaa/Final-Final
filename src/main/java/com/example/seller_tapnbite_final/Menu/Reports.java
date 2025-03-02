package com.example.seller_tapnbite_final.Menu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.seller_tapnbite_final.R;

import java.util.ArrayList;
import java.util.List;

public class Reports extends AppCompatActivity {

    private Spinner spinnerCanteen;
    private TextView txtTotalRiceMeal, txtTotalDrinks, txtTotalSnacks;
    private List<StockMenu.InventoryItem> stockList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        // Initialize UI elements
        spinnerCanteen = findViewById(R.id.spinnerCanteen);
        txtTotalRiceMeal = findViewById(R.id.txtTotalRiceMeal);
        txtTotalDrinks = findViewById(R.id.txtTotalDrinks);
        txtTotalSnacks = findViewById(R.id.txtTotalSnacks);

        // Load stock data
        stockList = StockMenu.getStockList(); // Retrieve inventory data

        // Update inventory summary
        updateInventorySummary();

        // Setup Spinner
        List<String> canteens = new ArrayList<>();
        canteens.add("Select Canteen");
        canteens.add("Canteen 1");
        canteens.add("Canteen 2");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, canteens);
        spinnerCanteen.setAdapter(adapter);

        spinnerCanteen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCanteen = parent.getItemAtPosition(position).toString();
                if (!selectedCanteen.equals("Select Canteen")) {
                    Toast.makeText(Reports.this, "Selected: " + selectedCanteen, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void updateInventorySummary() {
        int riceMealCount = 0, drinksCount = 0, snacksCount = 0;

        if (stockList != null) {
            for (StockMenu.InventoryItem item : stockList) {
                switch (item.category) {
                    case "Rice Meal":
                        riceMealCount += item.quantity;
                        break;
                    case "Drink":
                        drinksCount += item.quantity;
                        break;
                    case "Snack":
                        snacksCount += item.quantity;
                        break;
                }
            }
        }

        txtTotalRiceMeal.setText(String.valueOf(riceMealCount));
        txtTotalDrinks.setText(String.valueOf(drinksCount));
        txtTotalSnacks.setText(String.valueOf(snacksCount));
    }
}
