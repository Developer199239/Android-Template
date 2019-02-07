package com.gome.slidebar;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements Sidebar.OnSelectIndexItemListener, ComputeScrollRecycleView.OnScrollLetterListener {

    private TreeMap<String, List<String>> mContactMap;
    private LinearLayoutManager mLinearLayoutManager;
    private ComputeScrollRecycleView mComputeScrollRecycleView;
    private RecycleAdapter mRecycleAdapter;
    private LetterFlagView mLetterFlagView;
    private Sidebar mSidebar;


    private int mLastFirstVisibleItem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContactMap = Utils.getOrderContactMap();
        initViews();
    }

    private void initViews() {
        setContentView(R.layout.activity_main);
        mLetterFlagView = (LetterFlagView) findViewById(R.id.letter_flag_view);
        mSidebar = (Sidebar) findViewById(R.id.side_bar);
        mSidebar.setMap(mContactMap);
        mSidebar.setOnSelectIndexItemListener(this);
        mComputeScrollRecycleView = (ComputeScrollRecycleView) findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecycleAdapter = new RecycleAdapter(this, mContactMap);
        mComputeScrollRecycleView.setOnScrollLetterListener(this);
        mComputeScrollRecycleView.setLayoutManager(mLinearLayoutManager);
        mComputeScrollRecycleView.setAdapter(mRecycleAdapter);
        ScrollHelper scrollHelper = new ScrollHelper(mComputeScrollRecycleView,mLinearLayoutManager,mLetterFlagView);
    }

    @Override
    public void onSelectIndexItem(String index) {
        mLinearLayoutManager.scrollToPositionWithOffset(mRecycleAdapter.getPositionByKey(mContactMap, index), 0);
    }

    @Override
    public void onCurrentLetter(String tag) {
        mSidebar.updateCurrentIndexByScroll(tag);
        TextView letterFlag = mLetterFlagView.findLetterFlag(LetterFlagView.VIEW_TYPE.normal);
        if (letterFlag != null) {
            letterFlag.setText(tag);
        }
    }


    private class RecycleAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

        private TreeMap<String, List<String>> mContactMap;
        private LayoutInflater mLayoutInflater;

        public RecycleAdapter(Context context, TreeMap<String, List<String>> contactMap) {
            mLayoutInflater = LayoutInflater.from(context);
            mContactMap = contactMap;
        }


        @Override
        public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View convertView = mLayoutInflater.inflate(R.layout.item_view, parent, false);
            return new RecycleViewHolder(convertView);
        }

        @Override
        public void onBindViewHolder(RecycleViewHolder holder, int position) {
            if (mContactMap == null) {
                return;
            }
            String letter = getKey(position);
            holder.itemView.setTag(letter);
            holder.mLetter.setText(letter);
            List<String> list = mContactMap.get(letter);
            bindContainerView(list, holder.mContainer);
        }

        @Override
        public int getItemCount() {
            return mContactMap == null ? -1 : mContactMap.size();
        }

        public int getPositionByKey(Map<String, List<String>> map, String chooseKey) {
            if (map == null || chooseKey == null) {
                return -1;
            }
            int sign = 0;
            for (String key : map.keySet()) {
                if (key.equals(chooseKey)) {
                    return sign;
                }
                sign++;
            }
            return -1;
        }

        private String getKey(int index) {
            if (mContactMap == null) {
                return null;
            }
            int sign = 0;
            for (String key : mContactMap.keySet()) {
                if (sign == index) {
                    return key;
                }
                sign++;
            }
            return null;
        }

        private void bindContainerView(List<String> list, ViewGroup parent) {
            parent.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                LinearLayout linearLayout = (LinearLayout) mLayoutInflater.
                        inflate(R.layout.container_item_view, parent, false);
                TextView textView = (TextView) linearLayout.findViewById(R.id.container_item_view);
                View baseLine = linearLayout.findViewById(R.id.base_line);
                baseLine.setVisibility(i == list.size() - 1 ? View.INVISIBLE : View.VISIBLE);
                textView.setText(list.get(i));
                parent.addView(linearLayout);
            }
        }
    }


    private class RecycleViewHolder extends RecyclerView.ViewHolder {

        private TextView mLetter;
        private LinearLayout mContainer;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            mLetter = (TextView) itemView.findViewById(R.id.letter);
            mContainer = (LinearLayout) itemView.findViewById(R.id.container);
        }
    }
}
