/**
 * This class is designed to adapt to the menu that was chosen
 * by the user. All views adopt text respective of the chosen
 * menu. How it is decided is based on a value held in a singleton
 * called Menu.java. This value is set once a menu is chosen by the
 * user.
 */

package com.umenu.umenu.MenuPackage;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.umenu.umenu.R;
import com.umenu.umenu.UserData;

import java.util.ArrayList;



public class MenuSelectionGeneric extends AppCompatActivity{

    ArrayAdapter<FoodItem> adapter;

    //Holds the specific menu for the page. Eg Breakfast menu
    ArrayList<FoodItem> foodList;

    //Current selected item to be added
    FoodItem foodChoice;

    //Scrolling list holding fooditems
    ListView list;

    //Title that changes, displays the name of the selected item
    TextView title, selectedItem;

    Button addButton, removeButton, completeButton, viewOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_selection_generic);

        this.title = (TextView) findViewById(R.id.foodName);
        this.selectedItem = (TextView) findViewById(R.id.selectionToAdd);
        this.addButton = (Button)findViewById(R.id.menuAddButton);
        this.removeButton = (Button)findViewById(R.id.menuDeleteItem);
        this.completeButton = (Button)findViewById(R.id.menuPlaceOrder);
        this.viewOrderButton = (Button)findViewById(R.id.menuViewOrder);

        //Changes textView's text etc to suit the menu that was clicked on. This is decided
        //  by the value in the singleton Menu.menuSelected. Further details in Menu.java
        setupActivity();
        populateListView();
        setupClickListeners();
    }

    private void populateListView() {
        adapter = new CustomAdapter();
        this.list = (ListView) findViewById(R.id.menuListView);
        list.setAdapter(adapter);
        //list.setBackgroundColor(ContextCompat.getColor(MenuSelectionGeneric.this, R.color.background_20));
    }

    private class CustomAdapter extends ArrayAdapter<FoodItem>{

        public CustomAdapter(){
            super(MenuSelectionGeneric.this, R.layout.food_menu_view, foodList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.food_menu_view, parent, false);
            }

            FoodItem currentItem = foodList.get(position);

            ImageView imageView = (ImageView)itemView.findViewById(R.id.foodImage);
            //Adds the picture for the food item.
            imageView.setImageResource(currentItem.getImageID());
            //Adds the decription of the food item
            TextView foodDescription = (TextView)itemView.findViewById(R.id.foodDescription);
            foodDescription.setText(currentItem.getDescription());
            //Adds the food name
            TextView foodName = (TextView)itemView.findViewById(R.id.foodName);
            foodName.setText(currentItem.getName());
            foodName.setTextColor(ContextCompat.getColor(MenuSelectionGeneric.this, R.color.orange));
            //Adds the price of the food item appended to the existing text "Price: \n$ "
            TextView foodPrice = (TextView)itemView.findViewById((R.id.foodPrice));
            foodPrice.setText("Food Price:\n"+currentItem.getPrice());

            /*String.valueOf() can return string with several decimals and extra numbers
            Needs work*/


            return itemView;
            //return super.getView(position, convertView, parent);
        }
    }

    //Sets textView's text and other elements to align with the chosen menu since this
    //  class is designed to change depending on the menu the user wanted
    private void setupActivity(){
        if(Menu.getInstance().menuSelected == Menu.menuChoice.BREAK_FAST.getChoice()) {
            this.title.setText("Breakfast");
            this.foodList = Menu.getInstance().getBreakfastMenu();

        }
        else if(Menu.getInstance().menuSelected == Menu.menuChoice.LUNCH.getChoice()) {
            this.title.setText("Lunch");
            this.foodList = Menu.getInstance().getLunchMenu();

        }
        else if(Menu.getInstance().menuSelected == Menu.menuChoice.DINNER.getChoice()) {
            this.title.setText("Dinner");
            this.foodList = Menu.getInstance().getDinnerMenu();

        }
        else if(Menu.getInstance().menuSelected == Menu.menuChoice.DRINKS.getChoice()) {
            this.title.setText("Drinks");
            this.foodList = Menu.getInstance().getDrinksMenu();

        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
            "<ERROR> MenuSelectionGeneric.setupFoodList(): else statement", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    /**
     *  This sets what happens when the user clicks on an item in the ListView
     *  Just generally sets the FoodItem foodChoice to the respective one to add to a list
     *    when the user selects Add To Order
     */

    private void setupClickListeners(){
    //Listener for when a foodItem is clicked
        ListView itemList = (ListView)findViewById(R.id.menuListView);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                foodChoice = foodList.get(position);
                selectedItem.setText("Food Choice: \n"+foodChoice.getName());
            }
        });

    //Listener for complete order
        completeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Check to see if there are items in the order
                if(UserData.getInstance().getOrder().size()< 1){
                    Toast.makeText(getApplicationContext(),"There are no items in the order", Toast.LENGTH_SHORT).show();
                }
                //If there are items
                else{
                    Toast.makeText(getApplicationContext(),"Order placed", Toast.LENGTH_SHORT).show();
                    //Connect to database

                }
            }
        });

    //Listener for Remove Item from the order
    //Generates a popup menu so the user can click the one they want to remove
        removeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Check to see if items in order
                if(UserData.getInstance().getOrder() == null || UserData.getInstance().getOrder().size() < 1){
                    Toast.makeText(getApplicationContext(),"Order is currently empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    //Go to edit order activity
                    startActivity(new Intent(MenuSelectionGeneric.this,MenuEditOrder.class));

                }
            }
        });

    //Listener for add to order
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Adding to Singleton containing user data and current order
                if (foodChoice != null) {
                    UserData.getInstance().getOrder().add(foodChoice);
                    Toast.makeText(getApplicationContext(), "Added to order", Toast.LENGTH_SHORT).show();

                    //Testing purposes
                    //Toast.makeText(getApplicationContext(), String.valueOf(UserData.getInstance().getOrder()), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You have not selected an item", Toast.LENGTH_SHORT).show();

                }
            }
        });

    //Listener for viewing the order
        viewOrderButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
            //Check to see if items in order
            if(UserData.getInstance().getOrder() == null || UserData.getInstance().getOrder().size() < 1){
                Toast.makeText(getApplicationContext(),"Order is currently empty",Toast.LENGTH_SHORT).show();
            }
            else{
                //Go to edit order activity
                startActivity(new Intent(MenuSelectionGeneric.this,MenuEditOrder.class));
            }
            }
        });
    }

}
