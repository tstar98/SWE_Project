package com.example.thomas.prototypeoftheprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class ManagerHomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home_screen);
    }

    public void openSchdule(View view){
        Intent intent = new Intent(this, Schedule.class);
        startActivity(intent);
    }

    public void logOut(View view){
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void editSched(View view)
    {
        Intent intent = new Intent(this, EditSchedule.class);
        startActivity(intent);
    }
    public void requests(View view)
    {
        Intent intent = new Intent(this, ManagerRequest.class);
        startActivity(intent);
    }

    public void contacts(View view)
    {
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);
    }
}
