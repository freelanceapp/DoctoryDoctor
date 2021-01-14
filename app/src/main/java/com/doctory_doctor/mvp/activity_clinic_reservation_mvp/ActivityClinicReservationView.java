package com.doctory_doctor.mvp.activity_clinic_reservation_mvp;

import com.doctory_doctor.models.ReservisionTimeModel;

public interface ActivityClinicReservationView {
    void onDateSelected(String date,String dayname);
    void onLoad();
    void onFinishload();
    void onFailed(String msg);

    void onreservtimesucess(ReservisionTimeModel body);
    void onsucsess();

    void onServer();

    void onnotlogin();

    void onnotconnect(String toLowerCase);

    void onFailed();

}
