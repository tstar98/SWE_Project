package com.example.thomas.prototypeoftheprototype;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Request extends AppCompatActivity {
    Calendar dateOff;
    private String reason, date;
    private FirebaseUser user;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        //database instances
        mData = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();

        dateOff = new GregorianCalendar(1970, 0, 31);
        CalendarView cv = (CalendarView)findViewById(R.id.calendar);
        final TextView tv = (TextView)findViewById(R.id.selectDate);

        //gets date selected by user
        cv.setOnDateChangeListener((new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = (month + 1) + " " + dayOfMonth + " " + year;
                tv.setText(date);
                dateOff.set(year, month, dayOfMonth);
            }
        }));
    }

    //error checking before its sent off
    public void next(View v){
        Calendar currentDate = Calendar.getInstance();
        boolean toNext = true;
        EditText editText= (EditText)findViewById(R.id.reason);
        reason = editText.getText().toString();

        //checks if date entered hasn't already passed
        if (!dateOff.after(currentDate)){
            toNext = false;

            Toast toast = Toast.makeText(this, "Enter a valid date", Toast.LENGTH_SHORT);
            toast.show();
        }

        //checks if reason field is empty
        if(reason.isEmpty()){
            toNext = false;
            editText.setError("");
        }

        if(toNext){
            //push onto database
            mData.child("Pending Requests").child(date).child(user.getUid()).child("Name").setValue(user.getDisplayName());
            mData.child("Pending Requests").child(date).child(user.getUid()).child("Reason").setValue(reason);

            Toast toast = Toast.makeText(this, "Request Sent", Toast.LENGTH_LONG);
            toast.show();

            //Goes back to homescreen
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
    }
}
