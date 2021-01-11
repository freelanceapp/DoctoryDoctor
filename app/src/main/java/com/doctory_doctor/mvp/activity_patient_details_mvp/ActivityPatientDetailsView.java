package com.doctory_doctor.mvp.activity_patient_details_mvp;

import com.doctory_doctor.models.DrugModel;

import java.util.List;

public interface ActivityPatientDetailsView {
    void onSuccess(List<DrugModel> data);
    void onFailed(String msg);
    void showProgressBar();
    void hideProgressBar();

}
