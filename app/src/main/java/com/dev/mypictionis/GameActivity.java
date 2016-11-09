package com.dev.mypictionis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity {

    private LinearLayout paintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        DrawingView drawingView =(DrawingView) findViewById(R.id.draw_screen);
        drawingView.setColor("#000000");
    }


}
