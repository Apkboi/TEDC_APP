package com.example.tedc.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tedc.models.Complaint;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.repository.DataRepository;

public class ComplaintViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    DataRepository dataRepository;
    public MutableLiveData<VerificationResult> sendComplaintRes = new MutableLiveData<>();
    public MutableLiveData<VerificationResult> getComplaintRes = new MutableLiveData<>();

    public ComplaintViewModel(DataRepository dataRepository) {
        this.dataRepository = new DataRepository();
    }

    public ComplaintViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public  void saveComplaint(Complaint complaint){
        dataRepository.sendComplaint(complaint, sendComplaintRes);
    }

    public  MutableLiveData<VerificationResult> complaintResponse(){
        return getComplaintRes;
    }


//    Get Complaints
    public  MutableLiveData<VerificationResult> getComplaintResponse(){
        return sendComplaintRes;


    } public  void getComplaint(Complaint complaint){
        dataRepository.getUserComplaints( getComplaintRes);
    }


}