/**
 * This class contains a food item name, description, and picture(Yet to be coded). Is intended to
 * represent an item of food. Uses getters and setters so they cannot be changed as they should
 * not need to be.
 */

package com.umenu.umenu.MenuPackage;

import com.umenu.umenu.R;

import java.math.BigDecimal;


public class FoodItem {

    private String name;
    private String description;
    private BigDecimal price;

    private int imageID;

//Constructors
    public FoodItem(String name, String description, BigDecimal price, int ID){
        this.name = name;
        this.description = description;
        this.price = price;

        // Change this when get images
        this.imageID = ID;
        //this.imageID = ID;
    }
    public FoodItem(String name, String description, BigDecimal price){
        this.name = name;
        this.description = description;
        this.price = price;

        // Change this when get images
        this.imageID = R.drawable.notepad_icon;
        //this.imageID = ID;
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
    public int getImageID() {
        return imageID;
    }
    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//Methods:
    
}
