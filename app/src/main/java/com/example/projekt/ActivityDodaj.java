package com.example.projekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class ActivityDodaj extends Activity {

    String kod;
    String nazwa;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj);
        Intent intent = getIntent();
        kod = intent.getStringExtra("value");
        EditText kodactivity = findViewById(R.id.kod);
        final EditText nazwactivity = findViewById(R.id.nazwa);
        Button dodaj = findViewById(R.id.dodaj);
        kodactivity.setText(kod);

        dodaj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            nazwa=nazwactivity.getText().toString();

            }
        });
    }
}

