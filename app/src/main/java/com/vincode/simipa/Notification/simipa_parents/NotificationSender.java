package com.vincode.simipa.Notification.simipa_parents;

import com.vincode.simipa.model.SimipaParentsNotificationModel;

public class NotificationSender {

    public SimipaParentsNotificationModel data;
    public String to;

    public NotificationSender(SimipaParentsNotificationModel data, String to) {
        this.data = data;
        this.to = to;
    }

}
