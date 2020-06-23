package com.vincode.simipa.receiver.simipa_parents;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.vincode.simipa.Notification.simipa_parents.NotificationControl;
import com.vincode.simipa.R;
import com.vincode.simipa.model.SimipaParentsApprovedParentModel;
import com.vincode.simipa.model.SimipaParentsApprovedParentRecord;
import com.vincode.simipa.model.SimipaParentsApprovedParentResponce;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationApprovedRequestReceiver extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String id = intent.getStringExtra("id");
        String user = intent.getStringExtra("user");

        ApprovedData(id, user);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        assert id != null;
        manager.cancel(Integer.parseInt(id));
    }

    public void ApprovedData(final String id, String no_hp) {

        SimipaParentsApprovedParentModel approvedParentModel = new SimipaParentsApprovedParentModel(id, no_hp);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<SimipaParentsApprovedParentResponce> approvedStudent = apiService.approvedParent(approvedParentModel);

        approvedStudent.enqueue(new Callback<SimipaParentsApprovedParentResponce>() {
            @Override
            public void onResponse(@NotNull Call<SimipaParentsApprovedParentResponce> call, @NotNull Response<SimipaParentsApprovedParentResponce> response) {

                if (response.code() != 500) {

                    assert response.body() != null;
                    if (response.body().getResponseCode() == 200) {

                        for (SimipaParentsApprovedParentRecord approvedParentRecord : response.body().getRecords()) {
                            NotificationControl.sendNotification(context,
                                    approvedParentRecord.getToken(),
                                    context.getResources().getString(R.string.parent_notification_title_id_4),
                                    SharedPrefManager.getNameStudent() + " (" + SharedPrefManager.getNpmStudent() + ") " + context.getResources().getString(R.string.parent_notification_message_id_4),
                                    context.getResources().getString(R.string.parent_notification_channel_name_4),
                                    context.getResources().getString(R.string.parent_notification_group_name_4),
                                    id,
                                    SharedPrefManager.getNpmStudent(),
                                    SharedPrefManager.getNameStudent(),
                                    SharedPrefManager.getDepartmentStudent(),
                                    SharedPrefManager.getImageStudent());
                        }

                        Toast.makeText(context, context.getResources().getString(R.string.berhasil_disetujui), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(context, context.getResources().getString(R.string.gagal_disetujui), Toast.LENGTH_SHORT).show();

                    }
                } else {

                    Toast.makeText(context, context.getResources().getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NotNull Call<SimipaParentsApprovedParentResponce> call, @NotNull Throwable t) {

                Toast.makeText(context, context.getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
