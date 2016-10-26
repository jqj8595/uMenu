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

    //Lists of menu items
    private ArrayList<FoodItem> breakfastMenu = null;
    private ArrayList<FoodItem> lunchMenu = null;
    private ArrayList<FoodItem> dinnerMenu = null;
    private ArrayList<FoodItem> drinksMenu = null;

    public int menuSelected;

//Constructors and Singletons methods
    public static Menu getInstance() {
        if (ourInstance == null) {
            ourInstance = new Menu();

            //Connect to database and retrieve food items
            //And setup food items into lists in class fields
            getMenus();

        }

        return ourInstance;
    }
    private Menu() {
        //Does nothing
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

    public enum menuChoice{
        BREAK_FAST  (0),
        LUNCH       (1),
        DINNER      (2),
        DRINKS      (3);

        private final int choice;

        private menuChoice(int choice){
            this.choice = choice;
        }
        public int getChoice(){
            return this.choice;
        }
    }

    public static void getMenus(){

        //Uses dummy data
        SetupDummy dummy = new SetupDummy();
        dummy.setupMenu();

    }
}
