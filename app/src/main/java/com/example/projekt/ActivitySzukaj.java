package com.example.projekt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class ActivitySzukaj extends Activity {

    Baza baza = new Baza();

    TextView wyniki;
    EditText wyszukaj;
    Button szukaj_wyszukiwanie;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szukaj);

        wyniki = findViewById(R.id.wyniki);
        wyszukaj = findViewById(R.id.nazwa_szukaj);

        /*szukaj_wyszukiwanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String szukaj = wyszukaj.getText().toString();

               baza.readDataNazwa(szukaj, new Baza.mycallback() {
                   @Override
                   public void onCallback(String[] value) {
                        wyniki.setText(wyniki.getText().toString() + value[0] + " " + value[1]);
                   }
               });
            }
        });*/
    }
}
