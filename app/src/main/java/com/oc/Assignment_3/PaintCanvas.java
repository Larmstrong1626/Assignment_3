package com.oc.Assignment_3;

/*
    Robert Armstrong
    Justin Duff
    CSCI 4020
    Assignment 03
    PaintCanvas.java

 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;

public class PaintCanvas extends View {

    private Paint paintBrush;
    public Canvas my_canvas;




    public PaintCanvas(Context context){
        super(context);
        setup(null);
    }


    public PaintCanvas(Context context, AttributeSet attrs){
        super(context, attrs);
        setup(attrs);
    }

    public PaintCanvas(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        setup(attrs);
    }

    public void setup(AttributeSet attrs){



    }

    public void fresh_canvas(){

        my_canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

}
