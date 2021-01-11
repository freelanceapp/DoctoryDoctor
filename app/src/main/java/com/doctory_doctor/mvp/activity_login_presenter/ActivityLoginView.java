package com.doctory_doctor.mvp.activity_login_presenter;

import com.doctory_doctor.models.UserModel;

public interface ActivityLoginView {
    void onSuccess(UserModel userModel);
    void onFailed(String msg);
}
