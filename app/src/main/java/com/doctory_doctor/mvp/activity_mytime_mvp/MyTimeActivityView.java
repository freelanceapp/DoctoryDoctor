package com.doctory_doctor.mvp.activity_mytime_mvp;

import com.doctory_doctor.databinding.DialogAddTimeBinding;
import com.doctory_doctor.models.MyTimeModel;

public interface MyTimeActivityView {
    void onFinished();

    void onFailed(String msg);

    void ondata(MyTimeModel body);

    void onLoad();

    void onFinishload();

    void sucese();

    void onServer();


    void onDateSelected(String date, DialogAddTimeBinding binding);

    void delteucese();
}
