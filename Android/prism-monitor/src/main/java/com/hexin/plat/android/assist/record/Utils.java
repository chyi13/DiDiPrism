package com.hexin.plat.android.assist.record;

import com.hexin.plat.android.assist.floating.MenuItem;
import com.hexin.plat.android.assist.model.ActionBean;
import com.hexin.plat.android.assist.model.CaseBean;
import com.hexin.plat.android.assist.model.OperationBean;
import com.xiaojuchefu.prism.monitor.PrismConstants;
import com.xiaojuchefu.prism.monitor.model.EventData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utils {

    public static ActionBean createActionBean(int index, String extra, String action, EventData eventData) {
        return new ActionBean(index, eventData.getUnionId(), "", extra, action);
    }

    public static OperationBean convertToOperation(MenuItem item) {
        if (item != null) {
            return new OperationBean(item.getId(), "");
        }
        return null;
    }

    public static CaseBean createCaseBean(String name, String description, String user) {
        String id = UUID.randomUUID().toString();
        long createTime = System.currentTimeMillis();
        return new CaseBean(id, name, description, createTime, user);
    }

    public static ArrayList<EventData> convertActionBeanToEventData(List<ActionBean> actions) {
        ArrayList<EventData> events = new ArrayList<>();
        for (ActionBean action: actions) {
            EventData even = new EventData(PrismConstants.Event.TOUCH);
            even.eventId = action.getObjectId();
            events.add(even);
        }
        return events;
    }
}
