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
        TextView lokalizacja =findViewById(R.id.polozenie);
        Intent intent =getIntent();
        nazwa2=intent.getStringExtra("nazwa");
        kod=intent.getStringExtra("nadrzedne");

        System.out.println("kod"+kod);
        System.out.println("nazwa"+nazwa2);

       while(x==0){
           if(kod==null){
               x=1;


    }
           /*else{
               baza.readDataNazwa(kod, new Baza.mycallback() {
                   @Override
                   public void onCallback(String[] value) {
                       System.out.println(value[1]);
                       if( value[1]==""){
                           x=1;
                       }
                       else{
                           polozenie=value[0]+polozenie;
                           kod=value[1];

                       }
                   }
               });
           }*/
       }
        nazwa.setText(nazwa2);
        lokalizacja.setText(polozenie);
    }
}
