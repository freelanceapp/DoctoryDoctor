package com.doctory_doctor.mvp.fragment_live_mvp;

import com.doctory_doctor.models.ApointmentModel;

public interface LiveFragmentView {
    void onSuccess(ApointmentModel apointmentModel);
    void onFailed(String msg);
    void showProgressBar();
    void hideProgressBar();

}
