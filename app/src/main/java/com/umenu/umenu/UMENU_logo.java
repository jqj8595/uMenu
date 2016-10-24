package com.umenu.umenu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class UMENU_logo extends AppCompatActivity {
    public static int timeout = 4000; //time limit for logo to appear
    public boolean UmenuLogo = false; //Unit testing purposes
    public boolean value = false; //unit testing purtposes

    /**
     * Main method for Umenu logo class runs all layout and intents for the class inside the main
     * method.
     * @param savedInstanceState
     */

    @Override
   synchronized protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umenu_logo);

        //creates handler for delay of the screen so logo stays for user to see for 4000 miliseconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeintent = new Intent(UMENU_logo.this, MainActivity.class);

                startActivity(homeintent);
                UmenuLogo = true;
                finish();
            }
        }, timeout);
    }


    /**
     * Unit testing for quality assurance of start up screen if it has been executed of not
     * and if it runs in the required time.
     * @return boolean value if yes otherwise false.
     */
    public boolean check_startup_screen_Was_executed(){

        if(!UmenuLogo){
            value = false;
        }
        else{
            value = true;
        }
        return value;
    }

}
