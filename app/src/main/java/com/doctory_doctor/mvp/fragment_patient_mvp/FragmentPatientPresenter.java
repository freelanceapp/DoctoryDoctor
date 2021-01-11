package com.doctory_doctor.mvp.fragment_patient_mvp;

import android.content.Context;
import android.util.Log;

import com.doctory_doctor.R;
import com.doctory_doctor.models.PatentDataModel;
import com.doctory_doctor.models.UserModel;
import com.doctory_doctor.preferences.Preferences;
import com.doctory_doctor.remote.Api;
import com.doctory_doctor.tags.Tags;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPatientPresenter {
    private FragmentPatientView view;
    private Context context;
    private UserModel userModel;
    private Preferences preference;
    private Call<PatentDataModel> call;

    public FragmentPatientPresenter(FragmentPatientView view, Context context) {
        this.view = view;
        this.context = context;
        preference = Preferences.getInstance();
        userModel = preference.getUserData(context);
    }

    public void getPatient(String query) {
        if (userModel == null) {
            return;
        }

        view.showProgressBar();
        if (call!=null){
            call.cancel();

        }
        call = Api.getService(Tags.base_url).
                getMyPatient("Bearer " + userModel.getData().getToken(), "off", 20, 1,userModel.getData().getId(), query);

        call.enqueue(new Callback<PatentDataModel>() {
                    @Override
                    public void onResponse(Call<PatentDataModel> call, Response<PatentDataModel> response) {

                        if (response.isSuccessful()) {
                            view.hideProgressBar();
                            if (response.body() != null) {
                                view.onSuccess(response.body().getData());

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
                    public void onFailure(Call<PatentDataModel> call, Throwable t) {
                        try {
                            view.hideProgressBar();
                            if (t.getMessage() != null) {
                                Log.e("error", t.getMessage() + "__");

                                if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {
                                    view.onFailed(context.getString(R.string.something));

                                } else if (t.getMessage().toLowerCase().equals("Canceled".toLowerCase())||t.getMessage().toLowerCase().contains("Socket closed".toLowerCase())){

                                }else {
                                    view.onFailed(context.getString(R.string.failed));

                                }
                            }


                        } catch (Exception e) {
                        }

                    }
                });
    }
}