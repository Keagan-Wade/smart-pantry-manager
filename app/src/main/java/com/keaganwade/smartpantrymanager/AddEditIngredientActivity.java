package com.keaganwade.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditIngredientActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView textHeading;
    private EditText editTextName;
    private EditText editTextQuantity;
    private EditText editTextUnit;
    private EditText editTextExpiry;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);
        databaseHelper = new DatabaseHelper(this);

        textHeading = findViewById(R.id.textHeading);
        editTextName = findViewById(R.id.editTextName);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextUnit = findViewById(R.id.editTextUnit);
        editTextExpiry = findViewById(R.id.editTextExpiry);
        buttonSave = findViewById(R.id.buttonSave);

        int ingredientId = getIntent().getIntExtra("ingredient_id", -1);
        boolean isEditing = ingredientId != -1;

        if (isEditing) {
            Ingredient ingredient = databaseHelper.getIngredientById(ingredientId);
            textHeading.setText(R.string.edit_ingredient);
            editTextName.setText(ingredient.getName());
            editTextQuantity.setText(String.valueOf(ingredient.getQuantity()));
            editTextUnit.setText(ingredient.getUnit());
            editTextExpiry.setText(ingredient.getExpiryDate());
        } else {
            textHeading.setText(R.string.add_ingredient);
        }

        buttonSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String quantity = editTextQuantity.getText().toString().trim();
            String unit = editTextUnit.getText().toString().trim();
            String expiry = editTextExpiry.getText().toString().trim();

            if (name.isEmpty()) {
                editTextName.setError("Name is required!");
                return;
            }

            if (unit.isEmpty()) {
                editTextUnit.setError("Unit is required!");
                return;
            }

            double quantityValue;
            try {
                quantityValue = Double.parseDouble(quantity);
            } catch (NumberFormatException e) {
                editTextQuantity.setError("Enter a valid number!");
                return;
            }

            if (isEditing) {
                Ingredient ingredient = new Ingredient(ingredientId, name, quantityValue, unit, expiry.isEmpty() ? null : expiry);
                databaseHelper.updateIngredient(ingredient);
            } else {
                Ingredient ingredient = new Ingredient(name, quantityValue, unit, expiry.isEmpty() ? null : expiry);
                databaseHelper.addIngredient(ingredient);
            }

            finish();
        });
    }
}