package com.doctory_doctor.mvp.fragment_home_mvp;

import com.doctory_doctor.models.ApointmentModel;

public interface HomeFragmentView {
    void onSuccess(ApointmentModel apointmentModel);
    void onFailed(String msg);
    void showProgressBar();
    void hideProgressBar();

}
