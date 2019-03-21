package com.surroundapps.viewpagerwithoutfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomGridAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] gridViewString;
    private final int[] gridViewImageId;
    private final int rowHeight;


    public CustomGridAdapter(Context context, String[] gridViewString, int[] gridViewImageId, int rowHeight) {
        mContext = context;
        this.gridViewImageId = gridViewImageId;
        this.gridViewString = gridViewString;
        this.rowHeight = rowHeight;
    }


    @Override
    public int getCount() {
        return gridViewString.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View view) {
            textView = view.findViewById(R.id.android_gridview_text);
            imageView = view.findViewById(R.id.android_gridview_image);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gridview_layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            /*TextView textViewAndroid = (TextView) convertView.findViewById(R.id.android_gridview_text);
            ImageView imageViewAndroid = (ImageView) convertView.findViewById(R.id.android_gridview_image);
            textViewAndroid.setText(gridViewString[position]);
            imageViewAndroid.setImageResource(gridViewImageId[position]);
            LinearLayout parentLayout = (LinearLayout) convertView.findViewById(R.id.parentLayout);
            parentLayout.setLayoutParams(new LinearLayout.LayoutParams(GridView.AUTO_FIT, rowHeight));*/
        } else {
//            gridViewAndroid = (View) convertView;
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText(gridViewString[position]);
        viewHolder.imageView.setImageResource(gridViewImageId[position]);
        LinearLayout parentLayout = (LinearLayout) convertView.findViewById(R.id.parentLayout);
        parentLayout.setLayoutParams(new LinearLayout.LayoutParams(GridView.AUTO_FIT, rowHeight));

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "GridView Item: " + gridViewString[position], Toast.LENGTH_SHORT).show();
            }
        });

    return convertView;
    }
}
