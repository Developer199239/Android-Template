package com.surroundapps.thumimageloading;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private static final String TAG = ImageAdapter.class.getName();
    private ArrayList<String> filteredData;
    protected ImageLoader imageLoader;
    Context context;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView thumImage;
        Context context;

        MyViewHolder(View view) {
            super(view);
            thumImage = view.findViewById(R.id.image);
        }
    }

    public ImageAdapter(Context con) {
        this.context = con;
        this.filteredData = new ArrayList<>();
    }

    @NonNull
    @Override
    public ImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ImageAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.MyViewHolder holder, int position) {
        holder.thumImage.setImageResource(R.mipmap.ic_launcher);

        if (holder.thumImage instanceof RoundedImageView) {
            ((RoundedImageView) holder.thumImage).setCorners(
                    R.dimen.message_bubble_corners_radius,
                    R.dimen.message_bubble_corners_radius,
                    R.dimen.message_bubble_corners_radius,
                    0
            );
        }

//                String url = "https://cdn.pixabay.com/photo/2017/12/25/17/48/waters-3038803_1280.jpg";
        String url = "http://103.78.248.70:5443/vdo.sensor.buzz/1cdfbaabc20ea425f56a41f26d3064a32045c513/soIQxzMV5VEPK5gJZ8Tf4W6sZIBUNnW59AOXjleO/6553077554520776705.jpeg";

        Picasso.with(context).load(url).into(holder.thumImage);

//        try{
//            imageLoader.loadImage(holder.thumImage, url);
//        }catch (Exception e) {
//            Log.d("",e.getMessage());
//        }

    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }


    public void addAll(ArrayList<String> data) {
        this.filteredData = data;
        notifyDataSetChanged();
    }

}
