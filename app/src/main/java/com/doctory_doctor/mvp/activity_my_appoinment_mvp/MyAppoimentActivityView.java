package com.doctory_doctor.mvp.activity_my_appoinment_mvp;

import com.doctory_doctor.databinding.DialogAddTimeBinding;
import com.doctory_doctor.models.DayModel;

public interface MyAppoimentActivityView {
    void onFinished();

    void onFailed(String msg);

    void ondata(DayModel body);

    void onLoad();

    void onFinishload();

    void sucese();

    void onServer();


    void onDateSelected(String date, DialogAddTimeBinding binding);

    void suceseaddtime();
}
