package com.doctory_doctor.mvp.fragment_live_mvp;

import android.content.Context;
import android.util.Log;

import com.doctory_doctor.R;
import com.doctory_doctor.models.ApointmentModel;
import com.doctory_doctor.models.UserModel;
import com.doctory_doctor.remote.Api;
import com.doctory_doctor.tags.Tags;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveFragmentPresenter {
    private LiveFragmentView view;
    private Context context;

    public LiveFragmentPresenter(LiveFragmentView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void getApointment(UserModel userModel) {

       // Log.e("mmmmmmm",userModel.getData().getId()+"");
        view.showProgressBar();
        Api.getService(Tags.base_url).getMyApointment("Bearer "+userModel.getData().getToken(),"off",1,1,userModel.getData().getId(),"online")
                .enqueue(new Callback<ApointmentModel>() {
                    @Override
                    public void onResponse(Call<ApointmentModel> call, Response<ApointmentModel> response) {

                        if (response.isSuccessful()) {
                            view.hideProgressBar();
                            if (response.body() != null) {
                                view.onSuccess(response.body());

                            }

                        } else {

                            try {
                                view.onFailed(context.getString(R.string.failed));
                                Log.e("error", response.code() + "_" + response.errorBody().string());

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApointmentModel> call, Throwable t) {
                        try {

                            if (t.getMessage() != null) {
                                Log.e("error", t.getMessage() + "__");

                                if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {
                                    view.onFailed(context.getString(R.string.something));

                                } else {
                                    view.onFailed(context.getString(R.string.failed));

                                }
                            }


                        } catch (Exception e) {
                        }

                    }
                });
    }
}