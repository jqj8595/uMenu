package com.umenu.umenu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UMENU_logo extends AppCompatActivity {
    public static int timeout = 4000;
    boolean UmenuLogo = false;

    @Override
   synchronized protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umenu_logo);



//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent homeintent = new Intent(UMENU_logo.this, MainActivity.class);
//
//                startActivity(homeintent);
//                UmenuLogo = true;
//                finish();
//
//
//
//
//            }
//        }, timeout);




//        public boolean check_startup_screen_Was_executed(){
//            boolean value = false;
//            if(!UmenuLogo){
//                value = false;
//            }
//
//            else{
//                value = true;
//
//            }
//        return value;
//
//
//
//        }











    }
}
