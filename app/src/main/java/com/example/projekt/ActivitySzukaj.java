package com.example.projekt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySzukaj extends Activity {

    Baza baza = new Baza();

    TextView wyniki;
    EditText wyszukaj;
    //Button szukaj_btn;
    ArrayList<String> znalezione = new ArrayList<String>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szukaj);

        wyniki = findViewById(R.id.wyniki);
        wyszukaj = findViewById(R.id.nazwa_szukaj);
        //szukaj_btn = findViewById(R.id.szukaj_btn);

        /*szukaj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)*/

            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (wyszukaj.getText().toString().length() > 0) {
                        String szukaj = wyszukaj.getText().toString();

                        baza.readDataNazwa(szukaj, new Baza.mycallback() {
                            @Override
                            public void onCallback(String[] value) {
                                znalezione.add(value[0]);
                                znalezione.add(value[1]);
                            }
                        });

                        baza.readDataKod(szukaj, new Baza.mycallback() {
                            @Override
                            public void onCallback(String[] value) {
                                znalezione.add(value[0]);
                                znalezione.add(value[1]);
                            }
                        });
                        if(znalezione.size() > 0 && znalezione.get(0) != null) {
                            wyniki.setText("Wyniki wyszukiwania:\n");
                            int lp = 1;
                            for (int i = 0; i < znalezione.size(); i += 2) {
                                if (znalezione.get(i) != null && znalezione.get(i) != "0") {
                                    if (znalezione.get(i + 1) != "") {
                                        wyniki.setText(wyniki.getText().toString() + lp + ". Nazwa: " + znalezione.get(i) + ", nadrzedny: " + znalezione.get(i + 1) + "\n");
                                    } else {
                                        wyniki.setText(wyniki.getText().toString() + lp + ". Nazwa: " + znalezione.get(i) + ", produkt główny" + "\n");
                                    }
                                    ++lp;
                                }
                            }
                        } else {
                            wyniki.setText("Brak wyników wyszukiwania");
                        }
                        znalezione.clear();
                }
                }
            }, 0, 1000);

        };
    }

