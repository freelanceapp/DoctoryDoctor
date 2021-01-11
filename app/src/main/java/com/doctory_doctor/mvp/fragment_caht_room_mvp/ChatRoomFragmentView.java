package com.doctory_doctor.mvp.fragment_caht_room_mvp;

import com.doctory_doctor.models.UserRoomModelData;

public interface ChatRoomFragmentView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);

    void ondata(UserRoomModelData body);
}
