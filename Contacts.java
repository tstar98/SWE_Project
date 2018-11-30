package com.example.thomas.prototypeoftheprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        LinearLayout contactlayout = (LinearLayout) findViewById(R.id.cLayout);

        //while (there are still more requests)
        //{
        TextView rval; //formatted textView for each request

        //ADD CODE HERE FOR LOOKING UP TIME OFF REQUESTS, call addRequest method for each instance
        rval = addContact("Leandra Adcock", "850-123-4567");
        if (contactlayout != null) {
            contactlayout.addView(rval);
        }
        // }
    }

    //this method takes in info from database for contacts and returns a textview in the correct format
    //which can then be added to the layout
    protected TextView addContact(String name, String number) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        String fullText = name + "\n" + number + "\n";
        textView.setText(fullText);
        return textView;

    }
}

