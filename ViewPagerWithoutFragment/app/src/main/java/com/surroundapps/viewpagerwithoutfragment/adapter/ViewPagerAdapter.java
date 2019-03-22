package com.surroundapps.viewpagerwithoutfragment.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.surroundapps.viewpagerwithoutfragment.R;
import com.surroundapps.viewpagerwithoutfragment.adapter.CustomGridAdapter;
import com.surroundapps.viewpagerwithoutfragment.model.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murtuza Rahman on 3/21/19.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<ArrayList<DataModel>> mListData;
    GridView gridView;
    int height;

    public ViewPagerAdapter(Context context, ArrayList<ArrayList<DataModel>> listDate, int h) {
        mContext = context;
        mListData = listDate;
        height = h;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.view_item, container, false);

        gridView = view.findViewById(R.id.gridView);

        ArrayList<DataModel> data = mListData.get(position);

        CustomGridAdapter adapterViewAndroid = new CustomGridAdapter(mContext, data,height);
        gridView.setAdapter(adapterViewAndroid);
        gridView.deferNotifyDataSetChanged();


        container.addView(view);
        return view;
    }
}

