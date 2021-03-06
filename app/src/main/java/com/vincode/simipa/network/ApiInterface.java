package com.vincode.simipa.network;


import com.vincode.simipa.BuildConfig;
import com.vincode.simipa.Notification.simipa_parents.NotificationSender;
import com.vincode.simipa.model.AchievementResponse;
import com.vincode.simipa.model.BeritaResponse;
import com.vincode.simipa.model.CalendarResponse;
import com.vincode.simipa.model.ClassScheduleResponse;
import com.vincode.simipa.model.CollegeScheduleResponse;
import com.vincode.simipa.model.KRSResponse;
import com.vincode.simipa.model.LectureResponse;
import com.vincode.simipa.model.LoginResponse;
import com.vincode.simipa.model.PhotoNewsResponse;
import com.vincode.simipa.model.PracticeScheduleResponse;
import com.vincode.simipa.model.PresenceResponse;
import com.vincode.simipa.model.PresenceSeminarResponse;
import com.vincode.simipa.model.ProfileResponse;
import com.vincode.simipa.model.ScholarshipListResponse;
import com.vincode.simipa.model.ScholarshipPost;
import com.vincode.simipa.model.ScholarshipResponse;
import com.vincode.simipa.model.SeminarPresenceResponse;
import com.vincode.simipa.model.SeminarResponse;
import com.vincode.simipa.model.SeminarScheduleResponse;
import com.vincode.simipa.model.ServiceResponse;
import com.vincode.simipa.model.SimipaParentsApprovedParentModel;
import com.vincode.simipa.model.SimipaParentsApprovedParentResponce;
import com.vincode.simipa.model.SimipaParentsDeleteParentModel;
import com.vincode.simipa.model.SimipaParentsDeleteParentResponce;
import com.vincode.simipa.model.SimipaParentsListParentResponce;
import com.vincode.simipa.model.SimipaParentsNotificationResponce;
import com.vincode.simipa.model.SimipaParentsRejectParentModel;
import com.vincode.simipa.model.SimipaParentsRejectParentResponce;
import com.vincode.simipa.model.SimipaParentsUpdateTokenModel;
import com.vincode.simipa.model.SimipaParentsUpdateTokenResponce;
import com.vincode.simipa.model.SplashResponse;
import com.vincode.simipa.model.Status;
import com.vincode.simipa.model.StudyResponse;
import com.vincode.simipa.model.Value;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login-new.php")
    Call<LoginResponse> userLogin(
            @Field("user") String username,
            @Field("pass") String password,
            @Field("imei") String imei,
            @Field("ip") String ip,
            @Field("token") String token,
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
    @GET("read-jadwal-mhs.php")
    Call<PracticeScheduleResponse> getPracticeData(
            @Query("hari") String hari,
            @Query("npm") String npm,
            @Query("tahun_akademik") String tahun_akademik,
            @Query("semester") String semester,
            @Query("type") String type
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

    @GET("read-profile-dosen.php")
    Call<LectureResponse> getLecture(
            @Query("nip") String nip
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

    @GET("read-list-seminar.php")
    Call<PresenceSeminarResponse> getPresenceSeminar(
            @Query("tgl") String tgl
    );

    @GET("read-cek-hadir-seminar.php")
    Call<Status> cekSeminar(
            @Query("npm") String npm,
            @Query("id_seminar") String id_seminar
    );

    @Headers("Content-Type: application/json")
    @POST("create-peserta-seminar.php")
    Call<Value> insertPesertaSeminar(
            @Body String result
    );

    @Headers("Content-Type: application/json")
    @POST("delete-peserta-seminar.php")
    Call<Value> deletePesertaSeminar(
            @Body String result
    );

    @GET("read-berita.php")
    Call<BeritaResponse> getListNews(
    );

    @GET("read-berita-foto.php")
    Call<PhotoNewsResponse> getPhotoNews(
        @Query("id") String idNews
    );

    @Multipart
    @POST("create-prestasi-mhs.php")
    Call<Value> addAchieveStudent(
            @Part MultipartBody.Part fileSertifikat,
            @Part("nama_kegiatan") RequestBody namaKegiatan,
            @Part("penyelenggara") RequestBody penyelenggara,
            @Part("kategori") RequestBody kategori,
            @Part("tingkat") RequestBody tingkat,
            @Part("prestasi") RequestBody prestasi,
            @Part("tahun_pelaksanaan") RequestBody tahun,
            @Part("jenis_peserta") RequestBody jenis,
            @Part("npm") RequestBody npm
    );

    @Multipart
    @POST("create-beasiswa.php")
    Call<ScholarshipPost> insertBeasiswa(
            @Part MultipartBody.Part fileBeasiswa,
            @Part("npm") RequestBody npm,
            @Part("semester") RequestBody semester,
            @Part("tahun_beasiswa") RequestBody tahunBeasiswa,
            @Part("penyelenggara") RequestBody penyelenggara,
            @Part("nama_beasiswa") RequestBody namaBeasiswa
    );

    @GET("read-daftar-beasiswa-aktif.php")
    Call<ScholarshipListResponse> getDaftarBeasiswa(
            @Query("tanggal") String tanggal
    );


//Start SIMIPA modul Orang tua

    @POST("update-token.php")
    Call<SimipaParentsUpdateTokenResponce> updateToken(
            @Body SimipaParentsUpdateTokenModel updateTokenModel
    );

    @GET("read-list-parent.php")
    Call<SimipaParentsListParentResponce> listParent(
            @Query("npm") String npm
    );

    @POST("delete-parent.php")
    Call<SimipaParentsDeleteParentResponce> deleteParent(
            @Body SimipaParentsDeleteParentModel deleteParentModel
    );

    @POST("update-tolak-parent.php")
    Call<SimipaParentsRejectParentResponce> rejectParent(
            @Body SimipaParentsRejectParentModel rejectParentModel
    );

    @POST("update-validasi-parent.php")
    Call<SimipaParentsApprovedParentResponce> approvedParent(
            @Body SimipaParentsApprovedParentModel approvedParentModel
    );

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=" + BuildConfig.BASE_URL_SERVER_FIREBASE_NOTIFICATION
            }
    )
    @POST("fcm/send")
    Call<SimipaParentsNotificationResponce> sendNotifcation(@Body NotificationSender body);

//End SIMIPA modul Orang tua

}
