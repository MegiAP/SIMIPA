package com.vincode.simipa.network;


import com.vincode.simipa.model.AchievementResponse;
import com.vincode.simipa.model.CalendarResponse;
import com.vincode.simipa.model.ClassScheduleResponse;
import com.vincode.simipa.model.CollegeScheduleResponse;
import com.vincode.simipa.model.KRSResponse;
import com.vincode.simipa.model.LoginResponse;
import com.vincode.simipa.model.PresenceResponse;
import com.vincode.simipa.model.ProfileResponse;
import com.vincode.simipa.model.SeminarPresenceResponse;
import com.vincode.simipa.model.SeminarResponse;
import com.vincode.simipa.model.SeminarScheduleResponse;
import com.vincode.simipa.model.ScholarshipResponse;
import com.vincode.simipa.model.ServiceResponse;
import com.vincode.simipa.model.SplashResponse;
import com.vincode.simipa.model.StudyResponse;
import com.vincode.simipa.model.Value;


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
            @Query("kodeMK") String kodeMK,
            @Query("tahun_akademik") String tahun_akademik,
            @Query("semester") String semester
    );
    @GET("read-seminar.php")
    Call<SeminarScheduleResponse> getSeminarData(
            @Query("jenis") String jenis,
            @Query("jurusan") String jurusan,
            @Query("tgl") String tgl
    );

    @GET("read-beasiswa.php")
    Call<ScholarshipResponse> getScholarshipData(
            @Query("npm") String npm
    );
    @GET("read-prestasi.php")
    Call<AchievementResponse> getAchievementData(
            @Query("npm") String npm,
            @Query("kategori") String kategori
    );

    @GET("read-krs-student.php")
    Call<KRSResponse> getKRSData(
            @Query("semester") String semester,
            @Query("npm") String npm
    );

    //api tes untuk kehadiran seminar
    @FormUrlEncoded
    @POST("insert_daftar_seminar.php")
    Call<Value> setHadirSeminar(
            @Field("npm") String npm,
            @Field("nama") String nama,
            @Field("status") String status,
            @Field("id") String id_seminar
    );

    @FormUrlEncoded
    @POST("update_peserta_seminar.php")
    Call<Value> updateHadirSeminar(
            @Field("npm") String npm,
            @Field("id") String id_seminar
    );

    @GET("cek_daftar_seminar.php")
    Call<Value> cekDaftarSeminar(
            @Query("npm") String npm,
            @Query("id") String id_seminar
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

    @GET("read_seminar.php")
    Call<SeminarPresenceResponse> getSeminar(

    );

    @GET("read-rekapitulasi-seminar-mhs.php")
    Call<SeminarResponse> getRecapSeminar(
            @Query("npm") String npm
    );
}
