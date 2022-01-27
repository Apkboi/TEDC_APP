package com.example.tedc.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tedc.models.Complaint;
import com.example.tedc.models.LogBook;
import com.example.tedc.models.Post;
import com.example.tedc.models.Record;
import com.example.tedc.models.Skill;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.repository.DataRepository;

public class DataViewModel extends ViewModel {

    DataRepository dataRepository;

    public MutableLiveData<VerificationResult> saveRes = new MutableLiveData<>();
    public MutableLiveData<VerificationResult> recordRes = new MutableLiveData<>();


    public MutableLiveData<VerificationResult> saveLogBooRes = new MutableLiveData<>();
    public MutableLiveData<VerificationResult> getLogBookRes = new MutableLiveData<>();



    public MutableLiveData<VerificationResult> complaintRes = new MutableLiveData<>();
    public MutableLiveData<VerificationResult> getUserComplaintRes = new MutableLiveData<>();
    public MutableLiveData<VerificationResult> getAllComplaintRes = new MutableLiveData<>();

    public MutableLiveData<VerificationResult> getUserPostRes = new MutableLiveData<>();
    public MutableLiveData<VerificationResult> savePostRes = new MutableLiveData<>();
    public MutableLiveData<VerificationResult> allPostRes = new MutableLiveData<>();

    public MutableLiveData<VerificationResult> getUserSkillRes = new MutableLiveData<>();
    public MutableLiveData<VerificationResult> saveSkillRes = new MutableLiveData<>();
    public MutableLiveData<VerificationResult> allSkillRes = new MutableLiveData<>();


    public DataViewModel() {
        this.dataRepository = new DataRepository();
    }

    public void saveRecord(Record record) {
        dataRepository.saveRecord(record, saveRes);
    }

    public void getAllRecords() {
        dataRepository.getAllRecord(recordRes);
    }

    public MutableLiveData<VerificationResult> saveResponse() {
        return saveRes;
    }

    public MutableLiveData<VerificationResult> getRecordResponse() {
        return recordRes;
    }

//    ------------------------ Complaints  ----------------------


    //    ---------------- Complaints------------
    public void saveComplaint(Complaint complaint) {
        dataRepository.sendComplaint(complaint, complaintRes);
    }

    public MutableLiveData<VerificationResult> complaintResponse() {
        return complaintRes;
    }

    public MutableLiveData<VerificationResult> getUserComplaintResponse() {
        return getUserComplaintRes;


    }

    public void getUserComplaint() {
        dataRepository.getUserComplaints(getUserComplaintRes);
    }

    public MutableLiveData<VerificationResult> getAllComplaintResponse() {
        return getAllComplaintRes;


    }

    public void getAllComplaint() {
        dataRepository.getAllComplaints(getAllComplaintRes);
    }

//------------------- Posts ------------------

    public void savePost(Post post) {
        dataRepository.sendPost(post, savePostRes);
    }

    public MutableLiveData<VerificationResult> sendPostResponse() {
        return savePostRes;
    }

    public MutableLiveData<VerificationResult> getUserPostResponse() {
        return getUserPostRes;


    }

    public void getUserPost() {
        dataRepository.getUserPosts(getUserPostRes);
    }

    public MutableLiveData<VerificationResult> getAllPostResponse() {
        return allPostRes;


    }

    public void getAllPost() {
        dataRepository.getAllPosts(allPostRes);
    }


    //    ---------------------Log Books ---------------------
    public void saveLogBook(LogBook logBook) {
        dataRepository.saveLogBook(logBook, saveLogBooRes);
    }

    public void getAllLogBooks() {
        dataRepository.getAllLogBooks(getLogBookRes);
    }


    //    --------------- Skill--------------------
    public void saveSkill(Skill skill) {
        dataRepository.saveSkills(skill, saveSkillRes);
    }

    public MutableLiveData<VerificationResult> sendSkillResponse() {
        return saveSkillRes;
    }

    public MutableLiveData<VerificationResult> getUserSkilResponse() {
        return getUserSkillRes;


    }

    public void getUserSkill() {
        dataRepository.getUserSkills(getUserSkillRes);
    }

    public MutableLiveData<VerificationResult> getAllSkillRes() {
        return allSkillRes;


    }

    public void getAllSkill() {
        dataRepository.getAllSkills(allSkillRes);
    }


}
