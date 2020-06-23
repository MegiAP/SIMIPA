package com.vincode.simipa.Notification.simipa_parents;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.vincode.simipa.R;
import com.vincode.simipa.model.SimipaParentsNotificationModel;
import com.vincode.simipa.model.SimipaParentsNotificationResponce;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationControl {

    public static void sendNotification(final Context context, String usertoken, String title, String message, String channel_id, String group_id, String id, String user, String name, String department, String path) {

        SimipaParentsNotificationModel data = new SimipaParentsNotificationModel(title, message, channel_id, group_id, id, user, name, department, path);
        NotificationSender sender = new NotificationSender(data, usertoken);

        Log.d("Token", "upload");

        ApiInterface apiService = ApiClient.getClientNotification().create(ApiInterface.class);

        Call<SimipaParentsNotificationResponce> sendNotifcation = apiService.sendNotifcation(sender);
        sendNotifcation.enqueue(new Callback<SimipaParentsNotificationResponce>() {
            @SuppressLint("ShowToast")
            @Override
            public void onResponse(@NotNull Call<SimipaParentsNotificationResponce> call, @NotNull Response<SimipaParentsNotificationResponce> response) {
                Log.d("Token", response.code() + "");
                Log.d("Token", response.message() + "");

                if (response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().success != 1) {
                        Toast.makeText(context, context.getApplicationContext().getString(R.string.gagal_mengirim_notifikasi), Toast.LENGTH_LONG);
                    } else {
                        Log.d("Token", "Notification sukses");
                    }
                }
            }

            @SuppressLint("ShowToast")
            @Override
            public void onFailure(@NotNull Call<SimipaParentsNotificationResponce> call, @NotNull Throwable t) {
                Log.d("Token", "failed");
                Toast.makeText(context, context.getApplicationContext().getString(R.string.gagal_mengirim_notifikasi), Toast.LENGTH_LONG);
            }
        });

    }
}
