package com.keaganwade.smartpantrymanager;

import java.util.Arrays;

public class RecipeSeeder {

    public static void seedIfEmpty(DatabaseHelper databaseHelper) {

        if (databaseHelper.getAllRecipes().isEmpty()) {
            seedRecipes(databaseHelper);
        }
    }

    private static void seedRecipes(DatabaseHelper databaseHelper) {

        Recipe scrambledEggs = new Recipe(
                "Scrambled Eggs",
                "Crack eggs into a bowl, add milk and salt, whisk, then cook in a pan over medium heat, stirring until set)",
                Arrays.asList(

                        new RecipeIngredient(0, "Egg", 2, "pcs"),
                        new RecipeIngredient(0, "Milk", 30, "ml"),
                        new RecipeIngredient(0, "Salt", 1, "pinch")

                )

        );

        databaseHelper.addRecipe(scrambledEggs);

        Recipe butteredToast = new Recipe(
                "Buttered Toast",
                "Toast bread for 2 minutes, butter while hot and sprinkle salt on top.",
                Arrays.asList(

                        new RecipeIngredient(0, "Bread", 2, "pcs"),
                        new RecipeIngredient(0, "Butter", 20, "g"),
                        new RecipeIngredient(0, "Salt", 1, "pinch")

                )
        );

        databaseHelper.addRecipe(butteredToast);

        Recipe toastedCheese = new Recipe(
                "Toasted Cheese Sandwich",
                "Butter both sides of the bread, place generous amount of cheese between the 2 slices, grill in a pan on medium heat, 2 minutes a side.",
                Arrays.asList(

                        new RecipeIngredient(0, "Bread", 2, "pcs"),
                        new RecipeIngredient(0, "Cheese", 20, "g"),
                        new RecipeIngredient(0, "Butter", 10, "g")

                )
        );

        databaseHelper.addRecipe(toastedCheese);

        Recipe pancakes = new Recipe(
                "Pancakes",
                "Add flour and sugar to a bowl and pour in milk, whisk together then add eggs, whisk again until smooth consistency. Place batter in a pan on medium heat and flip when you see bubbles.",
                Arrays.asList(

                        new RecipeIngredient(0,"Flour", 200, "g"),
                        new RecipeIngredient(0, "Egg", 2, "pcs"),
                        new RecipeIngredient(0, "Milk", 250, "ml"),
                        new RecipeIngredient(0, "Sugar", 30, "g")

                )
        );

        databaseHelper.addRecipe(pancakes);

        Recipe vegetableSoup = new Recipe(
                "Vegetable Soup",
                "Add water to a pot and bring to boil, while you chop the onion, carrots, potatoes into small pieces. Let simmer for 30mins.",
                Arrays.asList(

                        new RecipeIngredient(0, "Onion", 1, "pcs"),
                        new RecipeIngredient(0, "Carrot", 2, "pcs"),
                        new RecipeIngredient(0, "Potato", 2, "pcs"),
                        new RecipeIngredient(0, "Water", 500, "ml")

                )
        );

        databaseHelper.addRecipe(vegetableSoup);

        Recipe garlicBread = new Recipe(
                "Garlic Bread",
                "Add garlic and butter to a bowl and microwave until melted and mix well. Brush over bread, wrap in tin foil and oven bake at 175 Degrees for 15 mins.",
                Arrays.asList(

                        new RecipeIngredient(0, "Bread", 1, "pcs"),
                        new RecipeIngredient(0, "Butter", 30, "g"),
                        new RecipeIngredient(0, "Garlic", 3, "pcs")

                )
        );

        databaseHelper.addRecipe(garlicBread);

        Recipe mashedPotatoes = new Recipe(
                "Mashed Potatoes",
                "Boil potatoes in water until soft. Add milk and butter to potatoes and mash well until smooth.",
                Arrays.asList(

                        new RecipeIngredient(0, "Potato", 4, "pcs"),
                        new RecipeIngredient(0, "Butter", 30, "g"),
                        new RecipeIngredient(0, "Milk", 50, "ml"),
                        new RecipeIngredient(0, "Salt", 1, "pinch")

                )
        );

        databaseHelper.addRecipe(mashedPotatoes);

        Recipe stirFry = new Recipe(
                "Chicken Stir Fry",
                "Chop up chicken into strips. Chop onion and peppers into fine strips. Add chicken to a pan on medium heat, then add garlic and soy sauce and stir constantly for 10 mins.",
                Arrays.asList(

                        new RecipeIngredient(0, "Chicken", 300, "g"),
                        new RecipeIngredient(0, "Onion", 1, "pcs"),
                        new RecipeIngredient(0, "Peppers", 1, "pcs"),
                        new RecipeIngredient(0, "Soy Sauce", 20, "ml"),
                        new RecipeIngredient(0, "Garlic", 2, "pcs")

                )
        );

        databaseHelper.addRecipe(stirFry);

        Recipe eggFriedNoodles = new Recipe(
                "Egg Fried Noodles",
                "Cook noodles according to package instructions. Scramble eggs ina pan, then add drained noodles, soy sauce, and chopped onion, stir frying until combined.",
                Arrays.asList(

                        new RecipeIngredient(0, "Noodles", 200, "g"),
                        new RecipeIngredient(0, "Egg", 2, "pcs"),
                        new RecipeIngredient(0, "Soy Sauce", 15, "ml"),
                        new RecipeIngredient(0, "Onion", 1, "pcs")

                )
        );

        databaseHelper.addRecipe(eggFriedNoodles);

        Recipe potatoWedges = new Recipe(
                "Potato Wedges",
                "Chop up potatoes into wedges and place on a baking tray, drizzle with olive oil and sprinkle with salt. Bake at 175 Degrees for 20 mins.",
                Arrays.asList(

                        new RecipeIngredient(0, "Potato", 3, "pcs"),
                        new RecipeIngredient(0, "Olive Oil", 20, "ml"),
                        new RecipeIngredient(0, "Salt", 1, "pinch")

                )
        );

        databaseHelper.addRecipe(potatoWedges);

        Recipe cheeseQuesadilla = new Recipe(
                "Cheese Quesadilla",
                "Butter the outside of the tortilla and place cheese between the two tortilla. Grill on medium heat until golden brown and cheese is melted.",
                Arrays.asList(

                        new RecipeIngredient(0, "Tortilla", 2, "pcs"),
                        new RecipeIngredient(0, "Cheese", 2, "pcs"),
                        new RecipeIngredient(0, "Butter", 10, "g")

                )
        );

        databaseHelper.addRecipe(cheeseQuesadilla);

        Recipe chickenMayo = new Recipe(
                "Chicken Mayo Sandwich",
                "Cook chicken breasts through in a pan. Once cooked, shred chicken into fine pieces, add salt and mix in the mayo, once mixed then place between bread slices.",
                Arrays.asList(

                        new RecipeIngredient(0, "Bread", 2, "pcs"),
                        new RecipeIngredient(0, "Mayo", 15, "ml"),
                        new RecipeIngredient(0, "Chicken", 50, "g"),
                        new RecipeIngredient(0, "Salt", 1, "pinch")

                )
        );

        databaseHelper.addRecipe(chickenMayo);

        Recipe potatoFries = new Recipe(
                "Potato Fries",
                "Chop up potatoes into thin fry shapes, drizzle with olive oil, sprinkle with salt and bake at 175 Degrees for 15 mins",
                Arrays.asList(

                        new RecipeIngredient(0, "Potato", 2, "pcs"),
                        new RecipeIngredient(0, "Olive Oil", 15, "ml"),
                        new RecipeIngredient(0, "Salt", 3, "pinch")

                )
        );

        databaseHelper.addRecipe(potatoFries);

        Recipe chickenWrap = new Recipe(
                "Chicken Wrap",
                "Cut chicken and peppers into small pieces and fry on medium heat while stirring, add garlic. Once fully cooked and golden brown place fulling into tortilla and fold.",
                Arrays.asList(

                        new RecipeIngredient(0, "Chicken", 50, "g"),
                        new RecipeIngredient(0, "Garlic", 5, "pcs"),
                        new RecipeIngredient(0, "Peppers", 1, "pcs"),
                        new RecipeIngredient(0, "Tortilla", 1, "pcs")

                )
        );

        databaseHelper.addRecipe(chickenWrap);

        Recipe baconEggRoll = new Recipe(
                "Bacon Egg Roll",
                "Add olive oil to a pan on medium heat then crack the egg into the pan. Once your egg is cooked remove it from the pan. Place bacon into the pan and fry until done. Cut and butter the bread roll and place face down into the pan to toast them. Then place egg and bacon on the bread roll and enjoy.",
                Arrays.asList(

                        new RecipeIngredient(0, "Bread Roll", 1, "pcs"),
                        new RecipeIngredient(0, "Egg", 1, "pcs"),
                        new RecipeIngredient(0, "Olive Oil", 5, "ml"),
                        new RecipeIngredient(0, "Bacon", 2, "pcs"),
                        new RecipeIngredient(0, "Butter", 10, "g")

                )
        );

        databaseHelper.addRecipe(baconEggRoll);

        Recipe cheeseOmelette = new Recipe(
                "Cheese Omelette",
                "Whisk eggs with a pinch of salt. Fry chopped onions until soft then pour in eggs, sprinkle cheese on top and fold over once done.",
                Arrays.asList(

                        new RecipeIngredient(0, "Egg", 3, "pcs"),
                        new RecipeIngredient(0, "Cheese", 30, "g"),
                        new RecipeIngredient(0, "Onion", 1, "pcs"),
                        new RecipeIngredient(0, "Salt", 1, "pinch")

                )
        );

        databaseHelper.addRecipe(cheeseOmelette);

        Recipe bananaSmoothie = new Recipe(
                "Banana Smoothie",
                "Peel and slice the bananas, add to a blender with milk and sugar and blend until smooth consistency.",
                Arrays.asList(

                        new RecipeIngredient(0, "Banana", 2, "pcs"),
                        new RecipeIngredient(0, "Milk", 200, "ml"),
                        new RecipeIngredient(0, "Sugar", 10, "g")

                )
        );

        databaseHelper.addRecipe(bananaSmoothie);

        Recipe tunaSandwich = new Recipe(
                "Tuna Sandwich",
                "Drain the tuna and mix it in a bowl with the mayo. Spread on a slice of bread and top with the other slice.",
                Arrays.asList(

                        new RecipeIngredient(0, "Bread", 2, "pcs"),
                        new RecipeIngredient(0, "Tuna", 1, "pcs"),
                        new RecipeIngredient(0, "Mayo", 20, "g")

                )
        );

        databaseHelper.addRecipe(tunaSandwich);
    }
}
