package com.example.projekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ActivitySkanujZnalezionoKod extends Activity {
String nazwa2;
String polozenie;
String kod;
int x=0;
Baza baza =new Baza();
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skanuj_znalezionokod);
        TextView nazwa =findViewById(R.id.nazwa);
        final TextView lokalizacja =findViewById(R.id.polozenie);
        Intent intent =getIntent();
        nazwa2=intent.getStringExtra("nazwa");
        kod=intent.getStringExtra("nadrzedne");
        polozenie=nazwa2;
        System.out.println("kod"+kod);
        System.out.println("nazwa"+nazwa2);
        if(kod.isEmpty()){
    System.out.println("jest empty");
            lokalizacja.setText(nazwa2);

        }
        else{
            System.out.println(" nie jest empty");

    baza.readDataKod(kod, new Baza.mycallback() {
        @Override
        public void onCallback(String[] value) {
            System.out.println(value[1]);
            if (kod.isEmpty()) {
                x = 1;
                polozenie = value[0] + "/" + polozenie;

            } else {
                polozenie = value[0] + "/" + polozenie;
                kod = value[1];


            }
            lokalizacja.setText(polozenie);
        }
    });

}




        nazwa.setText(nazwa2);

    }
}
