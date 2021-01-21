package com.hexin.plat.android.assist.floating;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.eastwood.common.adapter.QuickAdapter;
import com.eastwood.common.adapter.ViewHelper;
import com.hexin.plat.android.assist.callback.OnFloatingViewMenuListClickCallback;
import com.xiaojuchefu.prism.monitor.R;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class FloatingMenuSubList extends LinearLayout {

    ArrayList<MenuItem> mList;

    QuickAdapter<MenuItem> mAdapter;

    ListView mListView;

    public FloatingMenuSubList(Context context) {
        super(context);
    }

    public FloatingMenuSubList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingMenuSubList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FloatingMenuSubList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mListView = findViewById(R.id.list);
        mAdapter = new QuickAdapter<MenuItem>(getContext(), R.layout.assist_floating_submenu_item, mList) {
            @Override
            protected void convert(int position, ViewHelper helper, MenuItem item) {
                helper.setText(R.id.tv_text_playback_event, item.name);
//                helper.setText(R.id.tv_playback_content_text, item.icon);
            }
        };
        mListView.setAdapter(mAdapter);
    }

    public void setOnItemClickListener(OnFloatingViewMenuListClickCallback callback) {
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            callback.onClicked(this.mList.get(position));
        });
    }

    public void updateList(ArrayList<MenuItem> list) {
        this.mList = list;
        this.mAdapter.replaceAll(list);
        this.mAdapter.notifyDataSetChanged();
    }
}
