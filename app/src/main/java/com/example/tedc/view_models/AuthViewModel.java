package com.example.tedc.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.tedc.models.Student;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.repository.AuthRepository;


public class AuthViewModel extends ViewModel {
    AuthRepository authRepository;
     MutableLiveData<VerificationResult> authRes = new MutableLiveData<>();


    public AuthViewModel() {
        this.authRepository = new AuthRepository();
    }

    public void registerUser(Student user, String password) {
        authRepository.RegisterUser(user,password,authRes);
    }

    public void loginUser(String email,String password){
        authRepository.LoginUser(email,password,authRes);
    }
    public LiveData<VerificationResult> authRes(){
        return authRes;
    }
}
