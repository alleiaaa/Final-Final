package com.example.seller_tapnbite_final;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Reports extends AppCompatActivity {

    private Spinner spinnerCanteen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports); // Ensure this is the correct layout

        // Initialize Spinner
        spinnerCanteen = findViewById(R.id.spinnerCanteen);

        // Create a list of canteen numbers
        List<String> canteens = new ArrayList<>();
        canteens.add("Select Canteen");
        canteens.add("Canteen 1");
        canteens.add("Canteen 2");
        canteens.add("Canteen 3");

        // Set up ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, canteens);
        spinnerCanteen.setAdapter(adapter);

        // Handle item selection
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
}
