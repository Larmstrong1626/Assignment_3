package com.oc.Assignment_3;

/*
    Robert Armstrong
    Justin Duff
    CSCI 4020
    Assignment 03
    MainActivity.java

 */
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class MainActivity extends Activity {

    private PaintCanvas my_canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_canvas = (PaintCanvas) findViewById(R.id.my_canvas);

        /*****Color Picker Menu*****/
        final ImageButton colorPicked = findViewById(R.id.color_picker_btn);
        final PopupMenu colorPopMenu = new PopupMenu(getApplicationContext(), colorPicked);
        final Menu colorMenu = colorPopMenu.getMenu();

        colorMenu.add(0,0,0, "Black");
        colorMenu.add(0,1,0, "Red");
        colorMenu.add(0,2,0, "Blue");

        /*****Brush Size Menu*****/

        final ImageButton brushSize = findViewById(R.id.brush_size_btn);
        final PopupMenu brushSizePopMenu = new PopupMenu(getApplicationContext(), brushSize);
        Menu brushMenu = brushSizePopMenu.getMenu();

        brushMenu.add(0,0,0, "Thin");
        brushMenu.add(0,1,0, "Medium");
        brushMenu.add(0,2,0, "Thick");

        /******Shape menu*****/
        final ImageButton shape = findViewById(R.id.shape_btn);
        final PopupMenu shapePopMenu = new PopupMenu(getApplicationContext(), shape);
        Menu shapeMenu = shapePopMenu.getMenu();

        shapeMenu.add(0,0,0, "Line");
        shapeMenu.add(0,1,0, " Unfilled Rectangle");
        shapeMenu.add(0,2,0, "Filled Rectangle");
        shapeMenu.add(0,3,0, "Unfilled Oval");
        shapeMenu.add(0,4,0,"Filled Oval");

        /*****On Click Listener for Color Button*****/
        colorPicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPopMenu.show();
            }
        });

        /*****On Click Listener for Brush Size Button******/
        brushSize.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                brushSizePopMenu.show();
            }
        });

        /*****On Click Listner for Shape Button*****/
        shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shapePopMenu.show();
            }
        });

        /*****On Click Listener for sub menu of Colors*****/
        colorPopMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case 0:
                        my_canvas.setColor("Black");
                        colorPicked.setImageResource(R.drawable.black);
                        break;
                    case 1:
                        my_canvas.setColor("Red");
                        colorPicked.setImageResource(R.drawable.red);
                        break;
                    case 2:
                        my_canvas.setColor("Blue");
                        colorPicked.setImageResource(R.drawable.blue);
                        break;

                }
                return false;
            }
        });

        /****On Click Listener for sub menu of Brush sizes.*****/
        brushSizePopMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()) {
                    case 0:
                        my_canvas.change_brush(0);
                        brushSize.setImageResource(R.drawable.smallbrush);
                        break;
                    case 1:
                        my_canvas.change_brush(1);
                        brushSize.setImageResource(R.drawable.mediumbrush);
                        break;
                    case 2:
                        my_canvas.change_brush(2);
                        brushSize.setImageResource(R.drawable.thickbrush);
                        break;
                }
                return false;
            }
        });

        /*****On Click Listener for sub menu of shapes*****/
        shapePopMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case 0:
                        my_canvas.setShape(0);
                        shape.setImageResource(R.drawable.line);
                        break;
                    case 1:
                        my_canvas.setShape(1);
                        shape.setImageResource(R.drawable.rectangle);
                        break;
                    case 2:
                        my_canvas.setShape(2);
                        shape.setImageResource(R.drawable.filledrectangle);
                        break;
                    case 3:
                        my_canvas.setShape(3);
                        shape.setImageResource(R.drawable.oval);
                        break;
                    case 4:
                        my_canvas.setShape(4);
                        shape.setImageResource(R.drawable.filledoval);
                        break;
                }
                return false;
            }
        });
    }




}
