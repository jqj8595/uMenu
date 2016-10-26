/**
 * Main activity where the user can access the various menus being breakfast, lunch, dinner, and
 * drinks.
 */
package com.umenu.umenu.MenuPackage;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.umenu.umenu.R;

import java.util.ArrayList;

public class MenuMain extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        TextView breakfast = (TextView) findViewById(R.id.menuMainChoiceBreakfast);
        breakfast.setOnClickListener(this);
        TextView lunch = (TextView) findViewById(R.id.menuMainChoiceLunch);
        lunch.setOnClickListener(this);
        TextView dinner = (TextView) findViewById(R.id.menuMainChoiceDinner);
        dinner.setOnClickListener(this);
        TextView drinks = (TextView) findViewById(R.id.menuMainChoiceDrinks);
        drinks.setOnClickListener(this);

    }


    //Takes the user to the desired activity
    public void onClick(View v) {
        Intent nextActivity;
        Menu menu;
        switch (v.getId()) {
            //Inside the singleton class Menu is a variable held for setting up the next activity.
            //This is set with an enum inside Menu so as to limit confusion.
            //The idea is to limit the amount of classes needing to be coded and ensure uniformity
            //  among the various menus, by having one class with the framework for how to layout
            //  the activity.
            case R.id.menuMainChoiceBreakfast:
                //Create singleton Menu if not already created and pass back
                //Constructor for it will not create new one if one exists
                menu = Menu.getInstance();
                //Set the value that represents the next activity that was chosen
                menu.menuSelected = Menu.menuChoice.BREAK_FAST.getChoice();
                //Intent stuff
                nextActivity = new Intent(MenuMain.this, MenuSelectionGeneric.class);
                //Start activity. The next activity will pull from  the singleton that is Menu
                //  an int stored in Menu.menuSelected which sets the content for the class
                //  MenuSelectionGeneric
                startActivity(nextActivity);
                break;
            case R.id.menuMainChoiceLunch:
                menu = Menu.getInstance();
                menu.menuSelected = Menu.menuChoice.LUNCH.getChoice();

                nextActivity = new Intent(MenuMain.this, MenuSelectionGeneric.class);
                startActivity(nextActivity);
                break;
            case R.id.menuMainChoiceDinner:
                menu = Menu.getInstance();
                menu.menuSelected = Menu.menuChoice.DINNER.getChoice();

                nextActivity = new Intent(MenuMain.this, MenuSelectionGeneric.class);
                startActivity(nextActivity);
                break;
            case R.id.menuMainChoiceDrinks:
                menu = Menu.getInstance();
                menu.menuSelected = Menu.menuChoice.DRINKS.getChoice();

                nextActivity = new Intent(MenuMain.this, MenuSelectionGeneric.class);
                startActivity(nextActivity);
                break;
        }
    }
}
