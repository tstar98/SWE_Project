package com.example.thomas.prototypeoftheprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        boolean error = false;
        final EditText user = (EditText)findViewById(R.id.username);
        final EditText pass = (EditText)findViewById(R.id.password);

        if (user.getText().toString().isEmpty()) {
            error = true;
            user.setError("");
        }

        if (pass.getText().toString().isEmpty()){
            error = true;
            pass.setError("");
        }


        if (!error){
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
    }
}
