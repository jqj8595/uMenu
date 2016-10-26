package com.umenu.umenu;

import com.umenu.umenu.MenuPackage.FoodItem;
import com.umenu.umenu.MenuPackage.SetupDummy;

import java.util.ArrayList;

public class UserData {
//Object fields
    private static UserData ourInstance = null;

    private String userName;
    private ArrayList<FoodItem> order;
    private int table;

//Constructors and getInstances
    public static UserData getInstance() {
        if(ourInstance == null){
            ourInstance = new UserData();

            //Get and store user data
            getData();
        }


        return ourInstance;
    }
    private UserData() {
        //Nill
    }

//Getters and setters
    public int getTable() {
        return table;
    }
    public void setTable(int table) {
        this.table = table;
    }
    public String getUserName() {
        return userName;
    }

    /**
     * Only set user name when reseting database username as well
     * @param userName
     */
    public void setUserName(String userName) {
        userName = userName;
    }

    /**
     * Returns the full arraylist so it can be modified
     * @return
     */
    public ArrayList<FoodItem> getOrder() {
        return order;
    }
    public void setOrder(ArrayList<FoodItem> newOrder) {
        order = newOrder;
    }

//Methods

    //Stores user data
    public static void getData(){
        //Setting up dummy data
        SetupDummy dummy = new SetupDummy();
        dummy.setupUser();
    }

    public void addItemToOrder(FoodItem item){
        if(this.order == null){
            this.order = new ArrayList<FoodItem>();
        }
        this.order.add(item);
    }

}
