package com.dev.mypictionis.com.dev.mypictionis.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.mypictionis.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Database extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("chat");
    TextView tv;
    EditText editText;
    Map<String,Message> messages ;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String email = user.getEmail();

    //childe listener
    ChildEventListener childListener ;
    static int id =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        tv = (TextView) findViewById(R.id.chat_message);
        editText = (EditText) findViewById(R.id.edit_message);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messages = new HashMap<String, Message>();
                int idref=1;
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Iterable<DataSnapshot> datas = dataSnapshot.getChildren();
                Iterator<DataSnapshot> ite = datas.iterator();
                String s ="";
                while(ite.hasNext())
                {
                    DataSnapshot data = ite.next();
                    Message message = data.getValue(Message.class);
                    messages.put(String.valueOf(idref),message);
                    s+= message.message+"\n";
                    idref++;
                }
                tv.setText(email+" : "+s);
                id = idref;
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    public void sendMessage(View view)
    {
        messages.put(String.valueOf(id),new Message(editText.getText().toString(),"Mike"));
        ref.setValue(messages);
        id++;
    }
}
