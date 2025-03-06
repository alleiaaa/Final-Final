package com.example.seller_tapnbite_final.settingsContent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.seller_tapnbite_final.R;
import com.google.android.material.textfield.TextInputEditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StoreSetting extends AppCompatActivity {

    private TextInputEditText inputStoreName, inputCanteen, inputManager;
    private Button btnSaveChanges;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_setting);

        sharedPreferences = getSharedPreferences("StorePrefs", MODE_PRIVATE);

        inputStoreName = findViewById(R.id.inputStoreName);
        inputCanteen = findViewById(R.id.inputCanteenLocation);
        inputManager = findViewById(R.id.inputManager);
        btnSaveChanges = findViewById(R.id.saveChangesButton);

        loadSavedData();

        enableEditing(inputStoreName);
        enableEditing(inputCanteen);
        enableEditing(inputManager);

        btnSaveChanges.setOnClickListener(v -> saveChanges());

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    private void enableEditing(TextInputEditText editText) {
        editText.setFocusableInTouchMode(true);
    }

    private void saveChanges() {
        String newStoreName = inputStoreName.getText().toString().trim();
        String newCanteen = inputCanteen.getText().toString().trim();
        String newManager = inputManager.getText().toString().trim();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("store_name", newStoreName);
        editor.putString("canteen_location", newCanteen);
        editor.putString("manager_name", newManager);
        editor.apply();

        btnSaveChanges.setText("Changes Saved!");
    }

    private void loadSavedData() {
        String storeName = sharedPreferences.getString("store_name", "ABC Mart");
        String canteenLocation = sharedPreferences.getString("canteen_location", "1");
        String managerName = sharedPreferences.getString("manager_name", "John Doe");

        inputStoreName.setText(storeName);
        inputCanteen.setText(canteenLocation);
        inputManager.setText(managerName);
    }
}
