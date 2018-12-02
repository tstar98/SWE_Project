package com.example.thomas.prototypeoftheprototype;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Contacts extends AppCompatActivity {
    private DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        final LinearLayout contactlayout = (LinearLayout) findViewById(R.id.cLayout);
        mData = FirebaseDatabase.getInstance().getReference().child("Contacts");
        mData.addValueEventListener(new ValueEventListener()
        {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    TextView rval = addContact(snapshot.child("Name").getValue(String.class), snapshot.child("Phone").getValue(String.class));
                    //if (contactlayout != null) {
                        contactlayout.addView(rval);
                    //}
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });


    }


    //this method takes in info from database for contacts and returns a textview in the correct format
    //which can then be added to the layout
    protected TextView addContact(String name, String number) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        StringBuilder nhyphen = new StringBuilder(number);
        nhyphen.insert(3, "-");
        nhyphen.insert(7,"-");
        String fullText = name + "\n" + nhyphen + "\n" + "\n";
        SpannableString ss1=  new SpannableString(fullText);
        ss1.setSpan(new RelativeSizeSpan(2f), 0,name.length(), 0); // set size
        ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, name.length(), 0);// set color
        textView.setText(ss1);
        return textView;

    }
}

