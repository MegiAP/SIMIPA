package com.vincode.simipa.service.simipa_parents;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vincode.simipa.R;
import com.vincode.simipa.model.SimipaParentsUpdateTokenModel;
import com.vincode.simipa.model.SimipaParentsUpdateTokenResponce;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.receiver.simipa_parents.NotificationApprovedRequestReceiver;
import com.vincode.simipa.receiver.simipa_parents.NotificationRejectRequestReceiver;
import com.vincode.simipa.ui.main.MainActivity;
import com.vincode.simipa.util.SharedPrefManager;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages
        // are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data
        // messages are the type
        // traditionally used with GCM. Notification messages are only received here in
        // onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated
        // notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages
        // containing both notification
        // and data payloads are treated as notification messages. The Firebase console always
        // sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getData());

        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");
        String channelId = remoteMessage.getData().get("channel_id");
        String group_id = remoteMessage.getData().get("group_id");
        String id = remoteMessage.getData().get("id");
        String user = remoteMessage.getData().get("user");
        String path = remoteMessage.getData().get("photo_path");

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        Log.d(TAG, "Link path: " + path);
        Bitmap photoProfil = getCircleBitmap(Objects.requireNonNull(getBitmapFromURL(path)));

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Intent broadcastIntentApproved = new Intent(this, NotificationApprovedRequestReceiver.class);
        broadcastIntentApproved.putExtra("id", id);
        broadcastIntentApproved.putExtra("user", user);
        PendingIntent actionIntentApproved = PendingIntent.getBroadcast(this,
                0, broadcastIntentApproved, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent broadcastIntentReject = new Intent(this, NotificationRejectRequestReceiver.class);
        broadcastIntentReject.putExtra("id", id);
        broadcastIntentReject.putExtra("user", user);
        PendingIntent actionIntentReject = PendingIntent.getBroadcast(this,
                0, broadcastIntentReject, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder;

        assert channelId != null;
        if (channelId.equals(getApplicationContext().getResources().getString(R.string.student_notification_channel_name_1))) {

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_simipa_parents_notification_unila)
                    .setLargeIcon(photoProfil)
                    .setContentTitle(title)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(message))
                    .setAutoCancel(true)
                    .setGroup(group_id)
                    .addAction(R.drawable.ic_simipa_parents_check, getApplicationContext().getResources().getString(R.string.konfirmasi), actionIntentApproved)
                    .addAction(R.drawable.ic_simipa_parents_clear, getApplicationContext().getResources().getString(R.string.tolak), actionIntentReject)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                    .setColor(getResources().getColor(R.color.colorPrimary));
        } else {

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_simipa_parents_notification_unila)
                    .setLargeIcon(photoProfil)
                    .setContentTitle(title)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(message))
                    .setAutoCancel(true)
                    .setGroup(group_id)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                    .setColor(getResources().getColor(R.color.colorPrimary));
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    channelId,
                    NotificationManager.IMPORTANCE_DEFAULT);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        assert notificationManager != null;
        assert id != null;
        notificationManager.notify(Integer.parseInt(id), notificationBuilder.build());

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private Bitmap getCircleBitmap(Bitmap bitmapFromURL) {
        Bitmap bitmap;
        Rect srcRect, dstRect;
        float r;
        final int Lebar = bitmapFromURL.getWidth();
        final int tinggi = bitmapFromURL.getHeight();

        if (Lebar > tinggi) {
            bitmap = Bitmap.createBitmap(tinggi, tinggi, Bitmap.Config.ARGB_8888);
            int left = (Lebar - tinggi) / 2;
            int right = left + tinggi;
            srcRect = new Rect(left, 0, right, tinggi);
            dstRect = new Rect(0, 0, tinggi, tinggi);
            r = tinggi / 2f;
        } else {
            bitmap = Bitmap.createBitmap(Lebar, Lebar, Bitmap.Config.ARGB_8888);
            int top = (tinggi - Lebar) / 2;
            int bottom = top + Lebar;
            srcRect = new Rect(0, top, Lebar, bottom);
            dstRect = new Rect(0, 0, Lebar, Lebar);
            r = Lebar / 2f;
        }

        Canvas canvas = new Canvas(bitmap);

        final int color = 0xff424242;
        final Paint paint = new Paint();

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapFromURL, srcRect, dstRect, paint);

        bitmapFromURL.recycle();

        return bitmap;
    }

    private Bitmap getBitmapFromURL(String path) {
        try {

            Log.d(TAG, "Status: good");
            URL url = new URL(path);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            return BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();

            Log.d(TAG, "Status: null");

            return null;
        }
    }
    // [END receive_message]


    // [START on_new_token]

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }
    // [END on_new_token]

    private void sendRegistrationToServer(final String token) {
        // TODO: Implement this method to send token to your app server.

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        String imei = getUniqueIMEIId(getApplicationContext());

        SimipaParentsUpdateTokenModel updateTokenModel = new SimipaParentsUpdateTokenModel(SharedPrefManager.getNpmStudent(), token, imei);

        Call<SimipaParentsUpdateTokenResponce> updateToken = apiService.updateToken(updateTokenModel);
        updateToken.enqueue(new Callback<SimipaParentsUpdateTokenResponce>() {
            @Override
            public void onResponse(@NotNull Call<SimipaParentsUpdateTokenResponce> call, @NotNull retrofit2.Response<SimipaParentsUpdateTokenResponce> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getResponseCode() == 200) {

                        if (response.body().getTotalRecords() > 0) {
                            Log.d(TAG, "Success Refreshed token to Server: " + token);
                        } else {
                            Log.d(TAG, "Failed Refreshed token to Server: " + token);
                        }

                    } else if (response.body().getResponseCode() == 400) {
                        Log.d(TAG, "Something Wrong!!!");
                    }
                } else {
                    Log.d(TAG, "Something Wrong!!!");
                }
            }

            @Override
            public void onFailure(@NotNull Call<SimipaParentsUpdateTokenResponce> call, @NotNull Throwable t) {
                Log.d(TAG, "Server Error!!!");
            }
        });

    }

    @SuppressLint("HardwareIds")
    public String getUniqueIMEIId(Context context) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei;

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                imei = "Permission granted - Android Q or newer - " + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                assert telephonyManager != null;
                imei = telephonyManager.getImei();
            } else {
                assert telephonyManager != null;
                imei = telephonyManager.getDeviceId();
            }

            if (imei != null && !imei.isEmpty()) {
                return imei;
            } else {
                return android.os.Build.SERIAL;
            }

        }

        return "Permission denied - Android P or older -" + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
