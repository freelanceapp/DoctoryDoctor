package com.doctory_doctor.mvp.fragment_signup2_mvp;


import com.doctory_doctor.models.AllCityModel;
import com.doctory_doctor.models.AllSpiclixationModel;

public interface SignupFragmentView {
    void onSuccess(AllSpiclixationModel apointmentModel);
    void onFailed(String msg);
    void onLoad();
    void onFinishload();

    void onSuccesscitie(AllCityModel body);
}
