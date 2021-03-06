package com.coffee.core;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

public class CoffeeMachineTest {

    @Test
    public void coffeeMachineCanLoadOneIngredient() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Water", 20));

        CoffeeMachine machine = new CoffeeMachine(ingredients);

        assertNotNull(machine.getIngredients());
        assertTrue(machine.getIngredients().size() == ingredients.size());
    }

    @Test
    public void coffeeMachineCanLoadIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Water", 20));
        ingredients.add(new Ingredient("CoffeeBeans", 20));
        ingredients.add(new Ingredient("Milk", 10));
        ingredients.add(new Ingredient("Chocolate", 5));

        CoffeeMachine machine = new CoffeeMachine(ingredients);

        assertNotNull(machine.getIngredients());
        assertTrue(machine.getIngredients().size() == ingredients.size());
    }

    @Test
    public void coffeeMachineCanLoadIngredientsAfterBeingConstructed() {
        CoffeeMachine machine = new CoffeeMachine();
        machine.addIngredient(new Ingredient("Water", 20));
        machine.addIngredient(new Ingredient("CoffeeBeans", 20));
        machine.addIngredient(new Ingredient("Milk", 10));
        machine.addIngredient(new Ingredient("Chocolate", 5));

        assertNotNull(machine.getIngredients());
        assertTrue(machine.getIngredients().size() == 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void coffeeMachineCannotLoadTwiceTheSameIngredient() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Water", 20));
        ingredients.add(new Ingredient("Water", 1));

        new CoffeeMachine(ingredients);
    }

    @Test(expected = IllegalArgumentException.class)
    public void coffeeMachineCannotLoadANullIngredient() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.addIngredient(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void coffeeMachineCannotLoadTwiceTheSameIngredient2() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Water", 20));

        CoffeeMachine machine = new CoffeeMachine(ingredients);

        machine.addIngredient(new Ingredient("Water", 1));
    }

    @Test
    public void coffeeMachineLoadsIngredientsFromMemory() {
        CoffeeMachine machine = new CoffeeMachine();
        machine.loadIngredientsFromMemory();

        assertNotNull(machine.getIngredients());
        assertTrue(machine.getIngredients().size() == 5);
        assertTrue(machine.getIngredients().contains(new Ingredient("Water", 40)));
        assertTrue(machine.getIngredients().contains(new Ingredient("Coffee", 40)));
        assertTrue(machine.getIngredients().contains(new Ingredient("Milk", 40)));
        assertTrue(machine.getIngredients().contains(new Ingredient("Chocolate", 20)));
        assertTrue(machine.getIngredients().contains(new Ingredient("Sugar", 20)));
    }

    @Test
    public void coffeeMachineCanAddDrinks() {
        Ingredient water = new Ingredient("Water", 1);
        Ingredient coffee = new Ingredient("Coffee", 1);

        Drink espresso = new Drink("Espresso");
        espresso.addIngredient(water);
        espresso.addIngredient(coffee);

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.addDrink(espresso);

        assertTrue(coffeeMachine.getDrinks().contains(espresso));
    }

    @Test(expected = IllegalArgumentException.class)
    public void coffeeMachineCannotAddNullDrinks() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.addDrink(null);
    }

    @Test
    public void coffeeMachineLoadsDrinksFromMemory() {
        CoffeeMachine machine = new CoffeeMachine();
        machine.loadDrinksFromMemory();

        List<Drink> drinks = new ArrayList<>();
        drinks.add(new Drink("Espresso")
                .addIngredient(new Ingredient("Coffee", 2))
                .addIngredient(new Ingredient("Water", 2)));
        drinks.add(new Drink("Coffee")
                .addIngredient(new Ingredient("Coffee", 2))
                .addIngredient(new Ingredient("Water", 4)));
        drinks.add(new Drink("Cappuccino")
                .addIngredient(new Ingredient("Coffee", 2))
                .addIngredient(new Ingredient("Water", 2))
                .addIngredient(new Ingredient("Milk", 2)));
        drinks.add(new Drink("Chocolate")
                .addIngredient(new Ingredient("Chocolate", 2))
                .addIngredient(new Ingredient("Water", 3)));

        assertNotNull(machine.getDrinks());
        assertTrue(drinks.size() == machine.getDrinks().size());
        assertTrue(machine.getDrinks().containsAll(drinks));
    }

    @Test
    public void coffeeMachineInitLoadsIngredientsAndDrinksFromMemory() {
        CoffeeMachine machine = new CoffeeMachine();
        machine.init();

        List<Drink> drinks = new ArrayList<>();
        drinks.add(new Drink("Espresso")
                .addIngredient(new Ingredient("Coffee", 2))
                .addIngredient(new Ingredient("Water", 2)));
        drinks.add(new Drink("Coffee")
                .addIngredient(new Ingredient("Coffee", 2))
                .addIngredient(new Ingredient("Water", 4)));
        drinks.add(new Drink("Cappuccino")
                .addIngredient(new Ingredient("Coffee", 2))
                .addIngredient(new Ingredient("Water", 2))
                .addIngredient(new Ingredient("Milk", 2)));
        drinks.add(new Drink("Chocolate")
                .addIngredient(new Ingredient("Chocolate", 2))
                .addIngredient(new Ingredient("Water", 3)));

        assertNotNull(machine.getDrinks());
        assertTrue(drinks.size() == machine.getDrinks().size());
        assertTrue(machine.getDrinks().containsAll(drinks));

        assertNotNull(machine.getIngredients());
        assertTrue(machine.getIngredients().size() == 5);
        assertTrue(machine.getIngredients().contains(new Ingredient("Water", 40)));
        assertTrue(machine.getIngredients().contains(new Ingredient("Coffee", 40)));
        assertTrue(machine.getIngredients().contains(new Ingredient("Milk", 40)));
        assertTrue(machine.getIngredients().contains(new Ingredient("Chocolate", 20)));
        assertTrue(machine.getIngredients().contains(new Ingredient("Sugar", 20)));
    }

    @Test
    public void getDrinksNameListReturnsTheDrinkNames() {
        CoffeeMachine machine = new CoffeeMachine();

        machine.addDrink(new Drink("Espresso")
                .addIngredient(new Ingredient("Water", 1))
                .addIngredient(new Ingredient("Coffee", 1)));
        machine.addDrink(new Drink("Coffee")
                .addIngredient(new Ingredient("Water", 2))
                .addIngredient(new Ingredient("Coffee", 1)));

        ArrayList<String> list = new ArrayList<String>() {
            {
                add("Espresso");
                add("Coffee");
            }
        };
        assertEquals(list, machine.getDrinksNameList());
    }

    @Test
    public void getDrinksByNameWorks() {
        CoffeeMachine machine = new CoffeeMachine();

        machine.addDrink(new Drink("Espresso")
                .addIngredient(new Ingredient("Water", 1))
                .addIngredient(new Ingredient("Coffee", 1)));
        machine.addDrink(new Drink("Coffee")
                .addIngredient(new Ingredient("Water", 2))
                .addIngredient(new Ingredient("Coffee", 1)));

        Drink espresso = new Drink("Espresso")
                .addIngredient(new Ingredient("Water", 1))
                .addIngredient(new Ingredient("Coffee", 1));

        assertEquals(espresso, machine.getDrinkByName("Espresso"));
    }

    @Test
    public void consumeIngredientReducesTheTotalIngredients() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        coffeeMachine.addIngredient(new Ingredient("Water", 20));
        coffeeMachine.addIngredient(new Ingredient("Coffee", 20));

        Drink espresso = new Drink("Espresso")
                .addIngredient(new Ingredient("Water", 1))
                .addIngredient(new Ingredient("Coffee", 1));

        coffeeMachine.prepareDrink(espresso);

        assertTrue(coffeeMachine.getIngredientsMap().get("Water").getQuantity() == 19);
        assertTrue(coffeeMachine.getIngredientsMap().get("Coffee").getQuantity() == 19);
    }

    @Test(expected = IllegalStateException.class)
    public void prepareDrinkFailsIfIngredientDoesNotExist() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        coffeeMachine.addIngredient(new Ingredient("Water", 20));
        coffeeMachine.addIngredient(new Ingredient("Coffee", 20));

        Drink wEspresso = new Drink("WEspresso")
                .addIngredient(new Ingredient("Wine", 1))
                .addIngredient(new Ingredient("Coffee", 1));

        coffeeMachine.prepareDrink(wEspresso);
    }

    @Test
    public void bulkLoadDrinksWorks() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        List<Drink> drinks = new ArrayList<>();
        drinks.add(new Drink("Espresso")
                .addIngredient(new Ingredient("Water", 1))
                .addIngredient(new Ingredient("Coffee", 1)));
        drinks.add(new Drink("Coffee")
                .addIngredient(new Ingredient("Water", 2))
                .addIngredient(new Ingredient("Coffee", 1)));
        drinks.add(new Drink("Cappuccino")
                .addIngredient(new Ingredient("Water", 1))
                .addIngredient(new Ingredient("Coffee", 1))
                .addIngredient(new Ingredient("Milk", 1)));
        drinks.add(new Drink("Chocolate")
                .addIngredient(new Ingredient("Milk", 2))
                .addIngredient(new Ingredient("Chocolate", 1)));

        coffeeMachine.loadDrinks(drinks);

        assertNotNull(coffeeMachine.getDrinks());
        assertTrue(drinks.size() == coffeeMachine.getDrinks().size());
        assertTrue(coffeeMachine.getDrinks().containsAll(drinks));
    }

    @Test
    public void increaseDrinkStrengthAffectsOnlyOneDrink() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.init();

        Drink dPlus = coffeeMachine.getDrinkByName("Espresso");
        assertTrue(dPlus.getIngredientByName("Coffee").getQuantity() == 2);

        dPlus.increaseStrength(1);
        assertTrue(dPlus.getIngredientByName("Coffee").getQuantity() == 3);

        Drink d = coffeeMachine.getDrinkByName("Espresso");
        assertTrue(d.getIngredientByName("Coffee").getQuantity() == 2);
    }

    @Test
    public void decreaseDrinkStrengthAffectsOnlyOneDrink() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.init();

        Drink dMinus = coffeeMachine.getDrinkByName("Espresso");
        assertTrue(dMinus.getIngredientByName("Coffee").getQuantity() == 2);

        dMinus.decreaseStrength(1);
        assertTrue(dMinus.getIngredientByName("Coffee").getQuantity() == 1);

        Drink d = coffeeMachine.getDrinkByName("Espresso");
        assertTrue(d.getIngredientByName("Coffee").getQuantity() == 2);
    }
    
    @Test
    public void prepareADrinkWithSugarWorks() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.init();

        Drink espresso = coffeeMachine.getDrinkByName("Espresso");
        espresso.addSugar();
        
        coffeeMachine.prepareDrink(espresso);
        
        assertTrue(
            coffeeMachine.getIngredients().contains(new Ingredient("Water", 38)));
        assertTrue(
            coffeeMachine.getIngredients().contains(new Ingredient("Coffee", 38)));
        assertTrue(
            coffeeMachine.getIngredients().contains(new Ingredient("Sugar", 19)));
    }

}
