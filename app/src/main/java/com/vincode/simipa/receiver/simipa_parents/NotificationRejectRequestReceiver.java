package com.vincode.simipa.receiver.simipa_parents;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.vincode.simipa.Notification.simipa_parents.NotificationControl;
import com.vincode.simipa.R;
import com.vincode.simipa.model.SimipaParentsRejectParentModel;
import com.vincode.simipa.model.SimipaParentsRejectParentRecord;
import com.vincode.simipa.model.SimipaParentsRejectParentResponce;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationRejectRequestReceiver extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String id = intent.getStringExtra("id");
        String user = intent.getStringExtra("user");

        RejectData(id, user);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        assert id != null;
        manager.cancel(Integer.parseInt(id));
    }

    public void RejectData(final String id, String no_hp) {

        SimipaParentsRejectParentModel rejectParentModel = new SimipaParentsRejectParentModel(id, no_hp);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        final Call<SimipaParentsRejectParentResponce> rejectStudent = apiService.rejectParent(rejectParentModel);

        rejectStudent.enqueue(new Callback<SimipaParentsRejectParentResponce>() {
            @Override
            public void onResponse(@NotNull Call<SimipaParentsRejectParentResponce> call, @NotNull Response<SimipaParentsRejectParentResponce> response) {

                if (response.code() != 500) {

                    assert response.body() != null;
                    if (response.body().getResponseCode() == 200) {

                        for (SimipaParentsRejectParentRecord rejectParentRecord : response.body().getRecords()) {
                            NotificationControl.sendNotification(context,
                                    rejectParentRecord.getToken(),
                                    context.getResources().getString(R.string.parent_notification_title_id_3),
                                    SharedPrefManager.getNameStudent() + " (" + SharedPrefManager.getNpmStudent() + ") " + context.getResources().getString(R.string.parent_notification_message_id_3),
                                    context.getResources().getString(R.string.parent_notification_channel_name_3),
                                    context.getResources().getString(R.string.parent_notification_group_name_3),
                                    id,
                                    SharedPrefManager.getNpmStudent(),
                                    SharedPrefManager.getNameStudent(),
                                    SharedPrefManager.getDepartmentStudent(),
                                    SharedPrefManager.getImageStudent());
                        }

                        Toast.makeText(context, context.getResources().getString(R.string.berhasil_ditolak), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(context, context.getResources().getString(R.string.gagal_ditolak), Toast.LENGTH_SHORT).show();

                    }
                } else {

                    Toast.makeText(context, context.getResources().getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NotNull Call<SimipaParentsRejectParentResponce> call, @NotNull Throwable t) {

                Toast.makeText(context, context.getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
