package com.example.seller_tapnbite_final.settingsContent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.seller_tapnbite_final.R;
import com.google.android.material.textfield.TextInputEditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StoreSetting extends AppCompatActivity {

    private TextInputEditText inputStoreName, inputCanteen, inputManager;
    private Button btnSaveChanges;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_setting); // Replace with your actual XML file name

        // Initialize fields
        inputStoreName = findViewById(R.id.inputStoreName);
        inputCanteen = findViewById(R.id.inputCanteenLocation);
        inputManager = findViewById(R.id.inputManager);
        btnSaveChanges = findViewById(R.id.saveChangesButton);

        // Set default values (fetch from database or shared preferences if needed)
        inputStoreName.setText("ABC Mart");
        inputCanteen.setText("1");
        inputManager.setText("John Doe");

        // Make fields editable when clicked
        enableEditing(inputStoreName);
        enableEditing(inputCanteen);
        enableEditing(inputManager);

        // Save changes when button is clicked
        btnSaveChanges.setOnClickListener(v -> saveChanges());
    }

    private void enableEditing(TextInputEditText editText) {
        editText.setFocusableInTouchMode(true);
        editText.setOnClickListener(v -> editText.setFocusableInTouchMode(true));
    }

    private void saveChanges() {
        String newStoreName = inputStoreName.getText().toString().trim();
        String newCanteen = inputCanteen.getText().toString().trim();
        String newManager = inputManager.getText().toString().trim();

        // TODO: Save the updated values to database or shared preferences
        // For now, just show a confirmation
        btnSaveChanges.setText("Changes Saved!");
    }
}
