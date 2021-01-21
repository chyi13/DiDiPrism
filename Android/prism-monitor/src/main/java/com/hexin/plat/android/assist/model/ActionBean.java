package com.hexin.plat.android.assist.model;

import com.google.gson.annotations.Expose;
import com.xiaojuchefu.prism.monitor.model.EventData;

import java.util.UUID;

public class ActionBean {

    @Expose
    int index;
    @Expose
    String id;
    @Expose
    String objectId;
    @Expose
    String pageId;
    @Expose
    String extra;
    @Expose
    String action;
    @Expose
    String time;
    @Expose
    String result;

    EventData eventData;

    public ActionBean(int index, String objectId, String pageId, String extra, String action) {
        this.index = index;
        this.id = UUID.randomUUID().toString();
        this.objectId = objectId;
        this.pageId = pageId;
        this.extra = extra;
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getExtra() {
        return extra;
    }

    public String getResult() {
        return result;
    }
}
