package com.surroundapps.viewpagerwithoutfragment.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.surroundapps.viewpagerwithoutfragment.R;
import com.surroundapps.viewpagerwithoutfragment.adapter.ViewPagerAdapter;
import com.surroundapps.viewpagerwithoutfragment.callback.ItemSelectInterface;
import com.surroundapps.viewpagerwithoutfragment.model.DataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://medium.com/@jerrylinjf/use-viewpager-without-fragment-906e3643fec3
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, ItemSelectInterface {

    private LinearLayout pager_indicator;
    ViewPager viewPager;
    private int dotsCount;
    ViewPagerAdapter mAdapter;
    private ImageView[] dots;
    ArrayList<ArrayList<DataModel>> listDate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        pager_indicator = findViewById(R.id.viewPagerCountDots);

        viewPager.post(new Runnable() {
            @Override
            public void run() {
                listDate = populateData();
                int height = viewPager.getHeight() / 4;
                setupViewPager(height);
            }
        });

    }


    private ArrayList<ArrayList<DataModel>> populateData() {

        String[] gridViewString = {
                "Alaram", "Android", "Mobile", "Website", "Profile", "WordPress",
                "ActionScript", "Ada", "Agora", "Alice", "Argus", "Bash",
                "Boo", "BPEL", "C", "C++", "CDuce", "Ceylon",
                "Darwin", "Draco", "DRAKON", "C--", "Dylan", "D",
                "E", "Ease", "EGL", "Epigram", "Esterel", "EXEC 2",
                "F", "FFP", "Forth", "Franz Lisp", "Factor", "Flavors",
                "G", "GDScript", "Golo", "GraphTalk", "Io", "KEE",

        };

        int[] gridViewImageId = {
                R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale,
                R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale,
                R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale,
                R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale,
                R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale,
                R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale,
                R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale, R.drawable.ui_elements_menu_icon_new_sim_sale,
        };


        ArrayList<DataModel> stringImagesList = new ArrayList<>();
        for(int i = 0; i<gridViewString.length; i++) {
            DataModel dataModel = new DataModel(gridViewString[i],gridViewImageId[i]);
            stringImagesList.add(dataModel);
        }

        ArrayList<ArrayList<DataModel>> listDate = new ArrayList<>();

        int perPageGridSize = 12;

        for (int start = 0; start < gridViewString.length; start += perPageGridSize) {
            int end = Math.min(start + perPageGridSize, gridViewString.length);
            List<DataModel> sublist = stringImagesList.subList(start, end);
            listDate.add(new ArrayList<>(sublist));
        }

        return listDate;
    }


    private void setupViewPager(int h) {
        mAdapter = new ViewPagerAdapter(MainActivity.this, listDate, h);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
        setPageViewIndicator();
    }

    private void setPageViewIndicator() {

        Log.d("###setPageViewIndicator", " : called");
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    viewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });


            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("###onPageSelected, pos ", String.valueOf(position));
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        if (position + 1 == dotsCount) {

        } else {

        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(DataModel dataModel, int position) {

        Toast.makeText(this, "text: "+dataModel.getText() + ", Position: "+position, Toast.LENGTH_SHORT).show();
    }
}
