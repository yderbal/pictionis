package com.dev.mypictionis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dev.mypictionis.com.dev.mypictionis.db.Database;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void startGame(View view)
    {
        Toast.makeText(MenuActivity.this,"Gamestarted",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }

    public void checkDataBase(View view)
    {
        Intent intent = new Intent(this, Database.class);
        startActivity(intent);
    }
}
