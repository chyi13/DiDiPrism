package com.hexin.plat.android.assist.model;

public class OperationBean {
    String operationType;
    String extra;

    public OperationBean(String operationType, String extra) {
        this.operationType = operationType;
        this.extra = extra;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getExtra() {
        return extra;
    }
}
