package com.umenu.umenu.MenuPackage;

import com.umenu.umenu.UserData;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SetupDummy {


    public void setupMenu(){
        Menu menu = Menu.getInstance();
        ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();

        //Breakfast menu
        foodList.add(new FoodItem("Eggs benny", "Poached eggs on toast with holandaise sauce", new BigDecimal("17.50")));
        foodList.add(new FoodItem("Eggs on toast", "Fresh eggs on toast with tomato", new BigDecimal(11.50)));
        foodList.add(new FoodItem("Big breaky", "Sausages, eggs, hashbrowns, and whole meal toast", new BigDecimal("20.50")));
        menu.setBreakfastMenu(foodList);

        //Lunch menu
        foodList = new ArrayList<FoodItem>();
        foodList.add(new FoodItem("Hamburger", "Fresh NZ beef pattie with egg, lettuce and cheese", new BigDecimal("17.50")));
        foodList.add(new FoodItem("Rice Risotto", "You have had it before, use ur imagination", new BigDecimal("2.00")));
        foodList.add(new FoodItem("Candied Apple", "We all want one, come on accept it", new BigDecimal("200.00")));
        menu.setLunchMenu(foodList);

        //Dinner menu
        foodList = new ArrayList<FoodItem>();
        foodList.add(new FoodItem("Stir Fry", "Like ya mum would have made only way better", new BigDecimal("10.00")));
        foodList.add(new FoodItem("Rice Risotto", "Yeah it for dinner as well", new BigDecimal("2.00")));
        foodList.add(new FoodItem("Pork Ribs", "You want candied meat, well you got it", new BigDecimal("50.00")));
        foodList.add(new FoodItem("Salmon fillet", "Fresh NZ salmon battered with chips", new BigDecimal("30.00")));
        foodList.add(new FoodItem("Other item", "Ya thought I was gona do something nice with the salmon aye?", new BigDecimal("1000.00")));
        foodList.add(new FoodItem("Another item", "Adding things now to make the listView scroll", new BigDecimal("50.00")));
        foodList.add(new FoodItem("No ideas left", "This is so tedious", new BigDecimal("50.00")));
        menu.setDinnerMenu(foodList);

        //Drinks menu
        foodList = new ArrayList<FoodItem>();
        foodList.add(new FoodItem("Lemonade", "Like ya mum would have made only way better", new BigDecimal("10.00")));
        foodList.add(new FoodItem("Candied Apple", "Good for all occasions", new BigDecimal("2.00")));
        foodList.add(new FoodItem("House whisky", "Freshly brewed from ee'ol Jimbob down the bayou", new BigDecimal("50.00")));
        foodList.add(new FoodItem("Actual whisky", "Johny Walker, Blue Label", new BigDecimal("30.00")));
        foodList.add(new FoodItem("Garage Project Beer", "Im getting less crafty but this beer aint", new BigDecimal("1000.00")));
        foodList.add(new FoodItem("Hop Zombie", "EPIC brewing company. So much hops it will turn you into a monster", new BigDecimal("50.00")));
        foodList.add(new FoodItem("Three Wolves", "Macs brewing company. Best of their selection, so we don't stock the rest", new BigDecimal("50.00")));
        menu.setDrinksMenu(foodList);
    }

    public void setupUser(){
        UserData userData = UserData.getInstance();
        userData.setUserName("Richard Johnson");
        userData.setTable(0);
        userData.setOrder(new ArrayList<FoodItem>());
    }
}
