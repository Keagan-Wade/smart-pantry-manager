package com.keaganwade.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private IngredientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        RecipeSeeder.seedIfEmpty(databaseHelper);

        List<Ingredient> ingredientList = databaseHelper.getAllIngredients();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPantry);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new IngredientAdapter(ingredientList, ingredient -> showIngredientOptionsDialog(ingredient));

        recyclerView.setAdapter(adapter);

        Button buttonAddIngredient = findViewById(R.id.buttonAddIngredient);
        buttonAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditIngredientActivity.class);
            startActivity(intent);
        });

        Button buttonSuggestedRecipes = findViewById(R.id.buttonSuggestedRecipes);
        buttonSuggestedRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SuggestedRecipesActivity.class);
            startActivity(intent);
        });

        Button buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Ingredient> ingredientList = databaseHelper.getAllIngredients();
        adapter.updateList(ingredientList);
    }

    private void showIngredientOptionsDialog(Ingredient ingredient) {
        new AlertDialog.Builder(this)
                .setTitle(ingredient.getName())
                .setItems(new String[]{"Edit", "Delete"}, ((dialog, which) -> {

                    // Checks which button was clicked
                    if (which == 0) {
                        Intent intent = new Intent(MainActivity.this, AddEditIngredientActivity.class);
                        intent.putExtra("ingredient_id", ingredient.getId());
                        startActivity(intent);

                    } else {
                        databaseHelper.deleteIngredient(ingredient.getId());
                        List<Ingredient> refreshedList = databaseHelper.getAllIngredients();
                        adapter.updateList(refreshedList);
                    }
                })
                ) .show();
    }
}