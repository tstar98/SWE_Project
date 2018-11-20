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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUser extends AppCompatActivity {
    private static final String TAG = AddUser.class.getSimpleName();
    private FirebaseAuth mAuth;
    private String name;
    private FirebaseUser user;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        final EditText editTextname = (EditText)findViewById(R.id.editTextName);
        final EditText editTextemail = (EditText)findViewById(R.id.editTextEmail);
        final EditText editTextpass = (EditText)findViewById(R.id.editTextPass);
        final EditText editTextrepass = (EditText)findViewById(R.id.editTextRePass);
        final Button btn = (Button)findViewById(R.id.buttonRegister);

        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editTextname.getText().toString();
                String email = editTextemail.getText().toString();
                String pass = editTextpass.getText().toString();
                String repass = editTextrepass.getText().toString();
                boolean canRegisster = true;

                //check name
                //if empty cannot login
                if(name.isEmpty()){
                    canRegisster = false;
                    editTextname.setError("Field cannot be empty");
                }
                //check if it is a valid name
                else{
                    for(char c : name.toCharArray()){
                        if(!Character.isAlphabetic(c) && !Character.isSpaceChar(c) && c != '-'){
                            canRegisster = false;
                            editTextname.setError("Enter valid name");
                            break;
                        }
                    }
                }

                //check email
                //if empty cannot login
                if (email.isEmpty()){
                    canRegisster = false;
                    editTextemail.setError("Field cannot be empty");
                }
                //if not email cannot login
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    canRegisster = false;
                    editTextemail.setError("Invalid email");
                }

                //check password
                //if empty cannot login
                if(pass.isEmpty()){
                    canRegisster = false;
                    editTextpass.setError("Field cannot be empty");
                }
                //if passwords don't match
                else if (!pass.equals(repass)){
                    canRegisster = false;
                    editTextpass.setError("Fields must match");
                    editTextrepass.setError("Fields must match");
                }

                //if no errors attempt to register
                if (canRegisster){
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(AddUser.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // registered user
                                // Add name to profile
                                user = mAuth.getCurrentUser();
                                if (user != null) {
                                    //change profile request
                                    UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name).build();

                                    //successful update
                                    user.updateProfile(profile);

                                    //Toast.makeText(getApplicationContext(), "User registered", Toast.LENGTH_SHORT).show();
                                }
                                //go to homescreen
                                startActivity(new Intent(AddUser.this, HomeScreen.class));
                            }
                            else {
                                // If sign in fails, display a message to the user.
                                //User already exists
                                if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                                }
                                //displays message of why user can't register
                                else{
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

