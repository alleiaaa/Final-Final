package com.example.seller_tapnbite_final.Menu;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.seller_tapnbite_final.R;
import java.util.List;

public class Reports extends AppCompatActivity {

    // Sales Summary
    private TextView txtTotalSales, txtTodaySales, txtWeekSales, txtMonthSales;
    private ImageView imgSalesTrend;

    // Inventory Summary
    private TextView txtTotalRiceMeal, txtTotalDrinks, txtTotalSnacks;

    // Waste Summary
    private TextView txtTotalWaste, txtFoodWaste, txtPackagingWaste;
    private ImageView imgWasteTrend;

    private List<StockMenu.InventoryItem> stockList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        // Initialize UI elements
        initializeViews();

        // Load stock data
        stockList = StockMenu.getStockList(); // Retrieve inventory data

        // Update inventory summary
        updateInventorySummary();

        // Example sales data (replace with actual data)
        double currentSales = 50000; // Example current sales
        double previousSales = 45000; // Example previous sales

        // Update sales trend icon
        updateSalesTrendIcon(currentSales, previousSales);

        // Example waste data (replace with actual data)
        double currentWaste = 120; // Example current waste in kg
        double previousWaste = 100; // Example previous waste in kg

        // Update waste trend icon
        updateWasteTrendIcon(currentWaste, previousWaste);

        // Update waste summary
        updateWasteSummary(currentWaste, 80, 40); // Example: total waste, food waste, packaging waste
    }

    private void initializeViews() {
        // Sales Summary
        txtTotalSales = findViewById(R.id.txtTotalSales);
        txtTodaySales = findViewById(R.id.txtTodaySales);
        txtWeekSales = findViewById(R.id.txtWeekSales);
        txtMonthSales = findViewById(R.id.txtMonthSales);
        imgSalesTrend = findViewById(R.id.imgSalesTrend);

        // Inventory Summary
        txtTotalRiceMeal = findViewById(R.id.txtTotalRiceMeal);
        txtTotalDrinks = findViewById(R.id.txtTotalDrinks);
        txtTotalSnacks = findViewById(R.id.txtTotalSnacks);

        // Waste Summary
        txtTotalWaste = findViewById(R.id.txtTotalWaste);
        txtFoodWaste = findViewById(R.id.txtFoodWaste);
        txtPackagingWaste = findViewById(R.id.txtPackagingWaste);
        imgWasteTrend = findViewById(R.id.imgWasteTrend);
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

    private void updateSalesTrendIcon(double currentSales, double previousSales) {
        if (currentSales > previousSales) {
            // Sales are up
            imgSalesTrend.setImageResource(R.drawable.ic_trend_up);
        } else if (currentSales < previousSales) {
            // Sales are down
            imgSalesTrend.setImageResource(R.drawable.ic_trend_down);
        } else {
            // Sales are unchanged (optional: you can add a neutral icon if needed)
            imgSalesTrend.setImageResource(R.drawable.ic_trend_up); // Default to up or use a neutral icon
        }
    }

    private void updateWasteTrendIcon(double currentWaste, double previousWaste) {
        if (currentWaste > previousWaste) {
            // Waste is up
            imgWasteTrend.setImageResource(R.drawable.ic_trend_up);
        } else if (currentWaste < previousWaste) {
            // Waste is down
            imgWasteTrend.setImageResource(R.drawable.ic_trend_down);
        } else {
            // Waste is unchanged (optional: you can add a neutral icon if needed)
            imgWasteTrend.setImageResource(R.drawable.ic_trend_up); // Default to up or use a neutral icon
        }
    }

    private void updateWasteSummary(double totalWaste, double foodWaste, double packagingWaste) {
        // Update waste summary text views
        txtTotalWaste.setText(String.format("%.1f kg", totalWaste));
        txtFoodWaste.setText(String.format("%.1f kg", foodWaste));
        txtPackagingWaste.setText(String.format("%.1f kg", packagingWaste));
    }
}