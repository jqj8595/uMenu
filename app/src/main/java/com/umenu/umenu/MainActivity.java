package com.umenu.umenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
    CallbackManager callbackManager;

    @Override
   synchronized protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email,publish_actions");//grabs facebook profile permission
        getFacebook_LoginDetails(loginButton); //calls login details
    }


    /**
     * initializes facebook sdk and gets the application context for the login feature
     */
    protected void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
    }


    /**
     * log in button is used for access through the app. if user is identified calls on
     * getUserInfo to retrieve users profile information for the purpose of the application.
     * @param login_button
     */

    protected void getFacebook_LoginDetails(LoginButton login_button){
        // Callback registration
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult login_result) { //if users login is successful or not
                getFacebook_UserInfo(login_result);
            }
            @Override
            public void onCancel() { //cancelling process automatically takes you back
            }
            @Override
            public void onError(FacebookException exception) { //calls on facebook exception.

            }
        });
    }


    /**
     * This log in and sign up buttons are for the restaurants databse, for people not logging
     * in using facebook or other social media, and want to create their own user profile using
     * our databse.
     * @param v
     */
    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.btnLogin)
        {
            Intent intentLogin = new Intent(MainActivity.this,Homepage.class); //calls Homepage
            startActivity(intentLogin);
        }
        else if(v.getId() == R.id.btnSignup)
        {
            Intent iSignup = new Intent(MainActivity.this,Signup.class); // calls sign up page
            startActivity(iSignup);
        }
    }


    /**
     * This method retrieves the login user information from facebook and calls uppon the Homepage
     * activity once it has been successful
     * @param login_result
     */
    protected void getFacebook_UserInfo(LoginResult login_result){
        GraphRequest data_request = GraphRequest.newMeRequest(
                login_result.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        Intent intent = new Intent(MainActivity.this,Homepage.class);
                        intent.putExtra("jsondata",json_object.toString());
                        startActivity(intent);
                    }
                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();

    }

    /**
     * On activity result used for call back manager and to use sdk as a superclass for current
     * activity.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }











}
