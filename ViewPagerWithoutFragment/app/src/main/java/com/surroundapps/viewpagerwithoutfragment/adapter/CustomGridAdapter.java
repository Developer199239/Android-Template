package com.surroundapps.viewpagerwithoutfragment.adapter;

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

import com.surroundapps.viewpagerwithoutfragment.R;
import com.surroundapps.viewpagerwithoutfragment.callback.ItemSelectInterface;
import com.surroundapps.viewpagerwithoutfragment.model.DataModel;

import java.util.ArrayList;

public class CustomGridAdapter extends BaseAdapter {

    private Context mContext;
    private final int rowHeight;
    private ArrayList<DataModel> dataList;


    public CustomGridAdapter(Context context, ArrayList<DataModel> data, int rowHeight) {
        mContext = context;
        this.dataList = data;
        this.rowHeight = rowHeight;
    }


    @Override
    public int getCount() {
        return dataList.size();
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
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final DataModel dataModel = dataList.get(position);
        viewHolder.textView.setText(dataModel.getText());
        viewHolder.imageView.setImageResource(dataModel.getImage());
        LinearLayout parentLayout = convertView.findViewById(R.id.parentLayout);
        parentLayout.setLayoutParams(new LinearLayout.LayoutParams(GridView.AUTO_FIT, rowHeight));

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemSelectInterface itemSelectInterface = (ItemSelectInterface) mContext;
                itemSelectInterface.onClick(dataModel,position);
            }
        });

    return convertView;
    }
}
