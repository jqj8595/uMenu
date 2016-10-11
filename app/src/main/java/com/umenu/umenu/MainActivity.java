package com.umenu.umenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
   synchronized protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.btnLogin)
        {
            Intent intentLogin = new Intent(MainActivity.this,Homepage.class);
            startActivity(intentLogin);
        }
        else if(v.getId() == R.id.btnSignup)
        {
            Intent iSignup = new Intent(MainActivity.this,Signup.class);
            startActivity(iSignup);
        }


    }
}
