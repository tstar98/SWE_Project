package com.example.thomas.prototypeoftheprototype;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
    AlertDialog ad;

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
                openDialog(sw, tv);
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
    public void openDialog(final ArrayList<Switch> sw, final ArrayList<TextView> tv) {

        ad = new AlertDialog.Builder(this).create();
        TextView title = new TextView(this);
        title.setText("Request Warning!");
        title.setPadding(10, 10, 10, 10);   // Set Position
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        ad.setCustomTitle(title);

        TextView msg = new TextView(this);
        msg.setText("Any requests that were not approved will be deleted. \n Do you wish to continue?");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        ad.setView(msg);

        ad.setButton(AlertDialog.BUTTON_NEUTRAL,"SUBMIT", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < sw.size(); i++)
                {
                    String info = tv.get(i).getText().toString();
                    String infolist[] = info.split("\n");
                    infolist[2] = infolist[2].replace('-', ' ');

                    if (sw.get(i).isChecked())
                    {
                        mData.child("Approved Requests").child(infolist[2]).child(infolist[1]).child("Name").setValue(infolist[1]);
                        mData.child("Approved Requests").child(infolist[2]).child(infolist[1]).child("Date").setValue(infolist[2]);
                        mData.child("Approved Requests").child(infolist[2]).child(infolist[1]).child("Reason").setValue(infolist[2]);
                    }

                    mData.child("Pending Requests").child(infolist[2]).child(infolist[1]).child("Name").removeValue();
                    mData.child("Pending Requests").child(infolist[2]).child(infolist[1]).child("Date").removeValue();
                }
                startActivity(new Intent(getApplicationContext(), ManagerHomeScreen.class));
                Toast.makeText(getApplicationContext(), "Requests Updated", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ManagerRequest.this, ManagerHomeScreen.class));
            }
        });

        ad.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ad.cancel();
            }
        });

        new Dialog(getApplicationContext());
        ad.show();
        final Button okBT = ad.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        okBT.setPadding(50, 10, 10, 10);   // Set Position
        okBT.setTextColor(Color.BLUE);
        okBT.setLayoutParams(neutralBtnLP);

        final Button cancelBT = ad.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams negBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        negBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        cancelBT.setTextColor(Color.RED);
        cancelBT.setLayoutParams(negBtnLP);
    }


}

