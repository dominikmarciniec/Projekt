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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szukaj);

        Button szukaj_btn = findViewById(R.id.szukaj_btn);
        wyniki = findViewById(R.id.szukaj_wyniki);
        final EditText szukaj_tekst = findViewById(R.id.szukaj_tekst);

        szukaj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String szukaj = szukaj_tekst.getText().toString();

               baza.readDataNazwa(szukaj, new Baza.mycallback() {
                   @Override
                   public void onCallback(String[] value) {
                        wyniki.setText(wyniki.getText().toString() + value[0] + " " + value[1]);
                   }
               });
            }
        });
    }
}
