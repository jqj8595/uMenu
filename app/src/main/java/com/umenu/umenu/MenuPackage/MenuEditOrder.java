package com.umenu.umenu.MenuPackage;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.umenu.umenu.R;
import com.umenu.umenu.UserData;

import java.util.ArrayList;

public class MenuEditOrder extends AppCompatActivity {
    ArrayAdapter<FoodItem> adapter;

    FoodItem currentFoodItem;

    Button removeButton, orderButton;

    TextView currentFoodItemText;

    ListView orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_edit_order);

        this.removeButton = (Button)findViewById(R.id.menuEditOrderRemoveButton);
        this.orderButton = (Button)findViewById(R.id.menuEditOrderPlaceOrderButton);
        this.currentFoodItemText = (TextView)findViewById(R.id.menuEditOrderSelectionText);

        populateListView();
        setupClickListeners();
    }

    private void populateListView() {
        adapter = new CustomAdapter();
        this.orderList = (ListView) findViewById(R.id.menuEditOrderList);
        orderList.setAdapter(adapter);
    }
    private class CustomAdapter extends ArrayAdapter<FoodItem>{
        public CustomAdapter(){
            super(MenuEditOrder.this, R.layout.food_menu_view, UserData.getInstance().getOrder());
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.food_menu_view, parent, false);
            }

            FoodItem currentItem = UserData.getInstance().getOrder().get(position);

            ImageView imageView = (ImageView)itemView.findViewById(R.id.foodImage);
            //Adds the picture for the food item.
            imageView.setImageResource(currentItem.getImageID());
            //Adds the decription of the food item
            TextView foodDescription = (TextView)itemView.findViewById(R.id.foodDescription);
            foodDescription.setText(currentItem.getDescription());
            //Adds the food name
            TextView foodName = (TextView)itemView.findViewById(R.id.foodName);
            foodName.setText(currentItem.getName());
            foodName.setTextColor(ContextCompat.getColor(MenuEditOrder.this, R.color.orange));
            //Adds the price of the food item appended to the existing text "Price: \n$ "
            TextView foodPrice = (TextView)itemView.findViewById((R.id.foodPrice));
            foodPrice.setText("Food Price:\n"+currentItem.getPrice());

            return itemView;
            //return super.getView(position, convertView, parent);
        }
    }
    private void setupClickListeners(){
    //Food item select
        ListView itemList = (ListView)findViewById(R.id.menuEditOrderList);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentFoodItem = UserData.getInstance().getOrder().get(position);
                currentFoodItemText.setText("Food Choice: \n"+currentFoodItem.getName());
            }
        });

    //Place order button clicked
        orderButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //if no items in order
                if(UserData.getInstance().getOrder().size() <1){

                    Toast.makeText(getApplicationContext(),"There are no items in the order",Toast.LENGTH_SHORT).show();
                }
                //items must be in order so send to database
                else{
                    //Send to database
                    Toast.makeText(getApplicationContext(),"Sending to database",Toast.LENGTH_SHORT).show();
                }

            }
        });
    //Remove Button clicked
        removeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //If there is no selected item or the selected item is not in the order any more
                if(currentFoodItem == null || !UserData.getInstance().getOrder().contains(currentFoodItem)){
                    Toast.makeText(getApplicationContext(),"No item selected",Toast.LENGTH_SHORT).show();
                }
                else{
                    //itterates through the current order to find the selected item
                    for(int i = 0; i <UserData.getInstance().getOrder().size(); i++){
                        //checks
                        if(UserData.getInstance().getOrder().get(i).getName().equals(currentFoodItem.getName())){
                            UserData.getInstance().getOrder().remove(i);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    currentFoodItemText.setText("Food Choice: \nNothing Selected");
                }


            }
        });

    }
}
