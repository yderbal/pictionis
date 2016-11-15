package com.dev.mypictionis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Attr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Madhow on 09/11/2016.
 */
public class DrawingView extends View {

    private Path drawPath;
    private Paint drawPaint, canvasPaint;
    private int paintColor=0x00000000;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    FirebaseDatabase database;
    DatabaseReference ref;
    HashMap<String, DrawingObject> map = new HashMap<String,DrawingObject>();
    DrawingObject dwobj = new DrawingObject();
    static int id = 0;
    int i=0;

    public DrawingView(Context context, AttributeSet attrs){
        super(context, attrs);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("drawing"+id);
        setupDrawing();
        //id++;
    }

    private void setupDrawing()
    {
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        Coordinates c = null;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX,touchY);
                c = new Coordinates(touchX,touchY);
                dwobj.addOrigin(c);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX,touchY);
                dwobj.addPoint(new Coordinates(touchX,touchY));
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath,drawPaint);
                drawPath.reset();
                map.put("object"+i,dwobj);
                i++;
                dwobj = new DrawingObject(i);
                ref.setValue(map);
                break;
            default:return false;
        }
        invalidate();
        return true;

    }

    public void setColor(String color)
    {
        invalidate();
        paintColor = Color.parseColor(color);
        drawPaint.setColor(paintColor);
    }

    public void clearDraw()
    {
        ref.removeValue();
        drawCanvas.drawColor(Color.WHITE);
        map.clear();
        drawPath.reset();
        invalidate();
    }
}
