package com.doctory_doctor.services;

import com.doctory_doctor.models.AddTimeModel;
import com.doctory_doctor.models.AllCityModel;
import com.doctory_doctor.models.AllSpiclixationModel;
import com.doctory_doctor.models.ApointmentModel;
import com.doctory_doctor.models.DayModel;
import com.doctory_doctor.models.DrugDataModel;
import com.doctory_doctor.models.MessageDataModel;
import com.doctory_doctor.models.MessageModel;
import com.doctory_doctor.models.MyTimeModel;
import com.doctory_doctor.models.NotificationDataModel;
import com.doctory_doctor.models.PatentDataModel;
import com.doctory_doctor.models.PlaceGeocodeData;
import com.doctory_doctor.models.PlaceMapDetailsData;
import com.doctory_doctor.models.ReservisionTimeModel;
import com.doctory_doctor.models.SettingModel;
import com.doctory_doctor.models.UserModel;
import com.doctory_doctor.models.UserRoomModelData;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Service {

    @GET("place/findplacefromtext/json")
    Call<PlaceMapDetailsData> searchOnMap(@Query(value = "inputtype") String inputtype,
                                          @Query(value = "input") String input,
                                          @Query(value = "fields") String fields,
                                          @Query(value = "language") String language,
                                          @Query(value = "key") String key
    );

    @GET("geocode/json")
    Call<PlaceGeocodeData> getGeoData(@Query(value = "latlng") String latlng,
                                      @Query(value = "language") String language,
                                      @Query(value = "key") String key);

    @FormUrlEncoded
    @POST("api/login")
    Call<UserModel> login(@Field("phone_code") String phone_code,
                          @Field("phone") String phone

    );

    @FormUrlEncoded
    @POST("api/login")
    Call<UserModel> login(@Field("phone_code") String phone_code,
                          @Field("phone") String phone,
                          @Field("password") String password


    );
    @GET("api/get-specializations")
    Call<AllSpiclixationModel> getspicailest();

    @GET("api/get-cities")
    Call<AllCityModel> getcities();
    @Multipart
    @POST("api/doctor-register")
    Call<UserModel> signup(@Part("phone_code") RequestBody phone_code,
                           @Part("phone") RequestBody phone,
                           @Part("name") RequestBody name,
                           @Part("latitude")RequestBody lat_part,
                           @Part("longitude")RequestBody lng_part,
                           @Part("address")RequestBody address_part,

                           @Part("gender")RequestBody gender,
                           @Part("user_type")RequestBody user_type,
                           @Part("software_type") RequestBody software_type,
                           @Part("specialization_id") RequestBody spicial_part,
                           @Part("city_id") RequestBody city_part,
                           @Part("email") RequestBody email_part,
                           @Part MultipartBody.Part logo
,
                           @Part MultipartBody.Part image





    );
    @Multipart
    @POST("api/doctor-register")
    Call<UserModel> signup(@Part("phone_code") RequestBody phone_code,
                           @Part("phone") RequestBody phone,
                           @Part("name") RequestBody name,
                           @Part("latitude")RequestBody lat_part,
                           @Part("longitude")RequestBody lng_part,
                           @Part("address")RequestBody address_part,

                           @Part("gender")RequestBody gender,
                           @Part("user_type")RequestBody user_type,
                           @Part("software_type") RequestBody software_type,
                           @Part("specialization_id") RequestBody spicial_part,
                           @Part("city_id") RequestBody city_part,
                           @Part("email") RequestBody email_part,
                           @Part MultipartBody.Part liceimage
    );
    @GET("api/get-one-consulting")
    Call<MessageModel> getRoomMessages(
            @Query("medical_consulting_id") int medical_consulting_id,
            @Query("pagination_status") String pagination_status,
            @Query("per_link_") int per_link_,
            @Query("page") int page


    );
    @GET("api/get-medical-consultings")
    Call<UserRoomModelData> getRooms(
            @Query("user_id") int user_id,
            @Query("user_type") String user_type,
            @Query("pagination_status") String pagination_status

    );
    @GET("api/get-drugs")
    Call<DrugDataModel> getDrugs(@Header("Authorization") String Authorization,
                                 @Query("doctor_id") int doctor_id,
                                 @Query("user_id") int user_id

    );
    @GET("api/get-my-reservation")
    Call<ApointmentModel> getMyApointment(
            @Header("Authorization") String Authorization,
            @Query("pagination_status") String pagination_status,
            @Query("per_link_") int per_link_,
            @Query("page") int page,
            @Query("doctor_id") int doctor_id,
            @Query("reservation_type") String reservation_type

    );
    @GET("api/search-patient-by-name")
    Call<PatentDataModel> getMyPatient(@Header("Authorization") String Authorization,
                                       @Query("pagination_status") String pagination_status,
                                       @Query("per_link_") int per_link_,
                                       @Query("page") int page,
                                       @Query("doctor_id") int doctor_id,
                                       @Query("name") String name

    );
    @FormUrlEncoded
    @POST("api/add-msg")
    Call<MessageDataModel> sendmessagetext(
            @Field("from_user_id") String from_id,

            @Field("to_user_id") String to_id,
            @Field("type") String type,
            @Field("medical_consulting_id") String medical_consulting_id,
            @Field("message") String message


    );

    @Multipart
    @POST("api/add-msg")
    Call<MessageDataModel> sendmessagewithimage
            (
                    @Part("from_user_id") RequestBody from_user_id,
                    @Part("to_user_id") RequestBody to_user_id,
                    @Part("type") RequestBody type,
                    @Part("medical_consulting_id") RequestBody medical_consulting_id,
                    @Part MultipartBody.Part imagepart

            );
    @FormUrlEncoded
    @POST("api/contact-us")
    Call<ResponseBody> contactUs(@Field("name") String name,
                                 @Field("email") String email,
                                 @Field("subject") String subject,
                                 @Field("message") String message


    );

    @POST("api/logout")
    Call<ResponseBody> logout(@Header("Authorization") String user_token
    );

    @GET("api/settings")
    Call<SettingModel> getSetting();
    @GET("api/get-my-notifications")
    Call<NotificationDataModel> getNotification(@Header("Authorization") String Authorization,
                                                @Query("user_type") String user_type,
                                                @Query("user_id") int user_id

    );
    @FormUrlEncoded
    @POST("api/delete_notification")
    Call<ResponseBody> delteNotification(@Field("notification_id") int notification_id);
    @GET("api/Get-Days-with-Times")
    Call<DayModel> getDays(
            @Header("Authorization") String user_token,
            @Query("doctor_id") int doctor_id,
            @Query("pagination_status") String pagination_status

    );

    @FormUrlEncoded
    @POST("api/add-doctor-days")
    Call<ResponseBody> addday(
            @Header("Authorization") String user_token,
            @Field("doctor_id") String doctor_id,
            @Field("day_name[]") List<String> day_name


    );
    @FormUrlEncoded
    @POST("api/update-doctor-register")
    Call<UserModel> editprofile(
            @Header("Authorization") String user_token,
            @Field("id") String id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("title_job_degree") String title_job_degree



    );
    @FormUrlEncoded
    @POST("api/update-doctor-register")
    Call<UserModel> editprofile(
            @Header("Authorization") String user_token,
            @Field("id") String id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("title_job_degree") String title_job_degree,
            @Field("password") String password




    );
    @Multipart
    @POST("api/update-doctor-register")
    Call<UserModel> editprofile(
            @Header("Authorization") String user_token,
            @Part("id") RequestBody id,
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("title_job_degree") RequestBody title_job_degree,
            @Part("password") RequestBody password,
            @Part MultipartBody.Part logo




    );
    @Multipart
    @POST("api/update-doctor-register")
    Call<UserModel> editprofile(
            @Header("Authorization") String user_token,
            @Part("id") RequestBody id,
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("title_job_degree") RequestBody title_job_degree,
            @Part MultipartBody.Part logo




    );
    @FormUrlEncoded
    @POST("api/update-doctor-register")
    Call<UserModel> editprofile(
            @Header("Authorization") String user_token,
            @Field("detection_price") String detection_price,
            @Field("id") String id


    );
    @FormUrlEncoded
    @POST("api/update-doctor-register")
    Call<UserModel> switchprofile(
            @Header("Authorization") String user_token,
            @Field("is_emergency") String is_emergency,
            @Field("id") String id


    );
    @GET("api/Get-Times-By-Day")
    Call<MyTimeModel> gettimes(
            @Header("Authorization") String user_token,
            @Query("doctor_time_id") int doctor_time_id,
            @Query("pagination_status") String pagination_status

    );

    @POST("api/add-doctor-times")
    Call<ResponseBody> addtime(
            @Header("Authorization") String user_token,
            @Body AddTimeModel addTimeModel


    );
    @FormUrlEncoded
    @POST("api/delete-doctor-days")
    Call<ResponseBody> deleteday(
            @Header("Authorization") String user_token,
            @Field("doctor_time_id") int doctor_time_id
            );
    @FormUrlEncoded
    @POST("api/delete-doctor-times")
    Call<ResponseBody> deltetime(
            @Header("Authorization") String user_token,
            @Field("doctor_time_detail_id") int doctor_time_detail_id
    );
    @GET("api/get-doctor-reservations")
    Call<ReservisionTimeModel> getreservision(
            @Query("doctor_id") String doctor_id,
            @Query("date") String date,
            @Query("day_name") String day_name,
            @Query("reservation_type") String reservation_type




    );
    @FormUrlEncoded
    @POST("api/add-fast-reservations")
    Call<ResponseBody> addfastreservision(
                                      @Field("doctor_id") String doctor_id,
                                      @Field("date") String date,
                                      @Field("time")String time,
                                      @Field("cost")String cost,
                                      @Field("reservation_type")String reservation_type,
                                      @Field("day_name")String day_name,
                                      @Field("time_type")String time_type,
                                      @Field("patient_name")String patient_name,
                                      @Field("patient_phone")String patient_phone





    );
}