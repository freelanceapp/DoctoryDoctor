package com.doctory_doctor.mvp.activity_home_mvp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MenuItem;

import androidx.fragment.app.FragmentManager;

import com.doctory_doctor.R;
import com.doctory_doctor.models.UserModel;
import com.doctory_doctor.preferences.Preferences;
import com.doctory_doctor.ui.activity_home.fragments.Fragment_Appointment;
import com.doctory_doctor.ui.activity_home.fragments.Fragment_Consulting;
import com.doctory_doctor.ui.activity_home.fragments.Fragment_Live;
import com.doctory_doctor.ui.activity_home.fragments.Fragment_Patient;
import com.doctory_doctor.ui.activity_home.fragments.Fragment_More;

public class ActivityHomePresenter {
    private Context context;
    private FragmentManager fragmentManager;
    private HomeActivityView view;
    private Fragment_Live fragment_live;
    private Fragment_Appointment fragment_appointment;
    private Fragment_Consulting fragment_consulting;
    private Fragment_Patient fragment_patient;
    private Fragment_More fragment_more;
    private double lat=0.0,lng=0.0;

    public ActivityHomePresenter(Context context,HomeActivityView view, FragmentManager fragmentManager,double lat,double lng) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.view = view;
        this.lat = lat;
        this.lng = lng;
        displayFragmentAppointment();
    }

    @SuppressLint("NonConstantResourceId")
    public void manageFragments(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.live :
                displayFragmentLive();
                break;
            case R.id.consulting :
                displayFragmentConsulting();
                break;

            case R.id.patient :
                displayFragmentPatient();
                break;
            case R.id.more :
                displayFragmentMore();
                break;
            default:
                displayFragmentAppointment();
                break;
        }
    }
    private void displayFragmentLive(){
        if (fragment_live ==null){
            fragment_live = Fragment_Live.newInstance();
        }

        if (fragment_appointment!=null&&fragment_appointment.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_appointment).commit();
        }

        if (fragment_patient !=null&& fragment_patient.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_patient).commit();
        }

        if (fragment_consulting!=null&&fragment_consulting.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_consulting).commit();
        }
        if (fragment_more!=null&&fragment_more.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_more).commit();
        }

        if (fragment_live.isAdded()){
            fragmentManager.beginTransaction().show(fragment_live).commit();
        }else {
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment_live,"fragment_live").commit();
        }
    }

    private void displayFragmentAppointment(){
        if (fragment_appointment==null){
            fragment_appointment = Fragment_Appointment.newInstance();
        }

        if (fragment_live !=null&& fragment_live.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_live).commit();
        }

        if (fragment_patient !=null&& fragment_patient.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_patient).commit();
        }

        if (fragment_consulting!=null&&fragment_consulting.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_consulting).commit();
        }
        if (fragment_more!=null&&fragment_more.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_more).commit();
        }

        if (fragment_appointment.isAdded()){
            fragmentManager.beginTransaction().show(fragment_appointment).commit();
        }else {
            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment_appointment,"fragment_appointment").commit();
        }
    }

    private void displayFragmentConsulting(){
        if (fragment_consulting==null){
            fragment_consulting = Fragment_Consulting.newInstance();
        }


        if (fragment_live !=null&& fragment_live.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_live).commit();
        }

        if (fragment_patient !=null&& fragment_patient.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_patient).commit();
        }

        if (fragment_appointment!=null&&fragment_appointment.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_appointment).commit();
        }
        if (fragment_more!=null&&fragment_more.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_more).commit();
        }


        if (fragment_consulting.isAdded()){
            fragmentManager.beginTransaction().show(fragment_consulting).commit();
        }else {
            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment_consulting,"fragment_consulting").commit();
        }
    }

    private void displayFragmentPatient(){
        if (fragment_patient ==null){
            fragment_patient = Fragment_Patient.newInstance(lat,lng);
        }

        if (fragment_live !=null&& fragment_live.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_live).commit();
        }

        if (fragment_consulting!=null&&fragment_consulting.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_consulting).commit();
        }

        if (fragment_appointment!=null&&fragment_appointment.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_appointment).commit();
        }
        if (fragment_more!=null&&fragment_more.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_more).commit();
        }

        if (fragment_patient.isAdded()){
            fragmentManager.beginTransaction().show(fragment_patient).commit();
        }else {
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment_patient,"fragment_patient").commit();
        }
    }

    private void displayFragmentMore(){
        if (fragment_more==null){
            fragment_more = Fragment_More.newInstance();
        }

        if (fragment_live !=null&& fragment_live.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_live).commit();
        }

        if (fragment_consulting!=null&&fragment_consulting.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_consulting).commit();
        }

        if (fragment_appointment!=null&&fragment_appointment.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_appointment).commit();
        }
        if (fragment_patient !=null&& fragment_patient.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_patient).commit();
        }
        if (fragment_more.isAdded()){
            fragmentManager.beginTransaction().show(fragment_more).commit();
        }else {
            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment_more,"fragment_more").commit();
        }
    }

    public void backPress(){
        if (fragment_appointment !=null&& fragment_appointment.isAdded()&& fragment_appointment.isVisible()){
            view.onFinished();
        }else {
            displayFragmentAppointment();
            view.onAppointmentFragmentSelected();
        }
    }
}
