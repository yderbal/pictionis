package com.dev.mypictionis;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartingActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private final String FIREBASETAG = "FIREBASE";
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(FIREBASETAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(FIREBASETAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void createAccount(String email,String password)
    {
        //check user password
        //if ok createuserwithemailandpassword
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(FIREBASETAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(StartingActivity.this,"Register failed.",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(StartingActivity.this, "User Successfully Registered.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void signin(String email,String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(FIREBASETAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(FIREBASETAG, "signInWithEmail", task.getException());
                            Toast.makeText(StartingActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent intent = new Intent(StartingActivity.this,MenuActivity.class);
                            startActivity(intent);
                        }
                        // ...
                    }
                });
    }

    public void debugGetUser()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }

    /* --- Action buttons --- */
    public void signInUser(View view)
    {
        EditText editText = (EditText) findViewById(R.id.editText);
        String email= editText.getText().toString();
        editText = (EditText) findViewById(R.id.editText2);
        String password = editText.getText().toString();
        if(email.equals("") || password.equals(""))
        {
            Toast.makeText(StartingActivity.this,"Email or Password not filled",Toast.LENGTH_LONG).show();
            return;
        }
        signin(email,password);
    }

    public void registerUser(View view)
    {
        EditText editText = (EditText) findViewById(R.id.editText);
        String username = editText.getText().toString();
        editText = (EditText) findViewById(R.id.editText2);
        String password = editText.getText().toString();
        if(username.equals("") || password.equals(""))
        {
            Toast.makeText(StartingActivity.this,"Email or Password not filled",Toast.LENGTH_LONG).show();
            return;
        }
        createAccount(username, password);
    }
}
