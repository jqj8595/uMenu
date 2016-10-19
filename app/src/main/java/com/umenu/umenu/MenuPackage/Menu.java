/**
 * When using this class check to see if the object member is null first as the
 * database may not have retrieved the information
 * Hopefully a singleton object will not be garbage collected when not referenced. Information
 * regarding this is iffy. Used getters and setters as this information does not need to be changed
 * at any point.
 */

package com.umenu.umenu.MenuPackage;

import java.util.ArrayList;

public class Menu {
    //Holds this instance. Do not delete
    private static Menu ourInstance = null;

    //Menu items
    private ArrayList<FoodItem> breakfastMenu = null;
    private ArrayList<FoodItem> lunchMenu = null;
    private ArrayList<FoodItem> dinnerMenu = null;
    private ArrayList<FoodItem> drinksMenu = null;

//Constructors and Singletons methods
    public static Menu getInstance() {
        if (ourInstance == null) {
            ourInstance = new Menu();

            //Connect to database and retrieve food items

        }

        return ourInstance;
    }
    private Menu() {

    }

//Getters and setters
    public ArrayList<FoodItem> getBreakfastMenu() {
        return breakfastMenu;
    }
    public void setBreakfastMenu(ArrayList<FoodItem> breakfastMenu) {
        this.breakfastMenu = breakfastMenu;
    }
    public ArrayList<FoodItem> getLunchMenu() {
        return lunchMenu;
    }
    public void setLunchMenu(ArrayList<FoodItem> lunchMenu) {
        this.lunchMenu = lunchMenu;
    }
    public ArrayList<FoodItem> getDinnerMenu() {
        return dinnerMenu;
    }
    public void setDinnerMenu(ArrayList<FoodItem> dinnerMenu) {
        this.dinnerMenu = dinnerMenu;
    }
    public ArrayList<FoodItem> getDrinksMenu() {
        return drinksMenu;
    }
    public void setDrinksMenu(ArrayList<FoodItem> drinksMenu) {
        this.drinksMenu = drinksMenu;
    }


}
