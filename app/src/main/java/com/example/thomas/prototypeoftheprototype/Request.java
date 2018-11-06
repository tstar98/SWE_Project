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

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Request extends AppCompatActivity {
    Calendar dateOff;
    String reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        dateOff = new GregorianCalendar(1970, 0, 31);
        CalendarView cv = (CalendarView)findViewById(R.id.calendar);
        final TextView tv = (TextView)findViewById(R.id.selectDate);

        //gets date selected by user
        cv.setOnDateChangeListener((new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                tv.setText(month + " / " + dayOfMonth + " / " + year);
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
            Toast toast = Toast.makeText(this, "Request Sent", Toast.LENGTH_LONG);
            toast.show();

            //Goes back to homescreen
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
    }





}
