package com.vincode.simipa.network;


import com.vincode.simipa.model.AchievementResponse;
import com.vincode.simipa.model.CalendarResponse;
import com.vincode.simipa.model.ClassScheduleResponse;
import com.vincode.simipa.model.CollegeScheduleResponse;
import com.vincode.simipa.model.KRSResponse;
import com.vincode.simipa.model.LoginResponse;
import com.vincode.simipa.model.PresenceResponse;
import com.vincode.simipa.model.ProfileResponse;
import com.vincode.simipa.model.ScholarshipResponse;
import com.vincode.simipa.model.ServiceResponse;
import com.vincode.simipa.model.SplashResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> userLogin(
            @Field("user") String username,
            @Field("pass") String password,
            @Query("status") String status
    );

    @GET("read-profile.php")
    Call<ProfileResponse> userProfile(
            @Query("npm") String npm
    );

    @GET("read-update-splashscreen.php")
    Call<SplashResponse> getSplashImage(
            @Query("flag") String flag
    );

    @GET("read-kalender-akademik.php")
    Call<CalendarResponse> getCalendarAcademic(
            @Query("semester") String semester,
            @Query("tahun_akademik") String thnAkademik
    );

    @GET("read-layanan.php")
    Call<ServiceResponse> getServiceData(
            @Query("npm") String npm,
            @Query("jenis") String jenis
    );

    @GET("read-presensi-mhs.php")
    Call<PresenceResponse> getPresenceData(
            @Query("npm") String npm,
            @Query("tgl") String tanggal,
            @Query("type") String type
    );
    @GET("read-jadwal-ruang.php")
    Call<ClassScheduleResponse> getClassData(
            @Query("hari") String hari,
            @Query("jurusan") String jurusan
    );
    @GET("read-jadwal-mhs.php")
    Call<CollegeScheduleResponse> getCollegeData(
            @Query("hari") String hari,
            @Query("npm") String npm,
            @Query("tahun_akademik") String tahun_akademik,
            @Query("semester") String semester,
            @Query("type") String type

    );
    @GET("read-beasiswa.php")
    Call<ScholarshipResponse> getScholarshipData(
            @Query("npm") String npm
    );
    @GET("read-prestasi.php")
    Call<AchievementResponse> getAchievementData(
            @Query("npm") String npm
    );
    @GET("read-krs-student.php")
    Call<KRSResponse> getKRSData(
            @Query("semester") String semester,
            @Query("npm") String npm
    );
}
