package com.keaganwade.smartpantrymanager;

import java.util.List;

public class Recipe {

    private int id;
    private String name;
    private String steps;
    private List<RecipeIngredient> ingredientList;

    public Recipe(int id, String name, String steps, List<RecipeIngredient> ingredientList) {
        this.id = id;
        this.name = name;
        this.steps = steps;
        this.ingredientList = ingredientList;
    }

    public Recipe(String name, String steps, List<RecipeIngredient> ingredientList) {
        this.name = name;
        this.steps = steps;
        this.ingredientList = ingredientList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public List<RecipeIngredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<RecipeIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
}
