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
import static com.hexin.plat.android.assist.Configurations.OperationMenu;
import static com.hexin.plat.android.assist.Configurations.OperationSubMenu;

public class FloatingOperationView extends LinearLayout {
    public FloatingOperationView(Context context) {
        super(context);
    }

    public FloatingOperationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingOperationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FloatingOperationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    FloatingMenuTabLayout mFloatingMenuTabLayout;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mFloatingMenuTabLayout = findViewById(R.id.operation_tab_layout);
        mFloatingMenuTabLayout.init(OperationMenu, OperationSubMenu);
    }

    public static FloatingOperationView createOperationList(Context context) {
        return (FloatingOperationView) LayoutInflater.from(context).inflate(R.layout.assist_floating_operations, null);
    }
}
