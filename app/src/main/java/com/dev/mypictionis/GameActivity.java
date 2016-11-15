package com.dev.mypictionis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GameActivity extends AppCompatActivity {

    private LinearLayout paintLayout;
    DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        drawingView =(DrawingView) findViewById(R.id.draw_screen);
        drawingView.setColor("#000000");
    }


    public void clearDraw(View view)
    {
        drawingView.clearDraw();
    }
}
