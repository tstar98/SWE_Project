package com.example.thomas.prototypeoftheprototype;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ManagerRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_request);
        LinearLayout llayout = (LinearLayout) findViewById(R.id.ll);

        //while (there are still more requests)
        //{
            TextView rval; //formatted textView for each request

            //ADD CODE HERE FOR LOOKING UP TIME OFF REQUESTS, call addRequest method for each instance
            rval = addRequest("Leandra Adcock", "8/11/98", "Being born");
            if (llayout != null) {
                llayout.addView(rval);
            }
       // }
    }

    //this method takes in info from database for requests off and returns a textview in the correct format
    //which can then be added to the layout
    protected TextView addRequest(String name, String date, String reason)
    {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        String fullText = name + "\n" + date + "\n" + reason;
        textView.setText(fullText);
        return textView;

    }


}

