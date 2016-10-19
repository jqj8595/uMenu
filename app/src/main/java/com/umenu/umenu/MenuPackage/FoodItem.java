/**
 * This class contains a food item name, description, and picture(Yet to be coded). Is intended to
 * represent an item of food. Uses getters and setters so they cannot be changed as they should
 * not need to be.
 */

package com.umenu.umenu.MenuPackage;

public class FoodItem {

    private String name;
    private String description;

    //Image not added yet.
    //private

//Constructors
    public FoodItem(String name, String description){
        this.name = name;
        this.description = description;
    }

//Getters and setters:
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

//Methods:
    
}
