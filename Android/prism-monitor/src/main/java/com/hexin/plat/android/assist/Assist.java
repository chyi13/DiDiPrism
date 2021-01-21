package com.hexin.plat.android.assist;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hexin.plat.android.assist.record.RecordManager;
import com.xiaojuchefu.prism.monitor.R;

public class Assist {

    static WindowManager windowManager;
    static LinearLayout floatAction;

    public static void initAssist(Context context) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY ,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        layoutParams.gravity = Gravity.TOP | Gravity.START;
        floatAction = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.assist_floating_view_layout, null);

        windowManager.addView(floatAction, layoutParams);

        FloatingActionButton fab = floatAction.findViewById(R.id.fab);

        fab.setOnTouchListener((View v, MotionEvent event) -> {
            int action = event.getActionMasked();

            long duration = event.getEventTime() - event.getDownTime();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    int x = (int) (event.getRawX() - v.getWidth() / 2);
                    int y = (int) (event.getRawY() - v.getHeight() / 2);
                    WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                            WindowManager.LayoutParams.WRAP_CONTENT,
                            WindowManager.LayoutParams.WRAP_CONTENT,
                            x, y,
                            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY ,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                            PixelFormat.TRANSLUCENT);
                    params.gravity = Gravity.TOP | Gravity.START;
                    windowManager.updateViewLayout(floatAction, params);
                    return true;
                case MotionEvent.ACTION_UP:
                    if (duration < 100) {
                        v.performClick();
                    }
                    return false;
            }
            return false;
        });

        fab.setOnClickListener((view) -> {
            RecordManager.getInstance().showFloatingOperationView(context);
        });
    }
}
