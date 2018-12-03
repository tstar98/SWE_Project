package com.example.thomas.prototypeoftheprototype;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ManagerRequest extends AppCompatActivity {
    private DatabaseReference mData;
    private LinearLayout llayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_request);
        llayout = (LinearLayout) findViewById(R.id.ll);
        mData = FirebaseDatabase.getInstance().getReference();
        final ArrayList<TextView> tv = new ArrayList<TextView>();
        final ArrayList<Switch> sw = new ArrayList<Switch>();

        mData.child("Pending Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    for (DataSnapshot data1 : data.getChildren()){
                        String name = data1.child("Name").getValue(String.class);
                        String date = data1.child("Date").getValue(String.class);
                        String reason = data1.child("Reason").getValue(String.class);

                        TextView rval; //formatted textView for each request

                        rval = addRequest(name, date, reason);
                        if (llayout != null) {
                            llayout.addView(rval);
                            tv.add(rval);
                            Switch appdeny = new Switch(getApplicationContext());
                            appdeny.setTextSize(25);
                            appdeny.setSwitchTextAppearance(getApplicationContext(), R.style.x);
                            appdeny.setTextOn("Approve");
                            appdeny.setTextOff("Deny");
                            appdeny.setShowText(true);
                            llayout.addView(appdeny);
                            sw.add(appdeny);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
        final Button submit = new Button(this);
        submit.setText("Submit");
        llayout.addView(submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < sw.size(); i++)
                {
                    if (sw.get(i).isChecked())
                    {

                    }
                    else
                    {

                    }
                }
            }
        });
    }

    //this method takes in info from database for requests off and returns a textview in the correct format
    //which can then be added to the layout
    protected TextView addRequest(String name, String date, String reason)
    {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        StringBuilder datehyphen = new StringBuilder(date);
        for (int i = 0; i < datehyphen.length(); i++)
        {
            if (datehyphen.charAt(i) == ' ')
                datehyphen.setCharAt(i, '-');

        }
        String fullText = "\n" + name + "\n" + datehyphen + "\n" + "Reason: " + reason + "\n";
        SpannableString ss1=  new SpannableString(fullText);
        ss1.setSpan(new RelativeSizeSpan(2f), 0,fullText.length(), 0); // set size
        textView.setText(ss1);
        return textView;

    }


}

