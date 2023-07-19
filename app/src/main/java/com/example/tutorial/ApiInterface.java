package com.example.tutorial;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface ApiInterface {


    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("/reports/{id}")
    Call<ReportUserResponse> getReports();
}
