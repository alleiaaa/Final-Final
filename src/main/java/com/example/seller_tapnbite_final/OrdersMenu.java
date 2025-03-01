package com.example.seller_tapnbite_final;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;

public class OrdersMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_menu);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        getWindow().setStatusBarColor(ContextCompat.getColor(OrdersMenu.this, R.color.primary));
    }
}
