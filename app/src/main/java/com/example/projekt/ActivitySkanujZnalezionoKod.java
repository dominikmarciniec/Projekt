package com.example.projekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ActivitySkanujZnalezionoKod extends Activity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skanuj_znalezionokod);
        TextView komunikat2 =findViewById(R.id.komunikat2);

        komunikat2.setText("kod");

    }
}
