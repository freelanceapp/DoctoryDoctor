package com.doctory_doctor.mvp.activity_editprofile_mvp;


import com.doctory_doctor.models.UserModel;

public interface EditprofileActivityView {

    void onFailed(String msg);
    void onLoad();
    void onFinishload();

    void onFinished();

    void onupdateValid(UserModel body);
    void onFailed();
    void onServer();

    void onnotconnect(String msg);
}
