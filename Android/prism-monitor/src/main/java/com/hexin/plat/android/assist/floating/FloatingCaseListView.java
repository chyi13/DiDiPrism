package com.hexin.plat.android.assist.floating;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hexin.plat.android.assist.model.CaseBean;
import com.hexin.plat.android.assist.record.RecordManager;
import com.hexin.plat.android.assist.record.Utils;
import com.xiaojuchefu.prism.monitor.R;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class FloatingCaseListView extends LinearLayout implements AdapterView.OnItemClickListener {
    public FloatingCaseListView(Context context) {
        super(context);
    }

    public FloatingCaseListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingCaseListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FloatingCaseListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private ListView caseListView;
    private ArrayList<CaseBean> caseListData = new ArrayList<>();

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        caseListView = findViewById(R.id.case_list);
        caseListView.setAdapter(mListAdapter);
        caseListView.setOnItemClickListener(this);
    }

    public static FloatingCaseListView create(Context context) {
        return (FloatingCaseListView) LayoutInflater.from(context).inflate(R.layout.assist_floating_case_list, null);
    }

    public void setCaseList(ArrayList<CaseBean> caseList) {
        this.caseListData = caseList;
        this.mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    BaseAdapter mListAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return caseListData.size();
        }

        @Override
        public Object getItem(int position) {
            return caseListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.assist_floating_case_list_item, null);
                viewHolder.id = convertView.findViewById(R.id.id);
                viewHolder.name = convertView.findViewById(R.id.name);
                viewHolder.description = convertView.findViewById(R.id.description);
                viewHolder.run = convertView.findViewById(R.id.run);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.id.setText(caseListData.get(position).getId());
            viewHolder.name.setText(caseListData.get(position).getName());
            viewHolder.description.setText(caseListData.get(position).getDescription());
            viewHolder.run.setOnClickListener((view) -> {
                RecordManager.getInstance().playback(Utils.convertActionBeanToEventData(caseListData.get(position).getActions()));
            });
            return convertView;
        }
    };

    static class ViewHolder {
        TextView id;
        TextView name;
        TextView description;
        Button run;
        Button history;
    }
}
