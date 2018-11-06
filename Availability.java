package com.example.thomas.prototypeoftheprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import java.util.ArrayList;

public class Availability extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        //the following are check boxes for availability
        final CheckBox mon1 = findViewById(R.id.mon1);
        final CheckBox mon2 = findViewById(R.id.mon2);
        final CheckBox mon3 = findViewById(R.id.mon3);
        final CheckBox mon4 = findViewById(R.id.mon4);
        final CheckBox tues1 = findViewById(R.id.tues1);
        final CheckBox tues2 = findViewById(R.id.tues2);
        final CheckBox tues3 = findViewById(R.id.tues3);
        final CheckBox tues4 = findViewById(R.id.tues4);
        final CheckBox wed1 = findViewById(R.id.wed1);
        final CheckBox wed2 = findViewById(R.id.wed2);
        final CheckBox wed3 = findViewById(R.id.wed3);
        final CheckBox wed4 = findViewById(R.id.wed4);
        final CheckBox thurs1 = findViewById(R.id.thurs1);
        final CheckBox thurs2 = findViewById(R.id.thurs2);
        final CheckBox thurs3 = findViewById(R.id.thurs3);
        final CheckBox thurs4 = findViewById(R.id.thurs4);
        final CheckBox fri1 = findViewById(R.id.fri1);
        final CheckBox fri2 = findViewById(R.id.fri2);
        final CheckBox fri3 = findViewById(R.id.fri3);
        final CheckBox fri4 = findViewById(R.id.fri4);
        final CheckBox sat1 = findViewById(R.id.sat1);
        final CheckBox sat2 = findViewById(R.id.sat2);
        final CheckBox sat3 = findViewById(R.id.sat3);
        final CheckBox sat4 = findViewById(R.id.sat4);
        final CheckBox sun1 = findViewById(R.id.sun1);
        final CheckBox sun2 = findViewById(R.id.sun2);
        final CheckBox sun3 = findViewById(R.id.sun3);
        final CheckBox sun4 = findViewById(R.id.sun4);

        final Button submit = findViewById(R.id.submitAvailability);
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override

            //when submitting availability, create an arraylist for each day\
            //and take in availability based on checked boxes
            //then at end add each arraylist to master arraylist for full weekly availability
            public void onClick(View v)
            {
                ArrayList<ArrayList<Boolean>> availability = new ArrayList<>(7); //master list of weekly availability
                //individual day availability, day[0] = shift 1, day[1]= shift 2, and so on
                ArrayList<Boolean> monday = new ArrayList<Boolean>(4);
                ArrayList<Boolean> tuesday = new ArrayList<Boolean>(4);
                ArrayList<Boolean> wednesday = new ArrayList<Boolean>(4);
                ArrayList<Boolean> thursday = new ArrayList<Boolean>(4);
                ArrayList<Boolean> friday = new ArrayList<Boolean>(4);
                ArrayList<Boolean> saturday = new ArrayList<Boolean>(4);
                ArrayList<Boolean> sunday = new ArrayList<Boolean>(4);

                //filling Monday availability
                if (mon1.isChecked()) monday.add(0, true);
                else if (!mon1.isChecked()) monday.add(0, false);
                if (mon2.isChecked()) monday.add(1, true);
                else if (!mon2.isChecked()) monday.add(1, false);
                if (mon3.isChecked()) monday.add(2, true);
                else if (!mon3.isChecked()) monday.add(2, false);
                if (mon4.isChecked()) monday.add(3, true);
                else if (!mon4.isChecked()) monday.add(3, false);

                //filling Tuesday availability
                if (tues1.isChecked()) tuesday.add(0, true);
                else if (!tues1.isChecked()) tuesday.add(0, false);
                if (tues2.isChecked()) tuesday.add(1, true);
                else if (!tues2.isChecked()) tuesday.add(1, false);
                if (tues3.isChecked()) tuesday.add(2, true);
                else if (!tues3.isChecked()) tuesday.add(2, false);
                if (tues4.isChecked()) tuesday.add(3, true);
                else if (!tues4.isChecked()) tuesday.add(3, false);

                //filling Wednesday availability
                if (wed1.isChecked()) wednesday.add(0, true);
                else if (!wed1.isChecked()) wednesday.add(0, false);
                if (wed2.isChecked()) wednesday.add(1, true);
                else if (!wed2.isChecked()) wednesday.add(1, false);
                if (wed3.isChecked()) wednesday.add(2, true);
                else if (!wed3.isChecked()) wednesday.add(2, false);
                if (wed4.isChecked()) wednesday.add(3, true);
                else if (!wed4.isChecked()) wednesday.add(3, false);

                //filling Thursday availability
                if (thurs1.isChecked()) thursday.add(0, true);
                else if (!thurs1.isChecked()) thursday.add(0, false);
                if (thurs2.isChecked()) thursday.add(1, true);
                else if (!thurs2.isChecked()) thursday.add(1, false);
                if (thurs3.isChecked()) thursday.add(2, true);
                else if (!thurs3.isChecked()) thursday.add(2, false);
                if (thurs4.isChecked()) thursday.add(3, true);
                else if (!thurs4.isChecked()) thursday.add(3, false);

                //filling Friday availability
                if (fri1.isChecked()) friday.add(0, true);
                else if (!fri1.isChecked()) friday.add(0, false);
                if (fri2.isChecked()) friday.add(1, true);
                else if (!fri2.isChecked()) friday.add(1, false);
                if (fri3.isChecked()) friday.add(2, true);
                else if (!fri3.isChecked()) friday.add(2, false);
                if (fri4.isChecked()) friday.add(3, true);
                else if (!fri4.isChecked()) friday.add(3, false);

                //filling Saturday availability
                if (sat1.isChecked()) saturday.add(0, true);
                else if (!sat1.isChecked()) saturday.add(0, false);
                if (sat2.isChecked()) saturday.add(1, true);
                else if (!sat2.isChecked()) saturday.add(1, false);
                if (sat3.isChecked()) saturday.add(2, true);
                else if (!sat3.isChecked()) saturday.add(2, false);
                if (sat4.isChecked()) saturday.add(3, true);
                else if (!sat4.isChecked()) saturday.add(3, false);

                //filling Sunday availability
                if (sun1.isChecked()) sunday.add(0, true);
                else if (!sun1.isChecked()) sunday.add(0, false);
                if (sun2.isChecked()) sunday.add(1, true);
                else if (!sun2.isChecked()) sunday.add(1, false);
                if (sun3.isChecked()) sunday.add(2, true);
                else if (!sun3.isChecked()) sunday.add(2, false);
                if (sun4.isChecked()) sunday.add(3, true);
                else if (!sun4.isChecked()) sunday.add(3, false);

                //filling weekly availability
                availability.add(0,monday);
                availability.add(1,tuesday);
                availability.add(2,wednesday);
                availability.add(3,thursday);
                availability.add(4,friday);
                availability.add(5,saturday);
                availability.add(6,sunday);

                StringBuilder btn = new StringBuilder();
                //loop to send out info to storage (database??)
                for (int i = 0; i < availability.size(); i++)
                {
                    btn.append("On ");
                    if (i==0) btn.append("Monday ");
                    else if (i==1) btn.append("Tuesday ");
                    else if (i==2) btn.append("Wednesday ");
                    else if (i==3) btn.append("Thursday ");
                    else if (i==4) btn.append("Friday ");
                    else if (i==5) btn.append("Saturday ");
                    else if (i==6) btn.append("Sunday ");
                    ArrayList<Boolean> inner = availability.get(i);
                    btn.append("you are available for: ");
                    for (int j = 0; j < 4; j++)
                    {
                        int shiftnum = j + 1;
                        if (inner.get(j) == true)
                        {
                            btn.append("Shift " + shiftnum + ", ");
                        }

                    }
                }


                submit.setText(btn);

            }
        });


    }
}
