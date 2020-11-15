package com.doctory_doctor.mvp.activity_confirm_code_mvp;

import com.doctory_doctor.models.UserModel;

public interface ActivityConfirmCodeView {
    void onCounterStarted(String time);
    void onCounterFinished();
    void onCodeFailed(String msg);
    void onSuccess();
}
