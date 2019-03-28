package com.oc.Assignment_3;

/*
    Robert Armstrong
    Justin Duff
    CSCI 4020
    Assignment 03
    MainActivity.java

 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends Activity {

    private PaintCanvas my_canvas;
    private String color = "Black";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_canvas = (PaintCanvas) findViewById(R.id.my_canvas);


        /*****Color Picker Menu*****/
        final ImageButton colorPicked = findViewById(R.id.color_picker_btn);
        final PopupMenu colorPopMenu = new PopupMenu(getApplicationContext(), colorPicked);
        Menu colorMenu = colorPopMenu.getMenu();

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

        //*****FILL BUTTON****//
        final Button fill = findViewById(R.id.fill_btn);

        /*****On Click Listener for Color Button*****/
        colorPicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPopMenu.show();
            }
        });

        /*****On Click Listener for Open Button******/
        ImageButton open_btn=findViewById(R.id.open_btn);
        open_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                open_picture();
            }
        });

        /*****On Click Listener for Save Button******/
        ImageButton save_btn=findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveImage();
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

        /*****ON Click Listener for Fill Button****/
        fill.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                my_canvas.setBgPaint(color);
            }
        });

        /*****On Click Listener for sub menu of Colors*****/
        colorPopMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case 0:
                        my_canvas.setColor("Black");
                        color = "Black";
                        colorPicked.setImageResource(R.drawable.black);
                        fill.setTextColor(Color.BLACK);
                        break;
                    case 1:
                        my_canvas.setColor("Red");
                        colorPicked.setImageResource(R.drawable.red);
                        color = "Red";
                        fill.setTextColor(Color.RED);
                        break;
                    case 2:
                        my_canvas.setColor("Blue");
                        colorPicked.setImageResource(R.drawable.blue);
                        color = "Blue";
                        fill.setTextColor(Color.BLUE);
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
//Method to open gallery image and display on canvas
    public void  open_picture()
    {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(galleryIntent, ""),1);
    }
//On Activity result for opening gallery image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            try {
                Uri returnedUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), returnedUri);
                my_canvas.my_canvas.drawBitmap(bitmap, 0, 0, my_canvas.paintBrush);
                my_canvas.invalidate();
                Toast.makeText(getApplicationContext(), "Image opened!", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Couldn't open image.",Toast.LENGTH_LONG).show();

            }
        }
    }

    public void saveImage() {
        try {
            my_canvas.setDrawingCacheEnabled(true);
            Bitmap b = my_canvas.getDrawingCache();
            MediaStore.Images.Media.insertImage(this.getContentResolver(), b, "Drawing", "My Drawing");
             Toast.makeText(this, "Image Saved!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.i("Error Saving", e.toString());
        }
    }
}
