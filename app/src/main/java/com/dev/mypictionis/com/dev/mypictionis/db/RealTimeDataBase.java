package com.dev.mypictionis.com.dev.mypictionis.db;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Madhow on 14/11/2016.
 */

public class RealTimeDataBase {

    private FirebaseDatabase database;
    private DatabaseReference myRef ;
    private String TAG = "FIREBASE DATABASE";

    public RealTimeDataBase()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        myRef.setValue("Hello World");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void addElement()
    {
        myRef.setValue("Hello Mike");
    }
}
