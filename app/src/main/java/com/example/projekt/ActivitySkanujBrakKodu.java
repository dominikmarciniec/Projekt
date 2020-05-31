package com.example.projekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ActivitySkanujBrakKodu extends Activity {
    String kod;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skanuj_brakkodu);
        TextView komunikat =findViewById(R.id.komunikat);
        Intent intent =getIntent();
        kod=intent.getStringExtra("kod");

        komunikat.setText("kod"+kod);

    }
    public void changeActivity(View view) {
        String button_text;

        button_text = ((Button)view).getText().toString();

        if(button_text.equals("Powtórz skanowanie")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }else if(button_text.equals("Dodaj produkt")){
            Intent intent = new Intent(this, ActivityDodaj.class);
            intent.putExtra("value",kod);
            startActivity(intent);

        }

    }
}
