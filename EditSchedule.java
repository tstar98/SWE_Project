package com.example.thomas.prototypeoftheprototype;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class EditSchedule extends AppCompatActivity {

    private DatabaseReference mData;

    //Have to have an array list for each shift because the reading from the database is not linear
    private ArrayList<String> shiftm1 = new ArrayList<>();
    private ArrayList<String> shiftm2 = new ArrayList<>();
    private ArrayList<String> shiftm3 = new ArrayList<>();
    private ArrayList<String> shiftm4 = new ArrayList<>();
    private ArrayList<String> shiftt1 = new ArrayList<>();
    private ArrayList<String> shiftt2 = new ArrayList<>();
    private ArrayList<String> shiftt3 = new ArrayList<>();
    private ArrayList<String> shiftt4 = new ArrayList<>();
    private ArrayList<String> shiftw1 = new ArrayList<>();
    private ArrayList<String> shiftw2 = new ArrayList<>();
    private ArrayList<String> shiftw3 = new ArrayList<>();
    private ArrayList<String> shiftw4 = new ArrayList<>();
    private ArrayList<String> shiftr1 = new ArrayList<>();
    private ArrayList<String> shiftr2 = new ArrayList<>();
    private ArrayList<String> shiftr3 = new ArrayList<>();
    private ArrayList<String> shiftr4 = new ArrayList<>();
    private ArrayList<String> shiftf1 = new ArrayList<>();
    private ArrayList<String> shiftf2 = new ArrayList<>();
    private ArrayList<String> shiftf3 = new ArrayList<>();
    private ArrayList<String> shiftf4 = new ArrayList<>();
    private ArrayList<String> shifts1 = new ArrayList<>();
    private ArrayList<String> shifts2 = new ArrayList<>();
    private ArrayList<String> shifts3 = new ArrayList<>();
    private ArrayList<String> shifts4 = new ArrayList<>();
    private ArrayList<String> shiftu1 = new ArrayList<>();
    private ArrayList<String> shiftu2 = new ArrayList<>();
    private ArrayList<String> shiftu3 = new ArrayList<>();
    private ArrayList<String> shiftu4 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);

        final Button submit = findViewById(R.id.submitSchedule);

        final Spinner m1_1 = findViewById(R.id.emp1_1);
        final Spinner m1_2 = findViewById(R.id.emp1_2);
        final Spinner m2_1 = findViewById(R.id.emp1_3);
        final Spinner m2_2 = findViewById(R.id.emp1_4);
        final Spinner m3_1 = findViewById(R.id.emp1_5);
        final Spinner m3_2 = findViewById(R.id.emp1_6);
        final Spinner m4_1 = findViewById(R.id.emp1_7);
        final Spinner m4_2 = findViewById(R.id.emp1_8);
        final Spinner t1_1 = findViewById(R.id.emp2_1);
        final Spinner t1_2 = findViewById(R.id.emp2_2);
        final Spinner t2_1 = findViewById(R.id.emp2_3);
        final Spinner t2_2 = findViewById(R.id.emp2_4);
        final Spinner t3_1 = findViewById(R.id.emp2_5);
        final Spinner t3_2 = findViewById(R.id.emp2_6);
        final Spinner t4_1 = findViewById(R.id.emp2_7);
        final Spinner t4_2 = findViewById(R.id.emp2_8);
        final Spinner w1_1 = findViewById(R.id.emp3_1);
        final Spinner w1_2 = findViewById(R.id.emp3_2);
        final Spinner w2_1 = findViewById(R.id.emp3_3);
        final Spinner w2_2 = findViewById(R.id.emp3_4);
        final Spinner w3_1 = findViewById(R.id.emp3_5);
        final Spinner w3_2 = findViewById(R.id.emp3_6);
        final Spinner w4_1 = findViewById(R.id.emp3_7);
        final Spinner w4_2 = findViewById(R.id.emp3_8);
        final Spinner r1_1 = findViewById(R.id.emp4_1);
        final Spinner r1_2 = findViewById(R.id.emp4_2);
        final Spinner r2_1 = findViewById(R.id.emp4_3);
        final Spinner r2_2 = findViewById(R.id.emp4_4);
        final Spinner r3_1 = findViewById(R.id.emp4_5);
        final Spinner r3_2 = findViewById(R.id.emp4_6);
        final Spinner r4_1 = findViewById(R.id.emp4_7);
        final Spinner r4_2 = findViewById(R.id.emp4_8);
        final Spinner f1_1 = findViewById(R.id.emp5_1);
        final Spinner f1_2 = findViewById(R.id.emp5_2);
        final Spinner f2_1 = findViewById(R.id.emp5_3);
        final Spinner f2_2 = findViewById(R.id.emp5_4);
        final Spinner f3_1 = findViewById(R.id.emp5_5);
        final Spinner f3_2 = findViewById(R.id.emp5_6);
        final Spinner f4_1 = findViewById(R.id.emp5_7);
        final Spinner f4_2 = findViewById(R.id.emp5_8);
        final Spinner s1_1 = findViewById(R.id.emp6_1);
        final Spinner s1_2 = findViewById(R.id.emp6_2);
        final Spinner s2_1 = findViewById(R.id.emp6_3);
        final Spinner s2_2 = findViewById(R.id.emp6_4);
        final Spinner s3_1 = findViewById(R.id.emp6_5);
        final Spinner s3_2 = findViewById(R.id.emp6_6);
        final Spinner s4_1 = findViewById(R.id.emp6_7);
        final Spinner s4_2 = findViewById(R.id.emp6_8);
        final Spinner u1_1 = findViewById(R.id.emp7_1);
        final Spinner u1_2 = findViewById(R.id.emp7_2);
        final Spinner u2_1 = findViewById(R.id.emp7_3);
        final Spinner u2_2 = findViewById(R.id.emp7_4);
        final Spinner u3_1 = findViewById(R.id.emp7_5);
        final Spinner u3_2 = findViewById(R.id.emp7_6);
        final Spinner u4_1 = findViewById(R.id.emp7_7);
        final Spinner u4_2 = findViewById(R.id.emp7_8);

        mData = FirebaseDatabase.getInstance().getReference().child("Availability");

        /* ************************** Monday shifts ************************** */
        //shift 1
        mData.child("Monday").child("Shift1").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    shiftm1.add(snapshot.getValue(String.class));
                }
            }

            //Error message to Logcat if an error occurs while retrieving data
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        //Puts data in spinner
        shiftm1.add("Select Employee");
        ArrayAdapter<String> adapterm1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shiftm1);
        adapterm1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m1_1.setAdapter(adapterm1);
        m1_2.setAdapter(adapterm1);


        //shift 2
        mData.child("Monday").child("Shift2").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftm2.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftm2.add("Select Employee");
        ArrayAdapter<String> adapterm2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftm2);
        adapterm2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m2_1.setAdapter(adapterm2);
        m2_2.setAdapter(adapterm2);


        //shift 3
        mData.child("Monday").child("Shift3").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftm3.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftm3.add("Select Employee");
        ArrayAdapter<String>adapterm3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftm3);
        adapterm3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m3_1.setAdapter(adapterm3);
        m3_2.setAdapter(adapterm3);


        //shift 4
        mData.child("Monday").child("Shift4").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftm4.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftm4.add("Select Employee");
        ArrayAdapter<String>adapterm4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftm4);
        adapterm4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m4_1.setAdapter(adapterm4);
        m4_2.setAdapter(adapterm4);


        /* ************************** Tuesday ************************** */
        //shift 1
        mData.child("Tuesday").child("Shift1").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftt1.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftt1.add("Select Employee");
        ArrayAdapter<String>adaptert1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftt1);
        adaptert1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        t1_1.setAdapter(adaptert1);
        t1_2.setAdapter(adaptert1);


        //shift 2
        mData.child("Tuesday").child("Shift2").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftt2.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftt2.add("Select Employee");
        ArrayAdapter<String>adaptert2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftt2);
        adaptert2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        t2_1.setAdapter(adaptert2);
        t2_2.setAdapter(adaptert2);


        //shift 3
        mData.child("Tuesday").child("Shift3").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftt3.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftt3.add("Select Employee");
        ArrayAdapter<String>adaptert3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftt3);
        adaptert3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        t3_1.setAdapter(adaptert3);
        t3_2.setAdapter(adaptert3);

        //shift 4
        mData.child("Tuesday").child("Shift4").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftt4.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftt4.add("Select Employee");
        ArrayAdapter<String>adaptert4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftt4);
        adaptert4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        t4_1.setAdapter(adaptert4);
        t4_2.setAdapter(adaptert4);

        /* ************************* Wednesday ****************************** */
        //shift 1
        mData.child("Wednesday").child("Shift1").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftw1.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftw1.add("Select Employee");
        ArrayAdapter<String> adapterw1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftw1);
        adapterw1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        w1_1.setAdapter(adapterw1);
        w1_2.setAdapter(adapterw1);

        //shift 2
        mData.child("Wednesday").child("Shift2").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftw2.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftw2.add("Select Employee");
        ArrayAdapter<String>adapterw2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftw2);
        adapterw2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        w2_1.setAdapter(adapterw2);
        w2_2.setAdapter(adapterw2);


        //shift 3
        mData.child("Wednesday").child("Shift3").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftw3.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftw3.add("Select Employee");
        ArrayAdapter<String>adapterw3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftw3);
        adapterw2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        w3_1.setAdapter(adapterw3);
        w3_2.setAdapter(adapterw3);


        //shift 4
        mData.child("Wednesday").child("Shift4").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftw4.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftw4.add("Select Employee");
        ArrayAdapter<String>adapterw4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftw4);
        adapterw2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        w4_1.setAdapter(adapterw4);
        w4_2.setAdapter(adapterw4);


        /* ********************* Thursday ********************** */
        //Shift 1
        mData.child("Thursday").child("Shift1").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftr1.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftr1.add("Select Employee");
        ArrayAdapter<String>adapterr1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftr1);
        adapterr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        r1_1.setAdapter(adapterr1);
        r1_2.setAdapter(adapterr1);

        //Shift 2
        mData.child("Thursday").child("Shift2").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftr2.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftr2.add("Select Employee");
        ArrayAdapter<String>adapterr2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftr2);
        adapterr2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        r2_1.setAdapter(adapterr2);
        r2_2.setAdapter(adapterr2);


        //Shift 3
        mData.child("Thursday").child("Shift3").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftr3.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftr3.add("Select Employee");
        ArrayAdapter<String>adapterr3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftr3);
        adapterr3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        r3_1.setAdapter(adapterr3);
        r3_2.setAdapter(adapterr3);


        //Shift 4
        mData.child("Thursday").child("Shift4").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftr4.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftr4.add("Select Employee");
        ArrayAdapter<String>adapterr4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftr4);
        adapterr4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        r4_1.setAdapter(adapterr4);
        r4_2.setAdapter(adapterr4);

        /* ************************ Friday *********************** */
        //Shift 1
        mData.child("Friday").child("Shift1").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftf1.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftf1.add("Select Employee");
        ArrayAdapter<String>adapterf1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftf1);
        adapterf1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        f1_1.setAdapter(adapterf1);
        f1_2.setAdapter(adapterf1);


        //Shift 2
        mData.child("Friday").child("Shift2").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftf2.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftf2.add("Select Employee");
        ArrayAdapter<String>adapterf2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftf2);
        adapterf2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        f2_1.setAdapter(adapterf2);
        f2_2.setAdapter(adapterf2);


        //Shift 3
        mData.child("Friday").child("Shift3").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftf3.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftf3.add("Select Employee");
        ArrayAdapter<String>adapterf3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftf3);
        adapterf3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        f3_1.setAdapter(adapterf3);
        f3_2.setAdapter(adapterf3);


        //Shift 4
        mData.child("Friday").child("Shift4").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftf4.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftf4.add("Select Employee");
        ArrayAdapter<String>adapterf4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftf4);
        adapterf4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        f4_1.setAdapter(adapterf4);
        f4_2.setAdapter(adapterf4);


        /* *************************** Saturday ***************************** */
        //Shift 1
        mData.child("Saturday").child("Shift1").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shifts1.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shifts1.add("Select Employee");
        ArrayAdapter<String>adapters1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shifts1);
        adapters1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1_1.setAdapter(adapters1);
        s1_2.setAdapter(adapters1);

        //Shift 2
        mData.child("Saturday").child("Shift2").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shifts2.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shifts2.add("Select Employee");
        ArrayAdapter<String>adapters2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shifts2);
        adapters2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2_1.setAdapter(adapters2);
        s2_2.setAdapter(adapters2);


        //Shift 3
        mData.child("Saturday").child("Shift3").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shifts3.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shifts3.add("Select Employee");
        ArrayAdapter<String>adapters3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shifts3);
        adapters3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3_1.setAdapter(adapters3);
        s3_2.setAdapter(adapters3);


        //Shift 4
        mData.child("Saturday").child("Shift4").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shifts4.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shifts4.add("Select Employee");
        ArrayAdapter<String>adapters4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shifts4);
        adapters4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s4_1.setAdapter(adapters4);
        s4_2.setAdapter(adapters4);


        /* *************************** Sunday ***************************** */
        //Shift 1
        mData.child("Sunday").child("Shift1").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftu1.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftu1.add("Select Employee");
        ArrayAdapter<String>adapteru1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftu1);
        adapteru1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        u1_1.setAdapter(adapteru1);
        u1_2.setAdapter(adapteru1);



        //Shift 2
        mData.child("Sunday").child("Shift2").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftu2.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftu2.add("Select Employee");
        ArrayAdapter<String>adapteru2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftu2);
        adapteru2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        u2_1.setAdapter(adapteru2);
        u2_2.setAdapter(adapteru2);


        //Shift 3
        mData.child("Sunday").child("Shift3").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftu3.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftu3.add("Select Employee");
        ArrayAdapter<String>adapteru3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftu3);
        adapteru3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        u3_1.setAdapter(adapteru3);
        u3_2.setAdapter(adapteru3);

        //Shift 4
        mData.child("Sunday").child("Shift4").addValueEventListener(new ValueEventListener() {
            //reads data from database
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    shiftu4.add(snapshot.getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed read: ", databaseError.getMessage());
            }
        });
        shiftu4.add("Select Employee");
        ArrayAdapter<String>adapteru4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shiftu4);
        adapteru4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        u4_1.setAdapter(adapteru4);
        u4_2.setAdapter(adapteru4);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* ******** Monday ******** */
                //shift 1
                mData.child("NextWeek").child("Monday").child("Shift1").child("Employee1").setValue(m1_1.getSelectedItem().toString());
                mData.child("Next Week").child("Monday").child("Shift1").child("Employee2").setValue(m1_2.getSelectedItem().toString());
                //shift 2
                mData.child("Next Week").child("Monday").child("Shift2").child("Employee1").setValue(m2_1.getSelectedItem().toString());
                mData.child("Next Week").child("Monday").child("Shift2").child("Employee2").setValue(m2_2.getSelectedItem().toString());
                //shift 1
                mData.child("Next Week").child("Monday").child("Shift3").child("Employee1").setValue(m3_1.getSelectedItem().toString());
                mData.child("Next Week").child("Monday").child("Shift3").child("Employee2").setValue(m3_2.getSelectedItem().toString());
                //shift 1
                mData.child("Next Week").child("Monday").child("Shift4").child("Employee1").setValue(m4_1.getSelectedItem().toString());
                mData.child("Next Week").child("Monday").child("Shift4").child("Employee2").setValue(m4_2.getSelectedItem().toString());

                Toast.makeText(getApplicationContext(), "Schedule updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditSchedule.this, ManagerHomeScreen.class));
            }
        });


    }
}