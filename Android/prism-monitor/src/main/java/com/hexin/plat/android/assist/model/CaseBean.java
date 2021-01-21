package com.hexin.plat.android.assist.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class CaseBean {

    @Expose
    String id;
    @Expose
    String name;
    @Expose
    String description;
    @Expose
    long createTime;
    @Expose
    long editTime;
    @Expose
    String user;
    @Expose
    String platform;
    @Expose
    String app;
    @Expose
    String version;
    @Expose
    String device;
    @Expose
    String system;
    @Expose
    ArrayList<ActionBean> actions;

    public CaseBean(String id, String name, String description, long createTime, String user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createTime = createTime;
        this.user = user;
        this.actions = new ArrayList<>();
    }

    public CaseBean(String id, String name, String description, long createTime, long editTime, String user,
                        String platform, String app, String version, String device, String system,
                    ArrayList<ActionBean> actions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createTime = createTime;
        this.editTime = editTime;
        this.user = user;
        this.platform = platform;
        this.app = app;
        this.version = version;
        this.device = device;
        this.system = system;
        this.actions = actions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getEditTime() {
        return editTime;
    }

    public String getUser() {
        return user;
    }

    public String getPlatform() {
        return platform;
    }

    public String getApp() {
        return app;
    }

    public String getVersion() {
        return version;
    }

    public String getDevice() {
        return device;
    }

    public String getSystem() {
        return system;
    }

    public void setActions(ArrayList<ActionBean> actions) {
        this.actions = actions;
    }

    public ArrayList<ActionBean> getActions() {
        return actions;
    }
}
