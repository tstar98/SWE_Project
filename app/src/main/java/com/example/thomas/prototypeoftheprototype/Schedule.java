package com.example.thomas.prototypeoftheprototype;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Schedule extends AppCompatActivity {

    private static final String TAG = "Schedule";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        String date = new SimpleDateFormat("MM dd yyyy").format(Calendar.getInstance().getTime());

        String datelist[] = date.split(" ");
        final String month = datelist[0];
        final String day = datelist[1];
        final String year = datelist[2];

        final DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        updateSchedule(mData.child("Next Week"), mData.child("This Week"));

        //checks if date of next week's schedule has already passed
        mData.child("Next Week").child("Start Date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String nextDate = dataSnapshot.getValue(String.class);
                String dateList[] = nextDate.split(" ");
                String m = dateList[0];
                String d = dateList[1];
                String y = dateList[2];

                if(Integer.parseInt(year) > Integer.parseInt(y))
                    updateSchedule(mData.child("Next Week"), mData.child("This Week"));
                else if (Integer.parseInt(year) == Integer.parseInt(y)){
                    if (Integer.parseInt(month) > Integer.parseInt(m))
                        updateSchedule(mData.child("Next Week"), mData.child("This Week"));
                    else if (Integer.parseInt(month) == Integer.parseInt(m) && Integer.parseInt(d) > Integer.parseInt(d))
                        updateSchedule(mData.child("Next Week"), mData.child("This Week"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        /* ********** Monday ********** */
        mData.child("This Week").child("Monday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> shifts = new ArrayList<>();

                for (DataSnapshot i : dataSnapshot.getChildren())
                    for (DataSnapshot j : i.getChildren())
                        shifts.add(j.getValue().toString());

                TextView text = findViewById(R.id.monShift1a);
                text.setText(shifts.get(0));
                text = findViewById(R.id.monShift1b);
                text.setText(shifts.get(1));
                text = findViewById(R.id.monShift2a);
                text.setText(shifts.get(2));
                text = findViewById(R.id.monShift2b);
                text.setText(shifts.get(3));
                text = findViewById(R.id.monShift3a);
                text.setText(shifts.get(4));
                text = findViewById(R.id.monShift3b);
                text.setText(shifts.get(5));
                text = findViewById(R.id.monShift4a);
                text.setText(shifts.get(6));
                text = findViewById(R.id.monShift4b);
                text.setText(shifts.get(7));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        /* ********** Tuesday ********** */
        mData.child("This Week").child("Tuesday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> shifts = new ArrayList<>();

                for (DataSnapshot i : dataSnapshot.getChildren())
                    for (DataSnapshot j : i.getChildren())
                        shifts.add(j.getValue().toString());

                TextView text = findViewById(R.id.tuesShift1a);
                text.setText(shifts.get(0));
                text = findViewById(R.id.tuesShift1b);
                text.setText(shifts.get(1));
                text = findViewById(R.id.tuesShift2a);
                text.setText(shifts.get(2));
                text = findViewById(R.id.tuesShift2b);
                text.setText(shifts.get(3));
                text = findViewById(R.id.tuesShift3a);
                text.setText(shifts.get(4));
                text = findViewById(R.id.tuesShift3b);
                text.setText(shifts.get(5));
                text = findViewById(R.id.tuesShift4a);
                text.setText(shifts.get(6));
                text = findViewById(R.id.tuesShift4b);
                text.setText(shifts.get(7));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        /* ********** Wednesday ********** */
        mData.child("This Week").child("Wednesday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> shifts = new ArrayList<>();

                for (DataSnapshot i : dataSnapshot.getChildren())
                    for (DataSnapshot j : i.getChildren())
                        shifts.add(j.getValue().toString());

                TextView text = findViewById(R.id.wedShift1a);
                text.setText(shifts.get(0));
                text = findViewById(R.id.wedShift1b);
                text.setText(shifts.get(1));
                text = findViewById(R.id.wedShift2a);
                text.setText(shifts.get(2));
                text = findViewById(R.id.wedShift2b);
                text.setText(shifts.get(3));
                text = findViewById(R.id.wedShift3a);
                text.setText(shifts.get(4));
                text = findViewById(R.id.wedShift3b);
                text.setText(shifts.get(5));
                text = findViewById(R.id.wedShift4a);
                text.setText(shifts.get(6));
                text = findViewById(R.id.wedShift4b);
                text.setText(shifts.get(7));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        /* ********** Thursday ********** */
        mData.child("This Week").child("Thursday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> shifts = new ArrayList<>();

                for (DataSnapshot i : dataSnapshot.getChildren())
                    for (DataSnapshot j : i.getChildren())
                        shifts.add(j.getValue().toString());

                TextView text = findViewById(R.id.monShift1a);
                text.setText(shifts.get(0));
                text = findViewById(R.id.thursShift1b);
                text.setText(shifts.get(1));
                text = findViewById(R.id.thursShift2a);
                text.setText(shifts.get(2));
                text = findViewById(R.id.thursShift2b);
                text.setText(shifts.get(3));
                text = findViewById(R.id.thursShift3a);
                text.setText(shifts.get(4));
                text = findViewById(R.id.thursShift3b);
                text.setText(shifts.get(5));
                text = findViewById(R.id.thursShift4a);
                text.setText(shifts.get(6));
                text = findViewById(R.id.thursShift4b);
                text.setText(shifts.get(7));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        /* ********** Friday ********** */
        mData.child("This Week").child("Friday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> shifts = new ArrayList<>();

                for (DataSnapshot i : dataSnapshot.getChildren())
                    for (DataSnapshot j : i.getChildren())
                        shifts.add(j.getValue().toString());

                TextView text = findViewById(R.id.monShift1a);
                text.setText(shifts.get(0));
                text = findViewById(R.id.friShift1b);
                text.setText(shifts.get(1));
                text = findViewById(R.id.friShift2a);
                text.setText(shifts.get(2));
                text = findViewById(R.id.friShift2b);
                text.setText(shifts.get(3));
                text = findViewById(R.id.friShift3a);
                text.setText(shifts.get(4));
                text = findViewById(R.id.friShift3b);
                text.setText(shifts.get(5));
                text = findViewById(R.id.friShift4a);
                text.setText(shifts.get(6));
                text = findViewById(R.id.friShift4b);
                text.setText(shifts.get(7));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        /* ********** Saturday ********** */
        mData.child("This Week").child("Saturday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> shifts = new ArrayList<>();

                for (DataSnapshot i : dataSnapshot.getChildren())
                    for (DataSnapshot j : i.getChildren())
                        shifts.add(j.getValue().toString());

                TextView text = findViewById(R.id.satShift1a);
                text.setText(shifts.get(0));
                text = findViewById(R.id.satShift1b);
                text.setText(shifts.get(1));
                text = findViewById(R.id.satShift2a);
                text.setText(shifts.get(2));
                text = findViewById(R.id.satShift2b);
                text.setText(shifts.get(3));
                text = findViewById(R.id.satShift3a);
                text.setText(shifts.get(4));
                text = findViewById(R.id.satShift3b);
                text.setText(shifts.get(5));
                text = findViewById(R.id.satShift4a);
                text.setText(shifts.get(6));
                text = findViewById(R.id.satShift4b);
                text.setText(shifts.get(7));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        /* ********** Sunday ********** */
        mData.child("This Week").child("Sunday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> shifts = new ArrayList<>();

                for (DataSnapshot i : dataSnapshot.getChildren())
                    for (DataSnapshot j : i.getChildren())
                        shifts.add(j.getValue().toString());

                TextView text = findViewById(R.id.monShift1a);
                text.setText(shifts.get(0));
                text = findViewById(R.id.monShift1b);
                text.setText(shifts.get(1));
                text = findViewById(R.id.monShift2a);
                text.setText(shifts.get(2));
                text = findViewById(R.id.monShift2b);
                text.setText(shifts.get(3));
                text = findViewById(R.id.monShift3a);
                text.setText(shifts.get(4));
                text = findViewById(R.id.monShift3b);
                text.setText(shifts.get(5));
                text = findViewById(R.id.monShift4a);
                text.setText(shifts.get(6));
                text = findViewById(R.id.monShift4b);
                text.setText(shifts.get(7));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    //moves next week's schedule to this week
    //obtained from https://stackoverflow.com/questions/40456443/how-to-move-firebase-child-from-one-node-to-another-in-android
    protected void updateSchedule(final DatabaseReference fromPath, final DatabaseReference toPath) {
        fromPath.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toPath.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                        if (firebaseError != null) {
                            System.out.println("Copy failed");
                        } else {
                            System.out.println("Success");
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();;
            }
        });
    }
}
