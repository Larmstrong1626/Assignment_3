package com.oc.Assignment_3;

/*
    Robert Armstrong
    Justin Duff
    CSCI 4020
    Assignment 03
    PaintCanvas.java

 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintCanvas extends View {

    private Paint paintBrush;
    public Canvas my_canvas;

    private Bitmap my_Bitmap;

    private Path my_Path;
    boolean Rectangle = false;

    private float endX, endY;
    private float X, Y;
    private int my_color = Color.BLACK;//set color to black to start
    private static final float TOUCH_TOLERANCE = 5;
    Context context;


    public PaintCanvas(Context context) {
        super(context);
        setup();
    }


    public PaintCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public PaintCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public void setup() {


        my_Path = new Path();
        paintBrush = new Paint();
        paintBrush.setAntiAlias(true);
        paintBrush.setColor(my_color);
        paintBrush.setStyle(Paint.Style.STROKE);
        paintBrush.setStrokeJoin(Paint.Join.ROUND);
        paintBrush.setStrokeWidth(8f);


    }
//changes line thickness when brush thickness button is clicked
    public void change_brush() {
        invalidate();
        if(paintBrush.getStrokeWidth() == 8f) {
            paintBrush.setStrokeWidth(16f);
        } else if(paintBrush.getStrokeWidth() == 16f) {
            paintBrush.setStrokeWidth(32f);
        } else if(paintBrush.getStrokeWidth() == 32f) {
            paintBrush.setStrokeWidth(8f);
        }
    }
//gives a blank canvas to start new drawing
    public void fresh_canvas() {

        my_canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }
//sets the color based on color button clicked
    public void setColor(String new_color) {
        invalidate();
        my_color = Color.parseColor(new_color);
        paintBrush.setColor(my_color);

    }

    @Override
    protected void onSizeChanged(int w, int h, int old_width, int old_height) {
        super.onSizeChanged(w, h, old_width, old_height);

        my_Bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        my_canvas = new Canvas(my_Bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(my_Bitmap, 0, 0, null);
        canvas.drawPath(my_Path, paintBrush);
    }
//set the starting coordinates
    private void touchstart(float x, float y) {
        my_Path.moveTo(x,y);
        X = x;
        Y = y;
    }

    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - X);
        float dy = Math.abs(y - Y);

        if(dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            if(Rectangle) {
                endX = x;
                endY = y;
            } else {
                my_Path.quadTo(X, Y, (x + X) / 2, (y + Y) / 2);
                X = x;
                Y = y;
            }
        }

    }

    private void upTouch() {
        my_Path.lineTo(X,Y);
        if(Rectangle) {
            my_canvas.drawRect(X,Y,endX,endY,paintBrush);
        } else {
            my_canvas.drawPath(my_Path, paintBrush);
        }
        my_Path.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            touchstart(x, y);
            invalidate();
        }else if(event.getAction()==MotionEvent.ACTION_MOVE){
            moveTouch(x, y);
            invalidate();
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            upTouch();
            invalidate();
        }
        return true;


    }

}
