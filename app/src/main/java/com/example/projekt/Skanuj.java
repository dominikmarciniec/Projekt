package com.example.projekt;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.Handler;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.MultiDetector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

public class Skanuj  {
    private Context context;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;

    private String kod;
    private Handler handler = new Handler();
    private Baza baza = new Baza();
    Skanuj(SurfaceView surfaceView, Context context){
        this.surfaceView=surfaceView;
        this.context=context;
    }
    public interface Listener<T> {
        void on(T arg);
    }
  public void Camera( final Listener<Boolean> result){


      BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(context).setBarcodeFormats(Barcode.CODE_128 | Barcode.QR_CODE).build();
      TextRecognizer textRecognizer = new TextRecognizer.Builder(context).build();

        MultiDetector multiDetector = new MultiDetector.Builder()
                .add(barcodeDetector)
                .add(textRecognizer)
                .build();
        cameraSource=new CameraSource.Builder(context,multiDetector).setAutoFocusEnabled(true).build();


        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(ActivityCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
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

                            Vibrator vibrator=(Vibrator)context.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            cameraSource.stop();
                           kod=qrCodes.valueAt(0).displayValue;
                           result.on(true);
                            //  changeActivity(value);
                           // check(value);

                        }
                    }

                    );

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

    }

    public void check(){
System.out.println("check"+kod);
        baza.readDataNazwa(kod, new Baza.mycallback() {
            @Override
            public void onCallback(String[] value) {
                System.out.println("Loaded " + value[0]+value[1]);
                if(value[0]=="0" && value[1]=="0"){
                    changeActivityNotFound(kod);
                }
                else{
                    changeActivityfound(value);
                }
            }
        });


    }
    public String returnkod(){
        return kod;
    }

    public void changeActivityNotFound(String kod) {


        Intent intent = new Intent(context, ActivitySkanujBrakKodu.class);
        intent.putExtra("kod",kod);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);


    }
    public void changeActivityfound(String[] value) {


        Intent intent = new Intent(context, ActivitySkanujZnalezionoKod.class);
        intent.putExtra("nazwa",value[0]);
        intent.putExtra("nadrzedne",value[1]);
        System.out.println(value[1]+"value 1");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);


    }

}
