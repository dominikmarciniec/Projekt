package com.example.projekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ActivitySkanujZnalezionoKod extends Activity {
String nazwa;
String polozenie;
String kod;
int x=0;
Baza baza =new Baza();
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skanuj_znalezionokod);
        TextView nazwa2 =findViewById(R.id.nazwa);
        Intent intent =getIntent();
        nazwa=intent.getStringExtra("nazwa");
        kod=intent.getStringExtra("polozenie");
        nazwa2.setText(nazwa);
       while(x==0){
        baza.readDataNazwa(kod, new Baza.mycallback() {
            @Override
            public void onCallback(String[] value) {
                //if( value[1]!="0"){
                 //   x=1;
               // }
                //else{
                //    polozenie=value[0]+polozenie;
                //    kod=value[1];

               // }
            }
        });

    }
    }
}
