package com.example.projekt;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Baza {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String TAG = "MyActivity";

    public void addData(String nazwa, String kod, String nadrzedne){
        Map<String, Object> new_product = new HashMap<>();
        new_product.put("nazwa", nazwa);
        new_product.put("nadrzedne", nadrzedne);

        db.collection("products").document(kod)
                .set(new_product)
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid){
                        Log.d(TAG, "DocumentSnapshot successfully written!");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });


    }

    public interface mycallback {
        void onCallback(String[] value);
    }
    public void readDataKod(String kod , final mycallback callback){

        DocumentReference docRef = db.collection("products").document(kod);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                String[] pobraneDane = new String[2];
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                       // Log.d(TAG, "Pobrane dane: " + document.get());
                        pobraneDane[0] = document.getString("nazwa");
                        pobraneDane[1] = document.getString("nadrzedne");


                    } else {
                        Log.d(TAG, "Nie znaleziono danych");
                        pobraneDane[0] = pobraneDane[1] = "0";
                    }
                } else {
                    Log.d(TAG, "Coś poszło nie tak :/ ", task.getException());
                }
            callback.onCallback(pobraneDane);
            }
        });
    }

    public void readDataNazwa(String nazwa , final mycallback callback){

        db.collection("products")
                .whereEqualTo("nazwa", nazwa)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                String[] pobraneDane = new String[2];
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                       // Log.d(TAG, "Pobrane dane: " + document.get());

                        pobraneDane[0] = document.getString("nazwa");
                        pobraneDane[1] = document.getString("nadrzedne");

                    }
                } else {
                    Log.d(TAG, "Coś poszło nie tak :/ ", task.getException());
                }
                callback.onCallback(pobraneDane);
            }
        });
    }


}
