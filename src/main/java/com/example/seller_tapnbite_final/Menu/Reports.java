package com.example.seller_tapnbite_final.Menu;

import android.os.Bundle;
import android.widget.ImageButton;
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

        ImageButton backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(v -> finish());

        initializeViews();

        stockList = StockMenu.getStockList();


        updateInventorySummary();

        double currentSales = 50000;
        double previousSales = 45000;

        updateSalesTrendIcon(currentSales, previousSales);


        double currentWaste = 120;
        double previousWaste = 100;

        updateWasteTrendIcon(currentWaste, previousWaste);

        updateWasteSummary(currentWaste, 80, 40);
    }

    private void initializeViews() {

        txtTotalSales = findViewById(R.id.txtTotalSales);
        txtTodaySales = findViewById(R.id.txtTodaySales);
        imgSalesTrend = findViewById(R.id.imgSalesTrend);

        txtTotalRiceMeal = findViewById(R.id.txtTotalRiceMeal);
        txtTotalDrinks = findViewById(R.id.txtTotalDrinks);
        txtTotalSnacks = findViewById(R.id.txtTotalSnacks);

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
            imgSalesTrend.setImageResource(R.drawable.ic_trend_up);
        } else if (currentSales < previousSales) {
            imgSalesTrend.setImageResource(R.drawable.ic_trend_down);
        } else {
            imgSalesTrend.setImageResource(R.drawable.ic_trend_up);
        }
    }

    private void updateWasteTrendIcon(double currentWaste, double previousWaste) {
        if (currentWaste > previousWaste) {

            imgWasteTrend.setImageResource(R.drawable.ic_trend_up);
        } else if (currentWaste < previousWaste) {

            imgWasteTrend.setImageResource(R.drawable.ic_trend_down);
        } else {

            imgWasteTrend.setImageResource(R.drawable.ic_trend_up);
        }
    }

    private void updateWasteSummary(double totalWaste, double foodWaste, double packagingWaste) {

        txtTotalWaste.setText(String.format("%.1f kg", totalWaste));
        txtFoodWaste.setText(String.format("%.1f kg", foodWaste));
        txtPackagingWaste.setText(String.format("%.1f kg", packagingWaste));
    }
}