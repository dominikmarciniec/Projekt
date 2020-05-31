package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.SurfaceView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView=findViewById(R.id.camerapreview);

        final Skanuj skanuj =  new Skanuj(surfaceView ,this.getBaseContext());


            skanuj.Camera(new Skanuj.Listener<Boolean>(){
                public void on(Boolean result){
                   if(result==true){
                       skanuj.check();
                   }
                }
            });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    public void appExit(MenuItem item) {
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.wyszukaj_produkt:
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                Intent intent = new Intent(this, ActivitySzukaj.class);
                startActivity(intent);
                return true;
            case R.id.dodaj_produkt:
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                intent = new Intent(this, ActivityDodaj.class);
                startActivity(intent);
                return true;

        }
        return true;
    }
}
