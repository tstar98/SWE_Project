package com.example.thomas.prototypeoftheprototype;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText user;
    private EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
    }

    public void logIn(View view){
        String username = user.getText().toString();
        String password = pass.getText().toString();
        boolean canLog = true;

        if(username.isEmpty()){
            user.setError("Field can not be empty");
            canLog = false;
        }if(password.isEmpty()){
            canLog = false;
            pass.setError("Field can not be empty");
        }

        //if username and email are not empty, attempt login
        if (canLog) {
            mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //successful login
                    if(task.isSuccessful()){
                        FirebaseUser mUser = mAuth.getCurrentUser();
                        if (mUser.isEmailVerified()){

                        }
                        else {
                            Intent intent = new Intent(LoginActivity.this, HomeScreen.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }
                    //unsuccessful login, display error
                    else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void gotoRegister(View view){
        startActivity(new Intent(LoginActivity.this, AddUser.class));
    }
}
