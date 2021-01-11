package com.doctory_doctor.mvp.activity_notification_mvp;

import com.doctory_doctor.models.NotificationModel;

import java.util.List;

public interface ActivityNotificationView {
    void onSuccess(List<NotificationModel> data);
    void onFailed(String msg);
    void showProgressBar();
    void hideProgressBar();

    void onLoad();

    void onFinishload();

    void onSuccessDelete();
}
