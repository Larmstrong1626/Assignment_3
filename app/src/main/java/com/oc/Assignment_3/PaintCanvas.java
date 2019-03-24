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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PaintCanvas extends View {

    public Paint paintBrush;
    public Canvas my_canvas;

    private Bitmap my_Bitmap;

    private Path my_Path;
    boolean Rectangle = false;
    int shape =0;

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


        public void change_brush(int new_brush){
            //invalidate();
            int newBrush = new_brush;
            if(newBrush == 0){
                paintBrush.setStrokeWidth(8f);
            }else if(newBrush == 1){
                paintBrush.setStrokeWidth(16f);
            }else if(newBrush == 2){
                paintBrush.setStrokeWidth(32f);
            }else{
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
//sets the shape based on either line or rectangle options.
    public void setShape(int myShape){
            shape = myShape;
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
            if(shape != 0) {
                endX = x;
                endY = y;
            }else {
                my_Path.quadTo(X, Y, (x + X) / 2, (y + Y) / 2);
                X = x;
                Y = y;
            }
        }

    }

//Either draw rectangle, or line
    public void upTouch(int thisShape) {
        shape = thisShape;
        my_Path.lineTo(X,Y);
        if(shape == 0){
           my_canvas.drawPath(my_Path, paintBrush);
        }else if(shape == 1) {
            my_canvas.drawRect(X,Y,endX,endY,paintBrush);
        } else if(shape == 2) {
            paintBrush.setStyle(Paint.Style.FILL);
            my_canvas.drawRect(X,Y,endX,endY,paintBrush);
        }else if(shape == 3){
            my_canvas.drawOval(X,Y,endX,endY, paintBrush);
        }else if(shape == 4){
            paintBrush.setStyle(Paint.Style.FILL);
            my_canvas.drawOval(X,Y,endX,endY, paintBrush);
        }else{
            my_canvas.drawPath(my_Path, paintBrush);
        }
       my_Path.reset();
    }
//Touch event for the canvas,
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
            upTouch(shape);
            invalidate();
        }
        return true;


    }



}
