package com.umenu.umenu;

/**
 * Created by nishanjayetileke on 11/10/16.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    JSONObject response, profile_pic_data, profile_pic_url;
    TextView user_name, user_email;
    ImageView user_picture;
    NavigationView navigation_view;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    int REQUEST_CAMERA =0, SELECT_FILE =1;




    //main running method that is automatically calls during runtime of the application
    @Override
    synchronized protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_homepage);
        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("jsondata");
        shareDialog = new ShareDialog(this);










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


    /**
     * method initializes facebook sdk and creates object for callbackmanager.
     */
    private void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

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


    private void selectImage(){
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Homepage.this);
        builder.setTitle("Select Profile Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(items[item].equals("Take Photo")){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);


                }
                else if (items[item].equals("Choose from Library")){
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"), SELECT_FILE);

                }
                else if(items[item].equals("Cancel")){
                    dialog.dismiss();;
                }

            }
        });
        builder.show();
    }



    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA){
                onCaptureImageResult(data);
            }

        }
    }


    /**
     * select photo from gallery
     * @param data
     */
    private void onSelectFromGalleryResult(Intent data){
        Uri selectedImageUri = data.getData();
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);
        Bitmap thumbnail;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int Required_Size = 200;
        int scale =1;
        while(options.outWidth/scale/2>=Required_Size &&
                options.outHeight/scale/ 2>= Required_Size){
            scale *=2;
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            thumbnail = BitmapFactory.decodeFile(selectedImagePath, options);

            sharePhotoToFacebook(thumbnail);
        }


    }


    /**
     * take a photo and activate the camera on the device to upload photo
     * to facebook profile.
     * @param data
     */

    private void onCaptureImageResult(Intent data){
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try{
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sharePhotoToFacebook(thumbnail);


    }


    private void sharePhotoToFacebook(Bitmap imagePath) {

        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(imagePath)
                .setCaption("Nice Place.....")
                .build();

        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        shareDialog.show(content);

        ShareApi.share(content, null);
        testing_if_sharing_successful();

        Toast.makeText(Homepage.this, "Facebook Photo Upload Completed", Toast.LENGTH_LONG).show();

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
                selectImage();


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




    //Unit testing out puts successful to logcat if sharing is successful.
    public void testing_if_sharing_successful(){
        Log.e("Sharing","Successful");


    }


    //Unit tests to check if button is pushed or not during runtime
    //personal test method used while building the application.
    public boolean test_if_button_pushed() {
        boolean value = false;

        value = testing;

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
