package com.example.thomas.prototypeoftheprototype;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class AddUser extends AppCompatActivity {
    private static final String TAG = AddUser.class.getSimpleName();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        final EditText editTextemail = (EditText)findViewById(R.id.editTextEmail);
        final EditText editTextpass = (EditText)findViewById(R.id.editTextPass);
        final EditText editTextrepass = (EditText)findViewById(R.id.editTextRePass);
        final Button btn = (Button)findViewById(R.id.buttonRegister);

        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextemail.getText().toString();
                String pass = editTextpass.getText().toString();
                String repass = editTextrepass.getText().toString();
                boolean canRegisster = true;

                if (email.isEmpty()){
                    canRegisster = false;
                    editTextemail.setError("Field cannot be empty");
                }
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    canRegisster = false;
                    editTextemail.setError("Invalid email");
                }

                if(pass.isEmpty()){
                    canRegisster = false;
                    editTextpass.setError("Field cannot be empty");
                }
                else if (!pass.equals(repass)){
                    canRegisster = false;
                    editTextpass.setError("Fields must match");
                    editTextrepass.setError("Fields must match");
                }

                if (canRegisster){
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(AddUser.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(AddUser.this, "Registered user", Toast.LENGTH_LONG).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();

                                }else{
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });

    }
}

