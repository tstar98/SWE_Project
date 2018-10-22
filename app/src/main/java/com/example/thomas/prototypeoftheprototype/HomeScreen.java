package com.example.thomas.prototypeoftheprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

    }

    public void openSchdule(View view){
        Intent intent = new Intent(this, Schedule.class);
        startActivity(intent);
    }

    public void openAvailability(View view){
        Intent intent = new Intent(this, Availability.class);
        startActivity(intent);
    }

    public void openRequest(View view){
        Intent intent = new Intent(this, Request.class);
        startActivity(intent);
    }

    public void logOut(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
