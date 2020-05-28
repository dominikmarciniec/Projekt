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

import java.util.HashMap;
import java.util.Map;

public class Baza {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String TAG = "MyActivity";

    public void addData(String nazwa, String kod, String nadrzedne){
        Map<String, Object> new_product = new HashMap<>();
        new_product.put("nazwa", nazwa);
        new_product.put("nadrzedne", nadrzedne);
        // new_product.put("kraj", "polska");

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

    public void readData(String kolekcja, String dokument){
        DocumentReference docRef = db.collection(kolekcja).document(dokument);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        Log.d(TAG, "Pobrane dane: " + document.getData());
                        System.out.println("dupa123"+document.getData().toString());
                    } else {
                        Log.d(TAG, "Nie znaleziono danych");
                        System.out.println("eloelo");
                    }
                } else {
                    Log.d(TAG, "Coś poszło nie tak :/ ", task.getException());
                }
            }
        });
    }
    public String[] readDataNazwa(String kod){
        final String[] pobraneDane = new String[2];
        DocumentReference docRef = db.collection("products").document(kod);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        //Log.d(TAG, "Pobrane dane: " + document.get());
                        System.out.println("dupa123"+document.getData().toString());
                        pobraneDane[0] = document.getString("nazwa");
                        pobraneDane[1] = document.getString("nadrzedna");
                    } else {
                        Log.d(TAG, "Nie znaleziono danych");
                        System.out.println("eloelo");
                        pobraneDane[0] = pobraneDane[1] = "0";
                    }
                } else {
                    Log.d(TAG, "Coś poszło nie tak :/ ", task.getException());
                }
            }


        });
        return pobraneDane;
    }



}
