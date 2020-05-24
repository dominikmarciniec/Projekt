package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.MultiDetector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;
    TextRecognizer textRecognizer;
    String value;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView=findViewById(R.id.camerapreview);


        barcodeDetector=new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.CODE_128|Barcode.QR_CODE).build();
        textRecognizer = new TextRecognizer.Builder(this).build();

        MultiDetector multiDetector = new MultiDetector.Builder()
                .add(barcodeDetector)
                .add(textRecognizer)
                .build();
        cameraSource=new CameraSource.Builder(this,multiDetector).setAutoFocusEnabled(true).build();


        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                try {
                    cameraSource.start(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource = null;
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> qrCodes=detections.getDetectedItems();

                if(qrCodes.size()!=0)
                {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            cameraSource.stop();
                            value=qrCodes.valueAt(0).displayValue;
                            changeActivity(value);
                        }
                    });

                }


            }
        });
      /* textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
            @Override
            public void release() {
            }




            @Override
            public void receiveDetections(Detector.Detections<TextBlock> detections) {
                final SparseArray<TextBlock> items = detections.getDetectedItems();
                if (items.size() != 0 ){

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder stringBuilder = new StringBuilder();
                            for(int i=0;i<items.size();i++){
                                TextBlock item = items.valueAt(i);
                                stringBuilder.append(item.getValue());
                                stringBuilder.append("\n");
                            }
                            String number = stringBuilder.toString().replaceAll("\\D+","");
                            if(number.matches("^[0-9]{3,12}$")){
                                Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                vibrator.vibrate(1000);
                                changeActivity(number);
                            }
                        }
                    });
                }
            }
        }); */
       // addData("miasta", "ROGO");
    }


    public void changeActivity(String value) {

            Intent intent = new Intent(this, ActivitySkanujBrakKodu.class);
            intent.putExtra("value",value);
            startActivity(intent);


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
