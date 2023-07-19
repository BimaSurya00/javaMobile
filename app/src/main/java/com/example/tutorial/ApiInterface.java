package com.example.tutorial;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {


    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("reports/{user_id}")
    Call<ReportResponse> getReportsUser(@Path("user_id") int userId);
}
