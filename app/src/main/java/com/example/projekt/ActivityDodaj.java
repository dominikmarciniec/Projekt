package com.example.projekt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class ActivityDodaj extends Activity {

    Baza baza = new Baza();

    String kod;
    String nazwa;
    String nadrzedny;

    Button camera_open_id;
    SurfaceView click_image_id;
    private static final int pic_id = 123;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj);
        Intent intent = getIntent();
        kod = intent.getStringExtra("value");
        EditText kodactivity = findViewById(R.id.kod);
        final EditText nadrzednyactivity = findViewById(R.id.nadrzedny);
        final EditText nazwactivity = findViewById(R.id.nazwa);
        Button dodaj = findViewById(R.id.dodaj);
        kodactivity.setText(kod);

        camera_open_id = (Button)findViewById(R.id.dodaj);
        click_image_id = (SurfaceView)findViewById(R.id.camerapreview2);

        camera_open_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                // Create the camera_intent ACTION_IMAGE_CAPTURE
                // it will open the camera for capture the image
                Intent camera_intent
                        = new Intent(MediaStore
                        .ACTION_IMAGE_CAPTURE);

                // Start the activity with camera_intent,
                // and request pic id
                startActivityForResult(camera_intent, pic_id);
            }
        });


        dodaj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            nazwa=nazwactivity.getText().toString();
            nadrzedny=nadrzednyactivity.getText().toString();
            baza.addData(nazwa, kod, nadrzedny);

            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == pic_id) {
            // BitMap is data structure of image file
            // which stor the image in memory
            Bitmap photo = (Bitmap)data.getExtras().get("data");

            // Set the image in imageview for display
            click_image_id.setIM
        }
    }
}

