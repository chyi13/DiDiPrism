package com.hexin.plat.android.assist.floating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hexin.plat.android.assist.record.RecordManager;
import com.hexin.plat.android.assist.record.Utils;
import com.xiaojuchefu.prism.monitor.R;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FloatingMenuSubPagerAdapter extends RecyclerView.Adapter<FloatingMenuSubPagerAdapter.InnerViewHolder> {

    ArrayList<MenuItem> mTabListHead;
    HashMap<String, ArrayList<MenuItem>> mTabListData;

    Context mContext;

    FloatingMenuSubPagerAdapter(Context context, ArrayList<MenuItem> tabListHead, HashMap<String, ArrayList<MenuItem>> tabListData) {
        this.mContext = context;
        this.mTabListHead = tabListHead;
        this.mTabListData = tabListData;
    }

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder((FloatingMenuSubList) LayoutInflater.from(mContext).inflate(R.layout.assist_floating_list, parent, false));
    }

    @Override
    public void onBindViewHolder(InnerViewHolder holder, int position) {
        holder.updateListData(mTabListData.get(mTabListHead.get(position).id));
    }

    @Override
    public int getItemCount() {
        return mTabListData.size();
    }

    public static class InnerViewHolder extends RecyclerView.ViewHolder {

        FloatingMenuSubList listView;

        public InnerViewHolder(FloatingMenuSubList itemView) {
            super(itemView);
            this.listView = itemView;
            this.listView.setOnItemClickListener((menuItem) -> {
                RecordManager.getInstance().processMenuItem(menuItem);
            });
        }

        public void updateListData(ArrayList<MenuItem> list) {
            listView.updateList(list);
        }
    }
}
