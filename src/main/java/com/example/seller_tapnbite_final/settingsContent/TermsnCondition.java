package com.example.seller_tapnbite_final.settingsContent;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.seller_tapnbite_final.R;

public class TermsnCondition extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsn_condition);

        ImageButton btnBack = findViewById(R.id.btnBack);

        // Back button click listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes the activity and goes back
            }
        });
    }
}
