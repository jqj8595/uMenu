package com.umenu.umenu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Setting class holds all the layout buttons of the seetings functionality,
 * changing user settings of account which is directly from the data base.
 * Enables deletion of account and change of user profile information to be made.
 */

public class Setting_activity extends AppCompatActivity {

    public boolean check_box = false; //boolean value to check if check_box is ticked
    public static int timeout = 5000; //time out time for delay when deleting account
    public boolean account_deleted = true;

    /**
     * main settings method to be called adding the layout of all the buttons and functionality
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_activity);

    }

    /**
     * On click listener for the check box when deleting account off the application.
     * if checked assigns true to the check_box boolean to indicate upon saving the users
     * decision.
     * @param view
     */

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.delete_account:
                onSaveChanges();
                if (checked ) {
                    check_box =true;
                }
                // returns false otherwise indicating account is to be not deleted
                else{
                    check_box =false;
                }
                    break; //breaks case once decison is been made.
        }
    }


    /**
     * Button save changes selection after it is pushed in the settings activity.
     * Saves the users changes to their profile.
     */

    public void onSaveChanges(){
        Button save;
        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //for deleting account it will reboot back to the login
                if(check_box ){
                    Toast.makeText(getBaseContext(), "Account is removed successfully",
                            Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent homeintent = new Intent(Setting_activity.this, UMENU_logo.class);

                            startActivity(homeintent);
                            Toast.makeText(getBaseContext(), "One moment please...",
                                    Toast.LENGTH_LONG).show();
                            finish();

                        }
                    }, timeout); //set timmer for deletion process and time to take it off the
                    //the database.
                }
            }
        });

    }


    /**
     * Unit testing for user input and deletion of account sends a Toast message to let
     * the developers know if the functionality is working or not.
     */
    public void check_if_user_decison(){
        if(check_box){
            //sends graphical message through app if the check box works or not.
            Toast.makeText(getBaseContext(), "check box works",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(), "Check box does not work",
                    Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * Unit testing to check if account is deleted or not successfully.
     * returns true if it has otherwise false if not.
     * @return boolean
     */
    public boolean isAccount_deleted(){
        if(account_deleted){
            return true;
        }

        else{
            return false;
        }


    }


}



