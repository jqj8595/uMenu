//Test to see if this will commit

package com.umenu.umenu;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Feedback extends AppCompatActivity {
    private CheckBox service1, service2, service3, food1, food2, food3, value1, value2, value3, app1, app2, app3;
    private TextView charactersLeft;
    private EditText feedbackTextBox;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //feedbackFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/uMenu/FeedBack/";

        service1 = (CheckBox) findViewById(R.id.checkbox_serviceGood);
        service2 = (CheckBox) findViewById(R.id.checkbox_serviceOk);
        service3 = (CheckBox) findViewById(R.id.checkbox_serviceBad);
        food1 = (CheckBox) findViewById(R.id.checkbox_foodGood);
        food2 = (CheckBox) findViewById(R.id.checkbox_foodOk);
        food3 = (CheckBox) findViewById(R.id.checkbox_foodBad);
        value1 = (CheckBox) findViewById(R.id.checkbox_valueGood);
        value2 = (CheckBox) findViewById(R.id.checkbox_valueOk);
        value3 = (CheckBox) findViewById(R.id.checkbox_valueBad);
        app1 = (CheckBox) findViewById(R.id.checkbox_appGood);
        app2 = (CheckBox) findViewById(R.id.checkbox_appOk);
        app3 = (CheckBox) findViewById(R.id.checkbox_appBad);

        charactersLeft = (TextView) findViewById(R.id.textViewCharactersLeft);

        feedbackTextBox = (EditText) findViewById(R.id.userCommentBox);
        feedbackTextBox.addTextChangedListener(new commentCharactersLeft());

        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(doneListener);
        //Lock screen orientation to portrait. Its a menu ya dont read it side on
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        arrangeCheckBoxes(view);

    }
    private class commentCharactersLeft implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            charactersLeft.setText(String.valueOf(250 - s.length()) + " characters remaining");
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
    /**
     * Email for testing
     * email address: softwareproject@yandex.com
     * first/last names: software project
     * username: softwareproject
     * password: testing
     */
    private void sendEmail() {

    }
    /*
    private void saveToDevice(){
        String text ="\n";
        text = text+"service: "+returnChoice(service1, service2, service3)+"\n";
        text = text+"food: "+returnChoice(food1, food2, food3)+"\n";
        text = text+"value: "+returnChoice(value1, value2, value3)+"\n";
        text = text+"app: "+returnChoice(app1, app2, app3)+"\n";
        text = text+"user comment:\n"+feedbackTextBox.getText().toString()+"\n";
        text = text+feedbackTextBox.getText().toString();
        try{

            FileOutputStream fOut = openFileOutput("TEST_FILE1.txt", MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.write(text);
            osw.flush();
            osw.close();


            new File(feedbackFilePath).mkdir();
            File file = new File(feedbackFilePath+feedbackFileName);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(file, true);
            outStream.write(text.getBytes());
            outStream.close();
        }
        catch(FileNotFoundException e){
        }
        catch(IOException e){
        }
    }
    */
    private int returnChoice(CheckBox good, CheckBox ok, CheckBox bad) {
        if (good.isChecked()) {
            return 1;
        } else if (ok.isChecked()) {
            return 2;
        } else if (bad.isChecked()) {
            return 3;
        } else {
            return 0;
        }
    }

    private View.OnClickListener doneListener = new View.OnClickListener() {
        public void onClick(View v) {
            // Not working, opting for email
            //saveToDevice();

            // Not working as I screwed up my program doing it
            sendEmail();
        }
    };
    private void arrangeCheckBoxes(View compare) {
        if (compare.getId() == service1.getId() || compare.getId() == service2.getId() || compare.getId() == service3.getId()) {
            service1.setChecked(false);
            service2.setChecked(false);
            service3.setChecked(false);
        } else if (compare.getId() == food1.getId() || compare.getId() == food2.getId() || compare.getId() == food3.getId()) {
            food1.setChecked(false);
            food2.setChecked(false);
            food3.setChecked(false);
        } else if (compare.getId() == value1.getId() || compare.getId() == value2.getId() || compare.getId() == value3.getId()) {
            value1.setChecked(false);
            value2.setChecked(false);
            value3.setChecked(false);
        } else if (compare.getId() == app1.getId() || compare.getId() == app2.getId() || compare.getId() == app3.getId()) {
            app1.setChecked(false);
            app2.setChecked(false);
            app3.setChecked(false);
        }
        ((CheckBox) compare).setChecked(true);
    }

}

