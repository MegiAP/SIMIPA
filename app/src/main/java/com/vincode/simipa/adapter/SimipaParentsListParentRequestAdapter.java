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
import com.vincode.simipa.model.SimipaParentsApprovedParentModel;
import com.vincode.simipa.model.SimipaParentsApprovedParentRecord;
import com.vincode.simipa.model.SimipaParentsApprovedParentResponce;
import com.vincode.simipa.model.SimipaParentsListParentRecord;
import com.vincode.simipa.model.SimipaParentsRejectParentModel;
import com.vincode.simipa.model.SimipaParentsRejectParentRecord;
import com.vincode.simipa.model.SimipaParentsRejectParentResponce;
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
import retrofit2.Response;

public class SimipaParentsListParentRequestAdapter extends RecyclerView.Adapter<SimipaParentsListParentRequestAdapter.ListViewHolder> {

    private List<SimipaParentsListParentRecord> listParents = new ArrayList<>();
    private Context context;
    private long mLastClickTime = 0;

    public SimipaParentsListParentRequestAdapter(Context context) {
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
    public SimipaParentsListParentRequestAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simipa_parents_request, parent, false);
        return new SimipaParentsListParentRequestAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SimipaParentsListParentRequestAdapter.ListViewHolder holder, int position) {
        final SimipaParentsListParentRecord parent = listParents.get(position);
        holder.tv_nama.setText(parent.getNama());
        holder.tv_no_hp.setText(parent.getNoHp());

        Glide.with(context)
                .load(parent.getFoto())
                .placeholder(R.drawable.ic_simipa_parents_person_white)
                .into(holder.iv_foto_parent);

        holder.btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elapseClick();
                Objects.requireNonNull(holder.viewDialogConfirmReject.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                holder.viewDialogConfirmReject.getWindow().setGravity(Gravity.BOTTOM);
                holder.viewDialogConfirmReject.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                holder.viewDialogConfirmReject.setCanceledOnTouchOutside(false);
                holder.viewDialogConfirmReject.getWindow().setWindowAnimations(R.style.DialogAnimation_up_down);
                holder.viewDialogConfirmReject.show();
            }
        });

        holder.dialogCancelReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elapseClick();
                holder.viewDialogConfirmReject.dismiss();

            }
        });

        holder.dialogReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RejectData(parent.getId(), parent.getNoHp(), holder);
            }
        });

        holder.btn_approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elapseClick();
                Objects.requireNonNull(holder.viewDialogConfirmApproved.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                holder.viewDialogConfirmApproved.getWindow().setGravity(Gravity.BOTTOM);
                holder.viewDialogConfirmApproved.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                holder.viewDialogConfirmApproved.setCanceledOnTouchOutside(false);
                holder.viewDialogConfirmApproved.show();
            }
        });

        holder.dialogCancelApproved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elapseClick();
                holder.viewDialogConfirmApproved.dismiss();

            }
        });

        holder.dialogApproved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApprovedData(parent.getId(), parent.getNoHp(), holder);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listParents.size();
    }

    public void RejectData(final String id, String no_hp, @NonNull final SimipaParentsListParentRequestAdapter.ListViewHolder holder) {

        holder.dialogViewConfirmReject.setVisibility(View.GONE);
        holder.dialogViewLoadingReject.setVisibility(View.VISIBLE);

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
                                    SharedPrefManager.getNameStudent() + " (" + SharedPrefManager.getNameStudent() + ") " + context.getResources().getString(R.string.parent_notification_message_id_3),
                                    context.getResources().getString(R.string.parent_notification_channel_name_3),
                                    context.getResources().getString(R.string.parent_notification_group_name_3),
                                    id,
                                    SharedPrefManager.getNpmStudent(),
                                    SharedPrefManager.getNameStudent(),
                                    SharedPrefManager.getDepartmentStudent(),
                                    SharedPrefManager.getImageStudent());
                        }

                        holder.viewDialogConfirmReject.dismiss();
                        ((SimipaParentsActivity) context).getData();

                        Toast.makeText(context, context.getResources().getString(R.string.berhasil_ditolak), Toast.LENGTH_SHORT).show();

                    } else {

                        holder.viewDialogConfirmReject.dismiss();
                        ((SimipaParentsActivity) context).getData();

                        Toast.makeText(context, context.getResources().getString(R.string.gagal_ditolak), Toast.LENGTH_SHORT).show();

                    }
                } else {

                    holder.viewDialogConfirmReject.dismiss();
                    ((SimipaParentsActivity) context).getData();

                    Toast.makeText(context, context.getResources().getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NotNull Call<SimipaParentsRejectParentResponce> call, @NotNull Throwable t) {

                holder.viewDialogConfirmReject.dismiss();
                ((SimipaParentsActivity) context).getData();

                Toast.makeText(context, context.getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void ApprovedData(final String id, String no_hp, @NonNull final SimipaParentsListParentRequestAdapter.ListViewHolder holder) {

        holder.dialogViewConfirmApproved.setVisibility(View.GONE);
        holder.dialogViewLoadingApproved.setVisibility(View.VISIBLE);

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

                        holder.viewDialogConfirmApproved.dismiss();
                        ((SimipaParentsActivity) context).getData();

                        Toast.makeText(context, context.getResources().getString(R.string.berhasil_disetujui), Toast.LENGTH_SHORT).show();

                    } else {

                        holder.viewDialogConfirmApproved.dismiss();
                        ((SimipaParentsActivity) context).getData();

                        Toast.makeText(context, context.getResources().getString(R.string.gagal_disetujui), Toast.LENGTH_SHORT).show();

                    }
                } else {

                    holder.viewDialogConfirmApproved.dismiss();
                    ((SimipaParentsActivity) context).getData();

                    Toast.makeText(context, context.getResources().getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NotNull Call<SimipaParentsApprovedParentResponce> call, @NotNull Throwable t) {

                holder.viewDialogConfirmApproved.dismiss();
                ((SimipaParentsActivity) context).getData();

                Toast.makeText(context, context.getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        Dialog viewDialogConfirmApproved;
        CardView dialogApproved, dialogCancelApproved;
        View dialogViewConfirmApproved, dialogViewLoadingApproved;

        TextView dialogTextConfirmApproved, dialogTextCancelApproved;
        ImageView dialogIconConfirmApproved, dialogScaleConfirmApproved;

        LinearLayout dialogViewButtonApprovedApproved, dialogViewButtonApproved;

        Dialog viewDialogConfirmReject;
        CardView dialogReject, dialogCancelReject;
        View dialogViewConfirmReject, dialogViewLoadingReject;

        TextView dialogTextConfirmReject, dialogTextCancelReject;
        ImageView dialogIconConfirmReject, dialogScaleConfirmReject;

        LinearLayout dialogViewButtonRejectReject, dialogViewButtonReject;

        TextView tv_nama, tv_no_hp;
        ImageView iv_foto_parent, btn_approved, btn_reject;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            viewDialogConfirmApproved = new Dialog(context);
            viewDialogConfirmApproved.setContentView(R.layout.dialog_simipa_parents_confirm_approved_parent);

            dialogApproved = viewDialogConfirmApproved.findViewById(R.id.cv_approved);
            dialogCancelApproved = viewDialogConfirmApproved.findViewById(R.id.cv_cancel_approved);
            dialogTextConfirmApproved = viewDialogConfirmApproved.findViewById(R.id.text_confirm_approved);
            dialogIconConfirmApproved = viewDialogConfirmApproved.findViewById(R.id.icon_confirm_approved);
            dialogViewConfirmApproved = viewDialogConfirmApproved.findViewById(R.id.view_approved);
            dialogViewLoadingApproved = viewDialogConfirmApproved.findViewById(R.id.view_loading_approved);
            dialogScaleConfirmApproved = viewDialogConfirmApproved.findViewById(R.id.scale_vertical_approved);
            dialogTextCancelApproved = viewDialogConfirmApproved.findViewById(R.id.tv_batal_approved);
            dialogViewButtonApproved = viewDialogConfirmApproved.findViewById(R.id.button_approved);
            dialogViewButtonApprovedApproved = viewDialogConfirmApproved.findViewById(R.id.view_button_approved);

            viewDialogConfirmReject = new Dialog(context);
            viewDialogConfirmReject.setContentView(R.layout.dialog_simipa_parents_confirm_reject_parent);

            dialogReject = viewDialogConfirmReject.findViewById(R.id.cv_reject);
            dialogCancelReject = viewDialogConfirmReject.findViewById(R.id.cv_cancel_reject);
            dialogTextConfirmReject = viewDialogConfirmReject.findViewById(R.id.text_confirm_reject);
            dialogIconConfirmReject = viewDialogConfirmReject.findViewById(R.id.icon_confirm_reject);
            dialogViewConfirmReject = viewDialogConfirmReject.findViewById(R.id.view_reject);
            dialogViewLoadingReject = viewDialogConfirmReject.findViewById(R.id.view_loading_reject);
            dialogScaleConfirmReject = viewDialogConfirmReject.findViewById(R.id.scale_vertical_reject);
            dialogTextCancelReject = viewDialogConfirmReject.findViewById(R.id.tv_batal_reject);
            dialogViewButtonReject = viewDialogConfirmReject.findViewById(R.id.button_reject);
            dialogViewButtonRejectReject = viewDialogConfirmReject.findViewById(R.id.view_button_reject);

            tv_nama = itemView.findViewById(R.id.tv_item_name);
            tv_no_hp = itemView.findViewById(R.id.tv_item_no_hp);
            iv_foto_parent = itemView.findViewById(R.id.img_item_photo);

            btn_approved = itemView.findViewById(R.id.btn_approved);
            btn_reject = itemView.findViewById(R.id.btn_reject);
        }
    }
}
