package com.doctory_doctor.mvp.fragment_patient_mvp;

import com.doctory_doctor.models.UserModel;

import java.util.List;

public interface FragmentPatientView {
    void onSuccess(List<UserModel.User> data);
    void onFailed(String msg);
    void showProgressBar();
    void hideProgressBar();

}
