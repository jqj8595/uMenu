package com.umenu.umenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pankaj Puri on 26/09/2016.
 */
public class Signup extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
    public void onDoneClick(View v){
        if( v.getId()== R.id.btnDone) {
            EditText firstName = (EditText) findViewById(R.id.txtInputFirstName);
            EditText lastName = (EditText) findViewById(R.id.txtInputLastName);
            EditText Email = (EditText) findViewById(R.id.txtInputEmail);
            EditText password = (EditText) findViewById(R.id.inputTxtPass);
            EditText confirmPass = (EditText) findViewById(R.id.inputTxtConfirmPass);

            String fNameStr = firstName.getText().toString();
            String lNameStr = lastName.getText().toString();
            String emailStar = Email.getText().toString();
            String passwordStr = password.getText().toString();
            String confirmPassStr = confirmPass.getText().toString();

            if(!passwordStr.equals(confirmPassStr))
            {
                //popup msg;
                Toast pass= Toast.makeText(Signup.this,"Passwords don't match",Toast.LENGTH_SHORT);
                pass.show();
            }
            else{
                Intent iDone = new Intent(Signup.this,Homepage.class);
                startActivity(iDone);
            }
        }
    }
}