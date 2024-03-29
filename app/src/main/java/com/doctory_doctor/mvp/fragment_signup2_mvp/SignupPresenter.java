package com.doctory_doctor.mvp.fragment_signup2_mvp;

import android.content.Context;
import android.util.Log;


import com.doctory_doctor.R;
import com.doctory_doctor.models.AllCityModel;
import com.doctory_doctor.models.AllSpiclixationModel;
import com.doctory_doctor.remote.Api;
import com.doctory_doctor.tags.Tags;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupPresenter {
    private SignupFragmentView view;
    private Context context;

    public SignupPresenter(SignupFragmentView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void getSpecilization()
    {
        // Log.e("tjtjtj",userModel.getIs_confirmed());
        view.onLoad();

        Api.getService(Tags.base_url)
                .getspicailest()
                .enqueue(new Callback<AllSpiclixationModel>() {
                    @Override
                    public void onResponse(Call<AllSpiclixationModel> call, Response<AllSpiclixationModel> response) {
//                        view.onFinishload();

                        if (response.isSuccessful() && response.body() != null) {
                            view.onSuccess(response.body());
                        } else {
    //                        view.onFinishload();
                            view.onFailed(context.getString(R.string.something));
                            try {
                                Log.e("error_codess",response.code()+ response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<AllSpiclixationModel> call, Throwable t) {
                        try {
  //                          view.onFinishload();
                            view.onFailed(context.getString(R.string.something));
                            Log.e("Errorsss", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }
    public void getcities()
    {
        // Log.e("tjtjtj",userModel.getIs_confirmed());

        Api.getService(Tags.base_url)
                .getcities()
                .enqueue(new Callback<AllCityModel>() {
                    @Override
                    public void onResponse(Call<AllCityModel> call, Response<AllCityModel> response) {
                        view.onFinishload();

                        if (response.isSuccessful() && response.body() != null) {
                            view.onSuccesscitie(response.body());
                        } else {
                            view.onFinishload();
                            view.onFailed(context.getString(R.string.something));
                            try {
                                Log.e("error_codess",response.code()+ response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<AllCityModel> call, Throwable t) {
                        try {
                            view.onFinishload();
                            view.onFailed(context.getString(R.string.something));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }
}