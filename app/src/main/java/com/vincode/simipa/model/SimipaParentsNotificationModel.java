package com.vincode.simipa.model;

import com.google.gson.annotations.SerializedName;

public class SimipaParentsNotificationModel {

    @SerializedName("title")
    private String title;
    @SerializedName("message")
    private String message;
    @SerializedName("channel_id")
    private String channel_id;
    @SerializedName("grup_id")
    private String group_id;
    @SerializedName("id")
    private String id;
    @SerializedName("user")
    private String user;
    @SerializedName("name")
    private String name;
    @SerializedName("department")
    private String department;
    @SerializedName("path")
    private String path;

    public SimipaParentsNotificationModel(String title, String message, String channel_id, String group_id, String id, String user, String name, String department, String path) {
        this.title = title;
        this.message = message;
        this.channel_id = channel_id;
        this.group_id = group_id;
        this.id = id;
        this.user = user;
        this.name = name;
        this.department = department;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
