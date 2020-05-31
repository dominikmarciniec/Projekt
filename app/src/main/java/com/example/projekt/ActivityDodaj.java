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

        camera_open_id = (Button)findViewById(R.id.skanuj);
        click_image_id = (SurfaceView)findViewById(R.id.camerapreview2);
        final android.view.ViewGroup.LayoutParams params = click_image_id.getLayoutParams();

        camera_open_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                params.height=params.height*250;
                click_image_id.setVisibility(View.VISIBLE);
                click_image_id.setLayoutParams(params);
                camera_open_id.setEnabled(false);

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

}

