package com.example.tedc.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.tedc.models.Student;
import com.example.tedc.models.VerificationResult;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthRepository {
   public final FirebaseAuth auth;
   public final FirebaseFirestore db;


   MutableLiveData<VerificationResult> authRes;
    VerificationResult result;
    private String TAG = "auth repo" ;


    public AuthRepository() {
        this.auth = FirebaseAuth.getInstance();
        this.db = FirebaseFirestore.getInstance();
    }


    public void LoginUser(String email,String password,MutableLiveData<VerificationResult> authRes){

        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {


                authRes.postValue(new VerificationResult(false,authResult.getUser()));


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                authRes.postValue(new VerificationResult(true,e.getMessage()));


            }
        });
    }

    public void RegisterUser(Student student, String password , MutableLiveData<VerificationResult> authRes){

        auth.createUserWithEmailAndPassword(student.getEmail(),password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                  DocumentReference reference = db.collection("students").document(authResult.getUser().getUid());

                  student.setStudentId(authResult.getUser().getUid());
                    reference.set(student).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            authRes.postValue(new VerificationResult(false,authResult.getUser()));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            authRes.postValue(new VerificationResult(true,e.getMessage()));

                        }
                    });





            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.i(TAG, "onFailure: "+e.getMessage());
                authRes.postValue(new VerificationResult(true,e.getMessage()));
            }
        });





    }
}
