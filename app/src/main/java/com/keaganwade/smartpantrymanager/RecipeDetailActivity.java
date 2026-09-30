package com.keaganwade.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        databaseHelper = new DatabaseHelper(this);

        int recipeId = getIntent().getIntExtra("recipe_id", -1);
        Recipe selectedRecipe = databaseHelper.getRecipeById(recipeId);

        TextView textName = findViewById(R.id.textRecipeName);
        TextView textIngredients = findViewById(R.id.textRecipeIngredients);
        TextView textSteps = findViewById(R.id.textRecipeSteps);

        if (selectedRecipe != null) {
            textName.setText(selectedRecipe.getName());
            textSteps.setText(selectedRecipe.getSteps());

            StringBuilder builder = new StringBuilder();

            for (RecipeIngredient recipeIngredient : selectedRecipe.getIngredientList()) {
                builder.append("‣ ")
                        .append(recipeIngredient.getName())
                        .append(" - ")
                        .append(recipeIngredient.getQuantity())
                        .append(" ")
                        .append(recipeIngredient.getUnit())
                        .append("\n");
            }

            textIngredients.setText(builder.toString());
        }

    }
}
