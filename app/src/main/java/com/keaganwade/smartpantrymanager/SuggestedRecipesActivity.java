package com.keaganwade.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggested_recipes);

        databaseHelper = new DatabaseHelper(this);

        List<Recipe> recipeList = databaseHelper.getAllRecipes();
        List<Ingredient> pantry = databaseHelper.getAllIngredients();
        List<Recipe> suggestedRecipes = RecipeMatcher.getSuggestedRecipes(recipeList, pantry);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSuggestedRecipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        View errorMessage = findViewById(R.id.errorMessageText);

        if (suggestedRecipes.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            errorMessage.setVisibility(View.VISIBLE);

        } else {

            recipeAdapter = new RecipeAdapter(suggestedRecipes, recipe ->  {
                Intent intent = new Intent(SuggestedRecipesActivity.this, RecipeDetailActivity.class);
                intent.putExtra("recipe_id", recipe.getId());
                startActivity(intent);
            });

            recyclerView.setAdapter(recipeAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            errorMessage.setVisibility(View.GONE);
        }

    }
}
