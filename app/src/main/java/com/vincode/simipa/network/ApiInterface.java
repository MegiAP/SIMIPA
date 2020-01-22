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
import com.vincode.simipa.model.StudyResponse;
import com.vincode.simipa.model.Value;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
            @Query("type") String type,
            @Query("semester") String semester
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

    //api lokal
    @FormUrlEncoded
    @POST("absen.php")
    Call<Value> setPresence(
            @Field("ID_presensi") String id_presensi,
            @Field("status") String status,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("alamat") String alamat,
            @Field("npm") String npm




    );

    @Headers("Content-Type: application/json")
    @POST("update-presensi-mhs.php")
    Call<Value> updatePresence(
//            @Field("ID_presensi") String id_presensi,
//            @Field("status") String status,
//            @Field("latitude") String latitude,
//            @Field("longitude") String longitude,
//            @Field("alamat") String alamat,
//            @Field("npm") String npm
            @Body String result
    );

//    api latihan
    @GET("read_studi.php")
    Call<StudyResponse> getStudi(
            @Query("npm") String npm
    );
}
