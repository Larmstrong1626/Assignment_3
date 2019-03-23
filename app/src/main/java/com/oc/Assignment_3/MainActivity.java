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
        ImageButton colorPicked = findViewById(R.id.color_picker_btn);
        final PopupMenu colorPopMenu = new PopupMenu(getApplicationContext(), colorPicked);
        Menu colorMenu = colorPopMenu.getMenu();

        colorMenu.add(0,0,0, "Red  ");
        colorMenu.add(0,1,0, "Blue");

        /*****Brush Size Menu*****/

        ImageButton brushSize = findViewById(R.id.brush_size_btn);
        final PopupMenu brushSizePopMenu = new PopupMenu(getApplicationContext(), brushSize);
        Menu brushMenu = brushSizePopMenu.getMenu();

        brushMenu.add(0,0,0, "thin");
        brushMenu.add(0,0,0, "meduim");
        brushMenu.add(0,0,0, "thick");


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
    }
}
