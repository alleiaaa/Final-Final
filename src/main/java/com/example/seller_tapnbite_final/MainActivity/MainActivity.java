package com.example.seller_tapnbite_final.MainActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.seller_tapnbite_final.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.example.seller_tapnbite_final.DashboardFragments.OrderMain;
import com.example.seller_tapnbite_final.DashboardFragments.InventoryMain;
import com.example.seller_tapnbite_final.DashboardFragments.SettingMain;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.primary));

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigations);
        bottomNav.setSelectedItemId(R.id.nav_sales);
        bottomNav.setOnItemSelectedListener(navListener);

        Fragment selectedFragment = new OrderMain();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();

    }

    private NavigationBarView.OnItemSelectedListener navListener =
            item -> {
                int itemId = item.getItemId();
                Fragment selectedFragment = null;

                if (itemId == R.id.nav_inventory) {
                    selectedFragment = new InventoryMain();
                } else if (itemId == R.id.nav_sales) {
                    selectedFragment = new OrderMain();
                } else if (itemId == R.id.nav_settings) {
                    // Handle the profile case
                    selectedFragment = new SettingMain();

                } else {
                    selectedFragment = new InventoryMain();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            };

}