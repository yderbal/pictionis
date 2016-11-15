package com.dev.mypictionis;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;

/**
 * TODO: document your custom view class.
 */
public class PlayerView extends View {
    private Path drawPath;
    private Paint drawPaint, canvasPaint;
    private int paintColor=0x00000000;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    FirebaseDatabase database;
    DatabaseReference ref;
    HashMap<String, DrawingObject> map = new HashMap<String,DrawingObject>();
    DrawingObject dwobj = new DrawingObject();
    int id;

    public PlayerView(Context context, AttributeSet attrs) {
        super(context,attrs);
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    public void setId(int id)
    {
        this.id = id;
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("drawing"+id);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> datas = dataSnapshot.getChildren();
                Iterator<DataSnapshot> ite = datas.iterator();
                while(ite.hasNext())
                {
                    DataSnapshot data = ite.next();
                    DrawingObject value = data.getValue(DrawingObject.class);
                    Float x = value.origin.x;
                    Float y = value.origin.y;
                    drawPath.moveTo(x,y);
                    for(Coordinates c:value.points)
                    {
                        drawPath.lineTo(c.x,c.y);
                    }
                    drawCanvas.drawPath(drawPath,drawPaint);
                    drawPath.reset();
                }
                invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void setColor(String color)
    {
        invalidate();
        paintColor = Color.parseColor(color);
        drawPaint.setColor(paintColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap,0,0,canvasPaint);
        canvas.drawPath(drawPath,drawPaint);
    }

}
