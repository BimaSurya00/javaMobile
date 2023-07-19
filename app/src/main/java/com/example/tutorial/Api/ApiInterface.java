package com.example.tutorial.Api;

import com.example.tutorial.HistoryAdmin.DeleteReportResponse;
import com.example.tutorial.Auth.LoginRequest;
import com.example.tutorial.Auth.LoginResponse;
import com.example.tutorial.HistoryAdmin.ReportResponse;
import com.example.tutorial.StatusLaporanAdmin.UpdateStatusRequest;
import com.example.tutorial.StatusLaporanAdmin.UpdateStatusResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {


    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("admin/reports")
    Call<ReportResponse> getAllReports();

    @DELETE("admin/report/{report_id}")
    Call<DeleteReportResponse> delReport(@Path("report_id") int reportId);

    @PUT("admin/report/{report_id}")
    Call<UpdateStatusResponse> updateStatus(@Body UpdateStatusRequest updateStatusRequest, @Path("report_id") int report_id);

}
