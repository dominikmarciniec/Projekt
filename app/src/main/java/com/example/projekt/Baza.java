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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Baza {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String TAG = "MyActivity";

    public void addData(String nazwa, String kod, String nadrzedne){
        Map<String, Object> new_product = new HashMap<>();
        new_product.put("kod", kod);
        new_product.put("nadrzedne", nadrzedne);

        db.collection("products").document(nazwa)
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
                    } else {
                        Log.d(TAG, "Nie znaleziono danych");
                    }
                } else {
                    Log.d(TAG, "Coś poszło nie tak :/ ", task.getException());
                }
            }
        });
    }

    public void readDatazNazwa(String kod){
        db.collection("products")
                .whereEqualTo("kod", kod)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG,document.getData().toString());
                    }
                } else{
                        Log.d(TAG, "Nie znaleziono danych");
                    }
            }

        });
    }
}
