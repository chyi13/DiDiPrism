package com.hexin.plat.android.assist.record;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hexin.plat.android.assist.callback.OnPlaybackCallback;
import com.hexin.plat.android.assist.floating.FloatingActionView;
import com.hexin.plat.android.assist.floating.FloatingCaseListView;
import com.hexin.plat.android.assist.floating.FloatingOperationView;
import com.hexin.plat.android.assist.floating.MenuItem;
import com.hexin.plat.android.assist.model.ActionBean;
import com.hexin.plat.android.assist.model.CaseBean;
import com.hexin.plat.android.assist.model.OperationBean;
import com.xiaojuchefu.prism.monitor.PrismMonitor;
import com.xiaojuchefu.prism.monitor.model.EventData;
import com.xiaojuchefu.prism.monitor.touch.TouchEventHelper;
import com.xiaojuchefu.prism.monitor.touch.TouchRecord;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.hexin.plat.android.assist.Configurations.ACTION_ASSERT;
import static com.hexin.plat.android.assist.Configurations.ACTION_CLICK;
import static com.hexin.plat.android.assist.Configurations.ACTION_CLICK_IF_EXISTS;
import static com.hexin.plat.android.assist.Configurations.ACTION_INPUT;
import static com.hexin.plat.android.assist.Configurations.ACTION_SCROLL_DOWN;
import static com.hexin.plat.android.assist.Configurations.ACTION_SCROLL_LEFT;
import static com.hexin.plat.android.assist.Configurations.ACTION_SCROLL_RIGHT;
import static com.hexin.plat.android.assist.Configurations.ACTION_SCROLL_UP;
import static com.hexin.plat.android.assist.Configurations.KEY_APP_SCROLL;
import static com.hexin.plat.android.assist.Configurations.KEY_ASSERT;
import static com.hexin.plat.android.assist.Configurations.KEY_CASES;
import static com.hexin.plat.android.assist.Configurations.KEY_CLICK;
import static com.hexin.plat.android.assist.Configurations.KEY_DEVICE;
import static com.hexin.plat.android.assist.Configurations.KEY_INPUT;
import static com.hexin.plat.android.assist.Configurations.KEY_PROCESS;
import static com.hexin.plat.android.assist.Configurations.KEY_SCROLL;
import static com.hexin.plat.android.assist.Configurations.OPERATION_CASES;
import static com.hexin.plat.android.assist.Configurations.OPERATION_FINISH;
import static com.hexin.plat.android.assist.Configurations.OPERATION_START;

public class RecordManager {

    private static RecordManager recordManager;

    public synchronized static RecordManager getInstance() {
        if (recordManager == null) {
            synchronized (RecordManager.class) {
                if (recordManager == null) {
                    recordManager = new RecordManager();
                }
            }
        }
        return recordManager;
    }

    boolean isRecording = false;
    CaseBean caseBean;
    int actionIndex = 0;
    List<EventData> mPlaybackEvents = new ArrayList<>(100);
    PrismMonitor.OnPrismMonitorListener mOnPrismMonitorListener = eventData -> {
        Log.d("onEvent2", eventData.getUnionId());
        mPlaybackEvents.add(eventData);
    };

    RecordManager() {

    }

    public void processMenuItem(MenuItem menuItem) {
        switch (menuItem.getType()) {
            case KEY_CLICK:
            case KEY_INPUT:
            case KEY_SCROLL:
            case KEY_ASSERT:
                // TODO
                postAction(menuItem);
                break;
            case KEY_APP_SCROLL:
                // TODO
                break;
            case KEY_DEVICE: // 设备

                break;
            case KEY_CASES: // 用例
            case KEY_PROCESS: // 流程
                postOperation(Utils.convertToOperation(menuItem));
                break;
            default:
                break;
        }
    }

    private void postOperation(OperationBean operationBean) {
        switch (operationBean.getOperationType()) {
            case OPERATION_START: // 开始/暂停
                if (!isRecording) {
                    actionIndex = 0;
                    caseBean = Utils.createCaseBean("测试", "测试描述", "chaiyi");
                    PrismMonitor.getInstance().start();
                    PrismMonitor.getInstance().addOnPrismMonitorListener(mOnPrismMonitorListener);
                    isRecording = true;
                } else {
                    PrismMonitor.getInstance().pause();
                    isRecording = false;
                }
                break;
            case OPERATION_FINISH: // 结束
                PrismMonitor.getInstance().stop();
                StorageManager.getInstance().saveRecord(caseBean);
            case OPERATION_CASES: // 查看所有用例
                ArrayList<CaseBean> caseList = StorageManager.getInstance().loadRecords();
                showFloatingCasesView(context, caseList);
                break;
        }
    }

    private void postAction(MenuItem action) {
        if (action != null && mTargetView != null) {
            String extra = "";
            switch (action.getId()) {
                case ACTION_CLICK:
                    simulateClick();
                    break;
                case ACTION_CLICK_IF_EXISTS:
                    extra = "";
                    simulateClick();
                    break;
                case ACTION_INPUT:
                    // 用户进行输入
                    extra = "";
                    break;
                case ACTION_SCROLL_DOWN:
                    // 用户进行输入
                    extra = "500";
                    break;
                case ACTION_SCROLL_UP:
                    // 用户进行输入
                    extra = "500";
                    break;
                case ACTION_SCROLL_LEFT:
                    // 用户进行输入
                    extra = "500";
                    break;
                case ACTION_SCROLL_RIGHT:
                    // 用户进行输入
                    extra = "500";
                    break;
                case ACTION_ASSERT:
                    // 用户进行输入
                    extra = "500";
                    break;
                default:
                    break;
            }
            hideFloatingActionView();
            EventData eventData = createRecordEvent();
            ActionBean actionBean = Utils.createActionBean(actionIndex, extra, action.getId(), eventData);
            caseBean.getActions().add(actionBean);
            actionIndex ++;
        }
    }

    private FloatingActionView floatingActionView;
    private WeakReference<WindowManager> floatingActionViewWMRef;
    private FloatingOperationView floatingOperationView;
    private WeakReference<WindowManager> floatingOperationViewWMRef;
    private FloatingCaseListView floatingCaseListView;
    private WeakReference<WindowManager> floatingCaseListViewWMRef;

    private void addView(Context context, View viewToAdd) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    0, 0,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);
            layoutParams.gravity = Gravity.CENTER;
            if (viewToAdd instanceof FloatingOperationView) {
                floatingOperationViewWMRef = new WeakReference<>(windowManager);
                hideFloatingActionView();
                hideFloatingCasesView();
            } else if (viewToAdd instanceof FloatingActionView) {
                floatingActionViewWMRef = new WeakReference<>(windowManager);
                hideFloatingOperationView();
                hideFloatingCasesView();
            } else if (viewToAdd instanceof FloatingCaseListView) {
                floatingCaseListViewWMRef = new WeakReference<>(windowManager);
                hideFloatingActionView();
                hideFloatingOperationView();
            }
            windowManager.addView(viewToAdd, layoutParams);
        }
    }

    public void showFloatingActionView(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        floatingActionView = FloatingActionView.createActionList(context);
        floatingActionView.setOnTouchListener((v, event) -> {
            int action = event.getActionMasked();
            switch (action) {
                case MotionEvent.ACTION_OUTSIDE:
                    hideFloatingActionView();
                    return true;
            }
            return false;
        });
        addView(context, floatingActionView);
    }

    public void hideFloatingActionView() {
        if (floatingActionView != null && floatingActionViewWMRef != null) {
            WindowManager windowManager = floatingActionViewWMRef.get();
            if (windowManager != null) {
                windowManager.removeView(floatingActionView);
                floatingActionViewWMRef.clear();
            }
            floatingActionView = null;
        }
    }

    Context context;
    public void showFloatingOperationView(Context context) {
        floatingOperationView = FloatingOperationView.createOperationList(context);
        floatingOperationView.setOnTouchListener((v, event) -> {
            int action = event.getActionMasked();
            if (action == MotionEvent.ACTION_OUTSIDE) {
                hideFloatingOperationView();
                return true;
            }
            return false;
        });
        this.context = context;
        addView(context, floatingOperationView);
    }

    public void hideFloatingOperationView() {
        if (floatingOperationView != null && floatingOperationViewWMRef != null) {
            WindowManager windowManager = floatingOperationViewWMRef.get();
            if (windowManager != null) {
                windowManager.removeView(floatingOperationView);
                floatingOperationViewWMRef.clear();
            }
            floatingOperationView = null;
        }
    }

    public void showFloatingCasesView(Context context, ArrayList<CaseBean> caseList) {
        floatingCaseListView = FloatingCaseListView.create(context);
        floatingCaseListView.setCaseList(caseList);
        floatingCaseListView.setOnTouchListener((v, event) -> {
            int action = event.getActionMasked();
            if (action == MotionEvent.ACTION_OUTSIDE) {
                hideFloatingCasesView();
                return true;
            }
            return false;
        });
        this.context = context;
        addView(context, floatingCaseListView);
    }

    public void hideFloatingCasesView() {
        if (floatingCaseListView != null && floatingCaseListViewWMRef != null) {
            WindowManager windowManager = floatingCaseListViewWMRef.get();
            if (windowManager != null) {
                windowManager.removeView(floatingCaseListView);
                floatingCaseListViewWMRef.clear();
            }
            floatingCaseListView = null;
        }
    }

    View mTargetView;
    Window mTargetWindow;
    TouchRecord mTouchRecord;

    public void setTargetView(Window window, View view, TouchRecord touchRecord) {
        this.mTargetView = view;
        this.mTargetWindow = window;
        this.mTouchRecord = touchRecord;
    }

    private EventData createRecordEvent() {
        EventData eventData = TouchEventHelper.createEventData(mTargetWindow, mTargetView, mTouchRecord);
        if (eventData != null) {
            PrismMonitor.getInstance().post(eventData);
        }
        return eventData;
    }

    private void simulateClick() {
        if (mTargetView != null) {
            int[] outLocation = new int[2];
            mTargetView.getLocationOnScreen(outLocation);
            float x = outLocation[0] + mTargetView.getWidth() / 2;
            float y = outLocation[1] + mTargetView.getHeight() / 2;
            long downTime = SystemClock.uptimeMillis();
            final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime, MotionEvent.ACTION_DOWN, x, y, 0);
            final MotionEvent upEvent = MotionEvent.obtain(downTime + 100, downTime + 100, MotionEvent.ACTION_UP, x, y, 0);
            mTargetView.onTouchEvent(downEvent);
            mTargetView.onTouchEvent(upEvent);
            downEvent.recycle();
            upEvent.recycle();
        }
    }

    OnPlaybackCallback onPlaybackCallback;
    public void setOnPlaybackCallback(OnPlaybackCallback callback) {
        this.onPlaybackCallback = callback;
    }

    public void playback(ArrayList<EventData> events) {
        if (onPlaybackCallback != null) {
            onPlaybackCallback.onPlayback(events);
        }
    }
}
