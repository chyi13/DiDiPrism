package com.hexin.plat.android.assist.floating;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xiaojuchefu.prism.monitor.R;

import androidx.annotation.Nullable;

import static com.hexin.plat.android.assist.Configurations.ActionMenu;
import static com.hexin.plat.android.assist.Configurations.ActionSubMenu;

public class FloatingActionView extends LinearLayout {
    public FloatingActionView(Context context) {
        super(context);
    }

    public FloatingActionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingActionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FloatingActionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    FloatingMenuTabLayout mFloatingMenuTabLayout;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // create action layout
        mFloatingMenuTabLayout = findViewById(R.id.action_tab_layout);
        mFloatingMenuTabLayout.init(ActionMenu, ActionSubMenu);
    }

    public static FloatingActionView createActionList(Context context) {
        return (FloatingActionView) LayoutInflater.from(context).inflate(R.layout.assist_floating_actions, null);
    }
}
