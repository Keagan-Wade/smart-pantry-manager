package com.keaganwade.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        databaseHelper = new DatabaseHelper(this);

        Button buttonClearData = findViewById(R.id.buttonClearData);
        buttonClearData.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Clear All Pantry Data")
                    .setMessage("This will permanently delete every ingredient in your pantry.\n Would you like to continue?")
                    .setPositiveButton("Clear", (dialog, which) -> {
                        databaseHelper.deleteAllIngredients();
                        finish();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}