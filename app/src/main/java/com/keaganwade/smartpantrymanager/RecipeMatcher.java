package com.keaganwade.smartpantrymanager;

import java.util.ArrayList;
import java.util.List;

public class RecipeMatcher {

    // Checks required ingredients against the pantry
    private static boolean pantryHasEnough(RecipeIngredient required, List<Ingredient> pantry) {
        String requiredName = normalizeName(required.getName());
        String requiredFamily = baseUnitFamily(required.getUnit());
        double requiredUnit = convertToBaseUnit(required.getQuantity(), required.getUnit());

        for (Ingredient pantryIngredient : pantry) {
            String name = normalizeName(pantryIngredient.getName());

            if (name.equals(requiredName)) {
                String family = baseUnitFamily(pantryIngredient.getUnit());

                if (!family.equals(requiredFamily)) {
                    return false;
                }

                double amount = convertToBaseUnit(pantryIngredient.getQuantity(), pantryIngredient.getUnit());
                return amount >= requiredUnit;
            }
        }

        return false;
    }

    // Checks if there is enough of the required ingredients
    public static boolean matches(Recipe recipe, List<Ingredient> pantry) {

        for (RecipeIngredient required : recipe.getIngredientList()) {
            if (!pantryHasEnough(required, pantry)) {
                return false;
            }
        }

        return true;
    }

    // Returns a list of suggested recipes
    public static List<Recipe> getSuggestedRecipes(List<Recipe> allRecipes, List<Ingredient> pantry) {
        List<Recipe> suggested = new ArrayList<>();

        for (Recipe recipe : allRecipes) {

            if (matches(recipe, pantry)) {
                suggested.add(recipe);
            }
        }

        return suggested;
    }

    // Normalizes Names of Ingredients e.g Tomatoes into Tomato
    private static String normalizeName(String name) {
        String result = name.trim().toLowerCase();

        if (result.endsWith("es")) {
            result = result.substring(0, result.length() - 2);

        } else if (result.endsWith("s")) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    // Normalizes Differences in Units e.g kgs into g
    private static double convertToBaseUnit(double quantity, String unit) {
        String u = unit.trim().toLowerCase();

        switch (u) {
            case "kg":
                return quantity * 1000;
            case "g":
                return quantity;
            case "l":
                return quantity * 1000;
            case "ml":
                return quantity;
            default:
                return quantity;
        }
    }

    // Returns the Unit's family
    private static String baseUnitFamily(String unit) {
        String u = unit.trim().toLowerCase();

        switch (u) {
            case "kg":
            case "g":
                return "weight";
            case "l":
            case "ml":
                return "volume";
            default:
                return u;
        }
    }
}
