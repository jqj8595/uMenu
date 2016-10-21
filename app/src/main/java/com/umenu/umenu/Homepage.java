package com.umenu.umenu;

/**
 * Created by nishanjayetileke on 11/10/16.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class Homepage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    //time out for splash screen at the start of the app
    public static int timeout = 4000;

    //instances created for unit testing purposes
    public boolean testing = false;
    public boolean result = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    //main running method that is automatically calls during runtime of the application
    @Override
    synchronized protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        //test_hashKey(); //made for unit testing function

        //object menu buttons declared as instances to be used with event handler and to build on
        //functionality.
        Button menu;
        ImageButton setting;
        ImageButton share;

        //connecting the buttons with the xml id created with each button
        menu = (Button) findViewById(R.id.btnMenu);
        setting = (ImageButton) findViewById(R.id.settings);
        share = (ImageButton) findViewById(R.id.share);
        menu.setTransformationMethod(null);

        //following method calls are for
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toasts are created as testing function to allow the developers know if it is
                //working. Toasts functions are not part of the application functionality.
                //only use for toasts if a switch is turned on for example location services.
                //Toast.makeText(getBaseContext(), "Share Button Pushed", Toast.LENGTH_LONG) .show();
                //testing = true;
                PopupMenu popup_share = new PopupMenu(Homepage.this, v);
                MenuInflater inflater2 = popup_share.getMenuInflater();
                inflater2.inflate(R.menu.menu_share, popup_share.getMenu());
                popup_share.show();
                setForceShowIcon(popup_share);
            }
        });

        //setting button click listener activates when setting button is pushed during runtime.
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //takes user to setting activity defined in other java classes under the manifest
                Intent setting_page = new Intent(Homepage.this, Setting_activity.class);
                startActivity(setting_page);
                testing = true;
            }
        });
        //menu clickjing method
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Main menu selection pane using pop up menu selection style for use
                //throughout the whole app.
                PopupMenu popup = new PopupMenu(Homepage.this, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu, popup.getMenu());
                popup.show();
                setForceShowIcon(popup);


            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //method call to act as a on click listener for popup menu
    @Override
    public boolean onMenuItemClick(MenuItem item) {

        return false;
    }


    //method call for inflation of the popup menu to be activated.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }


    private void sharePhotoToFacebook() {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.social_facebook_con);

        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .setCaption("Nice Place.....")
                .build();

        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        ShareApi.share(content, null);

        Toast.makeText(Homepage.this, "Facebook Photo Upload Complated", Toast.LENGTH_LONG).show();

    }


    //selection commands from the main popup menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //using switch to handle menu selections
        switch (id) {

            case R.id.Restaurants:

                result = true;
                testing_menuWorks_NewActivity(result);

                break;

            case R.id.NearBy:

//                result =true;
//                testing_menuWorks_NewActivity(result);

                //creating new intent to open map activity when nearBy is selected
                Intent googleMap = new Intent(Homepage.this, MapsActivity.class);
                startActivity(googleMap);

                break;

            case R.id.Reviews:
                //case 3 for comment box selection for restaurants subject to change

//                result =true;
//                testing_menuWorks_NewActivity(result);
                Intent feedback = new Intent(Homepage.this, Feedback.class);
                startActivity(feedback);


                break;

            case R.id.OrderNow:

                //case 4 order now button get fast access to the ordering page

                result = true;
                testing_menuWorks_NewActivity(result);

                break;


            case R.id.DinnerPlaces:

                //case 5 check for dinner places from the main screen.

                result = true;
                testing_menuWorks_NewActivity(result);

                break;

            case R.id.LunchPlaces:

                //case 6 check for lunch places from the main screen.

                result = true;
                testing_menuWorks_NewActivity(result);

                break;


            case R.id.BreakfastPlaces:

                //case 7 checks for breakfast places from the main screen.

                result = true;
                testing_menuWorks_NewActivity(result);

                break;

            case R.id.Facebook:
                result = true;
                sharePhotoToFacebook();
                break;


            case R.id.Twitter:
                result = true;
                testing_menuWorks_NewActivity(result);
                break;


            case R.id.Gmail:
                result = true;
                testing_menuWorks_NewActivity(result);
                break;


            default:
                //default if no selection is made returns the boolean of the item selection
                return super.onOptionsItemSelected(item);

        }
        //returns a boolean of the option selceted.
        return super.onOptionsItemSelected(item);


    }


    /**
     * Unit Tests: if the hash key matches the online hash key from facebook developers site.
     * It troubleshoots if the sdk is having problems running.
     *
     * @param popupMenu
     */
//    public void test_hashKey(){
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.facebook.samples.hellofacebook",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
//    }


    //Method calls allows menu icons to be visible due to android automatically switching off
    //icons visibility in popup menu development
    public static void setForceShowIcon(PopupMenu popupMenu) {
        try {

            //creating a array to access popup menu fields
            Field[] theFields = popupMenu.getClass().getDeclaredFields();
            //looping through the array of popup menu fields
            for (Field fill : theFields) {

                //fills in the popup menu fields if it has an icon declared in the menu.xml file.
                if ("mPopup".equals(fill.getName())) {

                    fill.setAccessible(true);

                    Object popupMenu_swtich = fill.get(popupMenu);

                    //calls the popup helper class to get names of items in the popup menu

                    Class<?> classPopupHelper = Class.forName
                            (popupMenu_swtich.getClass().getName());

                    //forces icons to be visible in application

                    Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon",
                            boolean.class);

                    setForceIcons.invoke(popupMenu_swtich, true);
                    //loop breaks once changed or otherwise.
                    break;
                }
            }

            //error handler created in case of unexpected error while looping through the
            // popup menu fields
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    //unit test method for selection in popup menu for faster display
    public void testing_menuWorks_NewActivity(boolean selection) {
        if (selection) {
            Toast.makeText(getBaseContext(), "Function working", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getBaseContext(), "Function not working", Toast.LENGTH_SHORT).show();


    }

    //unit tests to check if other buttons on the menu are functional
    public void menuclick(View v) {

        Toast.makeText(getBaseContext(), " menu button is responsive", Toast.LENGTH_LONG).show();

    }

    //Unit tests method for search button to check if working using toasts
    public void searchClicked(View v) {

        Toast.makeText(getBaseContext(), "search button is responsive", Toast.LENGTH_LONG).show();
    }

    //Unit tests method for setting button to check if setting button works using toasts

    public void settingClicked(View v) {

        Toast.makeText(getBaseContext(), "setting button is responsive", Toast.LENGTH_LONG).show();


    }


    //Unit tests to check if button is pushed or not during runtime
    //personal test method used while building the application.
    public boolean test_if_button_pushed() {
        boolean value = false;

        if (!testing) {
            value = false;
        } else {
            value = true;
        }

        return value;

    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Homepage Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
