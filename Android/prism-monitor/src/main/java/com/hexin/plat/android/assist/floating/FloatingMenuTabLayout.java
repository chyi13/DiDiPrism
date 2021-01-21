package com.hexin.plat.android.assist.floating;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.eastwood.common.adapter.QuickAdapter;
import com.eastwood.common.adapter.ViewHelper;
import com.xiaojuchefu.prism.monitor.R;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

public class FloatingMenuTabLayout extends LinearLayout {

    public FloatingMenuTabLayout(Context context) {
        super(context);
    }

    public FloatingMenuTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingMenuTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FloatingMenuTabLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    int selectedTabIndex = -1;

    public void init(ArrayList<MenuItem> menu, HashMap<String, ArrayList<MenuItem>> subMenu) {
        ViewPager2 viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new FloatingMenuSubPagerAdapter(getContext(), menu, subMenu));
        viewPager.setUserInputEnabled(false);

        ListView tabListView = findViewById(R.id.tabs);
        tabListView.setAdapter(new QuickAdapter<MenuItem>(getContext(), R.layout.assist_floating_tab, menu) {
            @Override
            protected void convert(int position, ViewHelper helper, MenuItem tab) {
                helper.setText(R.id.tab_name, tab.name);
            }
        });

        tabListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedTabIndex = position;
            viewPager.setCurrentItem(position, false);
        });
    }
}
