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
        switch (v.getId()) {
            case R.id.menuMainChoiceBreakfast:
                nextActivity = new Intent(MenuMain.this, MenuBreakfast.class);
                startActivity(nextActivity);
                break;
            case R.id.menuMainChoiceLunch:

                break;
            case R.id.menuMainChoiceDinner:

                break;
            case R.id.menuMainChoiceDrinks:

                break;
        }
    }
}
