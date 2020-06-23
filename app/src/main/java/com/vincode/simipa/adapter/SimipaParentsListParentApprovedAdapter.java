package com.vincode.simipa.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vincode.simipa.Notification.simipa_parents.NotificationControl;
import com.vincode.simipa.R;
import com.vincode.simipa.model.SimipaParentsDeleteParentModel;
import com.vincode.simipa.model.SimipaParentsDeleteParentRecord;
import com.vincode.simipa.model.SimipaParentsDeleteParentResponce;
import com.vincode.simipa.model.SimipaParentsListParentRecord;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.ui.simipa_parents.SimipaParentsActivity;
import com.vincode.simipa.util.SharedPrefManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class SimipaParentsListParentApprovedAdapter extends RecyclerView.Adapter<SimipaParentsListParentApprovedAdapter.ListViewHolder> {

    final Context context;
    private List<SimipaParentsListParentRecord> listParents = new ArrayList<>();
    private long mLastClickTime = 0;

    public SimipaParentsListParentApprovedAdapter(Context context) {
        this.context = context;
    }

    public void elapseClick() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
    }

    public void setListParents(List<SimipaParentsListParentRecord> parents) {
        if (parents == null) return;
        this.listParents.clear();
        this.listParents.addAll(parents);
    }

    @NotNull
    @Override
    public SimipaParentsListParentApprovedAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simipa_parents_approved, parent, false);
        return new SimipaParentsListParentApprovedAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SimipaParentsListParentApprovedAdapter.ListViewHolder holder, int position) {
        final SimipaParentsListParentRecord parent = listParents.get(position);
        holder.tv_nama.setText(parent.getNama());
        holder.tv_no_hp.setText(parent.getNoHp());

        Glide.with(context)
                .load(parent.getFoto())
                .placeholder(R.drawable.ic_simipa_parents_person_white)
                .into(holder.iv_foto_parent);

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elapseClick();
                Objects.requireNonNull(holder.viewDialogConfirmDelete.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                holder.viewDialogConfirmDelete.getWindow().setGravity(Gravity.BOTTOM);
                holder.viewDialogConfirmDelete.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                holder.viewDialogConfirmDelete.setCanceledOnTouchOutside(false);
                holder.viewDialogConfirmDelete.getWindow().setWindowAnimations(R.style.DialogAnimation_up_down);
                holder.viewDialogConfirmDelete.show();
            }
        });

        holder.dialogCancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elapseClick();
                holder.viewDialogConfirmDelete.dismiss();

//                if (reload)
//                    ((ProfileFragment) fragment).getData();
            }
        });

        holder.dialogDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData(parent.getNoHp(), parent.getId(), holder);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listParents.size();
    }

    public void DeleteData(String no_hp, final String id, @NonNull final SimipaParentsListParentApprovedAdapter.ListViewHolder holder) {

        holder.dialogViewConfirmDelete.setVisibility(View.GONE);
        holder.dialogViewLoadingDelete.setVisibility(View.VISIBLE);

        SimipaParentsDeleteParentModel deleteParentModel = new SimipaParentsDeleteParentModel(id, no_hp);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<SimipaParentsDeleteParentResponce> deleteStudent = apiService.deleteParent(deleteParentModel);

        deleteStudent.enqueue(new Callback<SimipaParentsDeleteParentResponce>() {
            @Override
            public void onResponse(@NotNull Call<SimipaParentsDeleteParentResponce> call, @NotNull retrofit2.Response<SimipaParentsDeleteParentResponce> response) {

                if (response.code() != 500) {

                    assert response.body() != null;
                    if (response.body().getResponseCode() == 200) {

                        for (SimipaParentsDeleteParentRecord deleteParentRecord : response.body().getRecords()) {
                            NotificationControl.sendNotification(context,
                                    deleteParentRecord.getToken(),
                                    context.getResources().getString(R.string.parent_notification_title_id_2),
                                    SharedPrefManager.getNameStudent() + " (" + SharedPrefManager.getNpmStudent() + ") " + context.getResources().getString(R.string.parent_notification_message_id_2),
                                    context.getResources().getString(R.string.parent_notification_channel_name_2),
                                    context.getResources().getString(R.string.parent_notification_group_name_2),
                                    id,
                                    SharedPrefManager.getNpmStudent(),
                                    SharedPrefManager.getNameStudent(),
                                    SharedPrefManager.getDepartmentStudent(),
                                    SharedPrefManager.getImageStudent());
                        }

                        holder.viewDialogConfirmDelete.dismiss();
                        ((SimipaParentsActivity) context).getData();

                        Toast.makeText(context, context.getResources().getString(R.string.berhasil_dihapus), Toast.LENGTH_SHORT).show();


                    } else {

                        holder.viewDialogConfirmDelete.dismiss();
                        ((SimipaParentsActivity) context).getData();

                        Toast.makeText(context, context.getResources().getString(R.string.gagal_dihapus), Toast.LENGTH_SHORT).show();

                    }
                } else {

                    holder.viewDialogConfirmDelete.dismiss();
                    ((SimipaParentsActivity) context).getData();

                    Toast.makeText(context, context.getResources().getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NotNull Call<SimipaParentsDeleteParentResponce> call, @NotNull Throwable t) {

                holder.viewDialogConfirmDelete.dismiss();
                ((SimipaParentsActivity) context).getData();

                Toast.makeText(context, context.getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        Dialog viewDialogConfirmDelete;
        CardView dialogDelete, dialogCancelDelete;
        View dialogViewConfirmDelete, dialogViewLoadingDelete;

        TextView dialogTextConfirmDelete, dialogTextCancelDelete;
        ImageView dialogIconConfirmDelete, dialogScaleConfirmDelete;

        LinearLayout dialogViewButtonDeleteDelete, dialogViewButtonDelete;

        TextView tv_nama, tv_no_hp;
        ImageView iv_foto_parent, btn_delete;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            viewDialogConfirmDelete = new Dialog(context);
            viewDialogConfirmDelete.setContentView(R.layout.dialog_simipa_parents_confirm_delete_parent);

            dialogDelete = viewDialogConfirmDelete.findViewById(R.id.cv_delete);
            dialogCancelDelete = viewDialogConfirmDelete.findViewById(R.id.cv_cancel_delete);
            dialogTextConfirmDelete = viewDialogConfirmDelete.findViewById(R.id.text_confirm_delete);
            dialogIconConfirmDelete = viewDialogConfirmDelete.findViewById(R.id.icon_confirm_delete);
            dialogViewConfirmDelete = viewDialogConfirmDelete.findViewById(R.id.view_delete);
            dialogViewLoadingDelete = viewDialogConfirmDelete.findViewById(R.id.view_loading_delete);
            dialogScaleConfirmDelete = viewDialogConfirmDelete.findViewById(R.id.scale_vertical_delete);
            dialogTextCancelDelete = viewDialogConfirmDelete.findViewById(R.id.tv_batal_delete);
            dialogViewButtonDelete = viewDialogConfirmDelete.findViewById(R.id.button_delete);
            dialogViewButtonDeleteDelete = viewDialogConfirmDelete.findViewById(R.id.view_button_delete);

            tv_nama = itemView.findViewById(R.id.tv_item_name);
            tv_no_hp = itemView.findViewById(R.id.tv_item_no_hp);
            iv_foto_parent = itemView.findViewById(R.id.img_item_photo);

            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
