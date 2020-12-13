package com.doctory_doctor.mvp.activity_sign_up_mvp;

import com.doctory_doctor.models.DiseaseModel;
import com.doctory_doctor.models.UserModel;

import java.util.List;

public interface ActivitySignUpView {
    void onFragmentSignUp1Displayed();
    void onFragmentSignUp2Displayed();
    void onFragmentSignUp3Displayed();
    void onBack();
    void onFailed(String msg);
    void onLoad();
    void onFinishload();
    void onSignupValid(UserModel userModel);
    void onServer();
    void onnotconnect(String msg);

}
