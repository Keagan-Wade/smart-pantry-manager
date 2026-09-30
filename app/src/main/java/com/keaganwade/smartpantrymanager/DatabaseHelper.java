package com.keaganwade.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "pantry.db";
    private static final int DB_VERSION = 1;

    // Pantry Table
    public static final String TABLE_PANTRY = "pantry";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_QUANTITY = "quantity";
    public static final String COL_UNIT = "unit";
    public static final String COL_EXPIRY = "expiry_date";

    // Recipes Table
    public static final String TABLE_RECIPES = "recipes";
    public static final String COL_RECIPE_ID = "id";
    public static final String COL_RECIPE_NAME = "name";
    public static final String COL_RECIPE_STEPS = "steps";

    // Recipe Ingredients Table
    public static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
    public static final String COL_RI_ID = "id";
    public static final String COL_RI_RECIPE_ID = "recipe_id";
    public static final String COL_RI_NAME = "name";
    public static final String COL_RI_QUANTITY = "quantity";
    public static final String COL_RI_UNIT = "unit";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Creating the Pantry Table
        String createPantryStmt = "CREATE TABLE " + TABLE_PANTRY +
                "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT NOT NULL, " +
                COL_QUANTITY + " REAL NOT NULL, " +
                COL_UNIT + " TEXT NOT NULL, " +
                COL_EXPIRY + " TEXT"
                + ")";

        db.execSQL(createPantryStmt);

        // Creating the Recipes Table
        String createRecipesStmt = "CREATE TABLE " + TABLE_RECIPES +
                "(" +
                COL_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_RECIPE_NAME + " TEXT NOT NULL, " +
                COL_RECIPE_STEPS + " TEXT NOT NULL" +
                ")";

        db.execSQL(createRecipesStmt);

        // Creating Recipe Ingredients Table
        String createRIStmt = "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS +
                "(" +
                COL_RI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_RI_RECIPE_ID + " INTEGER NOT NULL, " +
                COL_RI_NAME + " TEXT NOT NULL, " +
                COL_RI_QUANTITY + " REAL NOT NULL, " +
                COL_RI_UNIT + " TEXT NOT NULL, " +
                "FOREIGN KEY(" + COL_RI_RECIPE_ID + ") REFERENCES " + TABLE_RECIPES + "(" + COL_RECIPE_ID + ")" +
                ")";

        db.execSQL(createRIStmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        onCreate(db);
    }

    // Adds ingredient to the database
    public long addIngredient(Ingredient ingredient) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME, ingredient.getName());
        values.put(COL_QUANTITY, ingredient.getQuantity());
        values.put(COL_UNIT, ingredient.getUnit());
        values.put(COL_EXPIRY, ingredient.getExpiryDate());

        return db.insert(TABLE_PANTRY, null, values);
    }

    // Update an ingredient
    public int updateIngredient(Ingredient ingredient) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME, ingredient.getName());
        values.put(COL_QUANTITY, ingredient.getQuantity());
        values.put(COL_UNIT, ingredient.getUnit());
        values.put(COL_EXPIRY, ingredient.getExpiryDate());

        return  db.update(TABLE_PANTRY, values, COL_ID + " = ? ", new String[]{ String.valueOf(ingredient.getId()) });
    }

    // Delete an ingredient
    public int deleteIngredient(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return  db.delete(TABLE_PANTRY, COL_ID + " = ? ", new String[]{ String.valueOf(id) });
    }

    public void deleteAllIngredients() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PANTRY, null, null);
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {COL_ID, COL_NAME, COL_QUANTITY, COL_UNIT, COL_EXPIRY};

        try (Cursor cursor = db.query(TABLE_PANTRY, columns, null, null, null, null, COL_NAME + " ASC")) {

            // Using column index for optimal performance
            int idIndex = cursor.getColumnIndexOrThrow(COL_ID);
            int nameIndex = cursor.getColumnIndexOrThrow(COL_NAME);
            int quantityIndex = cursor.getColumnIndexOrThrow(COL_QUANTITY);
            int unitIndex = cursor.getColumnIndexOrThrow(COL_UNIT);
            int expiryIndex = cursor.getColumnIndexOrThrow(COL_EXPIRY);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                double quantity = cursor.getDouble(quantityIndex);
                String unit = cursor.getString(unitIndex);
                String expiry_date = cursor.getString(expiryIndex);

                Ingredient ingredient = new Ingredient(id, name, quantity, unit, expiry_date);

                list.add(ingredient);
            }

        } catch (Exception e) {
            Log.e("DatabaseHelper", "getAllIngredients failed.", e);
        }
        return list;
    }

    public Ingredient getIngredientById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {COL_ID, COL_NAME, COL_QUANTITY, COL_UNIT, COL_EXPIRY};

        Ingredient ingredient = null;
        try (Cursor cursor = db.query(TABLE_PANTRY, columns, COL_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null)) {

            if (cursor.moveToFirst()) {

                int nameIndex = cursor.getColumnIndexOrThrow(COL_NAME);
                int quantityIndex = cursor.getColumnIndexOrThrow(COL_QUANTITY);
                int unitIndex = cursor.getColumnIndexOrThrow(COL_UNIT);
                int expiryIndex = cursor.getColumnIndexOrThrow(COL_EXPIRY);

                String name = cursor.getString(nameIndex);
                double quantity = cursor.getDouble(quantityIndex);
                String unit = cursor.getString(unitIndex);
                String expiry = cursor.getString(expiryIndex);

                ingredient = new Ingredient(id, name, quantity, unit, expiry);
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "getIngredientById failed.", e);
        }

        return ingredient;
    }

    public Recipe getRecipeById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {COL_RECIPE_ID, COL_RECIPE_NAME, COL_RECIPE_STEPS};

        Recipe recipe = null;
        try (Cursor cursor = db.query(TABLE_RECIPES, columns, COL_RECIPE_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null)) {

            if (cursor.moveToFirst()) {

                int nameIndex = cursor.getColumnIndexOrThrow(COL_RECIPE_NAME);
                int stepsIndex = cursor.getColumnIndexOrThrow(COL_RECIPE_STEPS);

                String name = cursor.getString(nameIndex);
                String steps = cursor.getString(stepsIndex);

                recipe = new Recipe(id, name, steps, getIngredientsForRecipe(id));
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "getRecipeById failed.", e);
        }

        return recipe;
    }

    public List<Recipe> getAllRecipes() {
        SQLiteDatabase db = getReadableDatabase();
        List<Recipe> recipeList = new ArrayList<>();
        String[] columns = {COL_RECIPE_ID, COL_RECIPE_NAME, COL_RECIPE_STEPS};

        try (Cursor cursor = db.query(TABLE_RECIPES, columns, null, null, null,null, COL_RECIPE_NAME)) {

            int recipeIdIndex = cursor.getColumnIndexOrThrow(COL_RECIPE_ID);
            int nameIndex = cursor.getColumnIndexOrThrow(COL_RECIPE_NAME);
            int stepsIndex = cursor.getColumnIndexOrThrow(COL_RECIPE_STEPS);

            while (cursor.moveToNext()) {
                int recipeId = cursor.getInt(recipeIdIndex);
                String name = cursor.getString(nameIndex);
                String steps = cursor.getString(stepsIndex);

                Recipe recipe = new Recipe(recipeId, name, steps, getIngredientsForRecipe(recipeId));

                recipeList.add(recipe);
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "getAllRecipes failed.", e);
        }

        return recipeList;
    }

    public long addRecipe(Recipe recipe) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues recipeValues = new ContentValues();
        recipeValues.put(COL_RECIPE_NAME, recipe.getName());
        recipeValues.put(COL_RECIPE_STEPS, recipe.getSteps());

        long recipeId = db.insert(TABLE_RECIPES, null, recipeValues);

        recipe.getIngredientList().forEach(ri -> {

            ContentValues recipeIngredientValues = new ContentValues();

            recipeIngredientValues.put(COL_RI_RECIPE_ID, recipeId);
            recipeIngredientValues.put(COL_RI_NAME, ri.getName());
            recipeIngredientValues.put(COL_RI_QUANTITY, ri.getQuantity());
            recipeIngredientValues.put(COL_RI_UNIT, ri.getUnit());

            db.insert(TABLE_RECIPE_INGREDIENTS, null, recipeIngredientValues);
        });

        return recipeId;
    }

    public List<RecipeIngredient> getIngredientsForRecipe(int recipeId) {
        SQLiteDatabase db = getReadableDatabase();
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        String[] columns = {COL_RI_ID, COL_RI_RECIPE_ID, COL_RI_NAME, COL_RI_QUANTITY, COL_RI_UNIT};

        try (Cursor cursor = db.query(TABLE_RECIPE_INGREDIENTS, columns, COL_RI_RECIPE_ID + " = ?", new String[]{ String.valueOf(recipeId) }, null, null, null)) {

            int idIndex = cursor.getColumnIndexOrThrow(COL_RI_ID);
            int nameIndex = cursor.getColumnIndexOrThrow(COL_RI_NAME);
            int quantityIndex = cursor.getColumnIndexOrThrow(COL_RI_QUANTITY);
            int unitIndex = cursor.getColumnIndexOrThrow(COL_RI_UNIT);

            while (cursor.moveToNext()) {

                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                double quantity = cursor.getDouble(quantityIndex);
                String unit = cursor.getString(unitIndex);

                RecipeIngredient recipeIngredient = new RecipeIngredient(id, recipeId, name, quantity, unit);

                recipeIngredients.add(recipeIngredient);
            }

        } catch (Exception e) {
            Log.e("DatabaseHelper", "getIngredientsForRecipe failed.", e);
        }

        return recipeIngredients;
    }
}
