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

    // Checks if ingredient exists in DB
    public boolean ingredientExists(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PANTRY, new String[]{COL_ID}, COL_NAME + " = ? ", new String[]{name}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"id", "name", "quantity", "unit", "expiry_date"};

        try (Cursor cursor = db.query(TABLE_PANTRY, columns, null, null, null, null, COL_NAME + " ASC")) {

            // Using column index for optimal performance
            int idIndex = cursor.getColumnIndexOrThrow("id");
            int nameIndex = cursor.getColumnIndexOrThrow("name");
            int quantityIndex = cursor.getColumnIndexOrThrow("quantity");
            int unitIndex = cursor.getColumnIndexOrThrow("unit");
            int expiryIndex = cursor.getColumnIndexOrThrow("expiry_date");

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
}
