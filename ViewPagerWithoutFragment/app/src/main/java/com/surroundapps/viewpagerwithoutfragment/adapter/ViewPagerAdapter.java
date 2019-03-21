package com.surroundapps.viewpagerwithoutfragment.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.surroundapps.viewpagerwithoutfragment.R;
import com.surroundapps.viewpagerwithoutfragment.adapter.CustomGridAdapter;

import java.util.List;

/**
 * Created by Murtuza Rahman on 3/21/19.
 */
public class ViewPagerAdapter extends PagerAdapter {

    String[] gridViewString = {
            "Alram", "Android", "Mobile", "Website", "Profile", "WordPress",
            "Alram", "Android", "Mobile", "Website", "Profile", "WordPress",
            "Alram", "Android", "Mobile", "Website", "Profile", "WordPress",

    } ;

    int[] gridViewImageId = {
            R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale,
            R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale,
            R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale,

    };


    private Context mContext;
    private List<String> mListData;
    GridView androidGridView;
    int height;

    public ViewPagerAdapter(Context context, List<String> listDate, int h) {
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
//        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.view_item1, container, false);
//
//        final TextView textView = view.findViewById(R.id.textView);
//
//        Button button = view.findViewById(R.id.button);
//        button.setText(mListData.get(position));
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                textView.setText(mListData.get(position));
//            }
//        });


        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.view_item, container, false);

        androidGridView = view.findViewById(R.id.grid_view_image_text);
//        androidGridView.post(new Runnable(){
//            public void run(){
//                int height = androidGridView.getHeight()/3;
//                final CustomGridAdapter adapterViewAndroid = new CustomGridAdapter(mContext, gridViewString, gridViewImageId,height);
//                androidGridView.setAdapter(adapterViewAndroid);
//                notifyDataSetChanged();
//            }
//        });

        CustomGridAdapter adapterViewAndroid = new CustomGridAdapter(mContext, gridViewString, gridViewImageId,height);
        androidGridView.setAdapter(adapterViewAndroid);


        container.addView(view);
        return view;
    }
}

