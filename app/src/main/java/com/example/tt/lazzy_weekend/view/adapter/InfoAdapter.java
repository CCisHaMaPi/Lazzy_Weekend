package com.example.tt.lazzy_weekend.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.tt.lazzy_weekend.R;
import com.example.tt.lazzy_weekend.model.bean.AppendInfo;
import com.example.tt.lazzy_weekend.utils.ImageLoader;

import java.util.List;

/**
 * Created by my on 2017/1/18.
 */

public class InfoAdapter extends BaseAdapter {

    private Context context;
    private List<AppendInfo.ResultBean> result;
    private LayoutInflater inflater;

    public InfoAdapter(Context context, List<AppendInfo.ResultBean> result) {
        this.context = context;
        this.result = result;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return result == null ? 0 : result.size();
    }

    @Override
    public Object getItem(int position) {
        return result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        if (null == view) {
            view = inflater.inflate(R.layout.item_frag_info, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) view.findViewById(R.id.info_image);
            holder.title = (TextView) view.findViewById(R.id.info_title);
            holder.name = (TextView) view.findViewById(R.id.info_name);
            holder.time = (TextView) view.findViewById(R.id.info_time);
            holder.number = (TextView) view.findViewById(R.id.info_number);
            holder.price = (TextView) view.findViewById(R.id.info_price);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
            holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.image.setImageResource(R.mipmap.def_loading);

            holder.title.setText("");
            holder.name.setText("");
            holder.time.setText("");
            holder.number.setText("");
            holder.price.setText("");
        }


        final AppendInfo.ResultBean bean = result.get(position);
        holder.title.setText(bean.getTitle());
        holder.name.setText(bean.getPoi() + " · " + bean.getCategory());
        holder.time.setText(bean.getTime_info());
        holder.number.setText(String.valueOf(bean.getCollected_num()) + "人收藏");
        holder.price.setText("¥" +bean.getPrice_info());

        String imageURL = bean.getFront_cover_image_list().get(0);
        holder.image.setTag(imageURL);
        ImageLoader.loadImage(imageURL, new ImageLoader.OnImageLoaderListener() {
            @Override
            public void onImageLoad(Bitmap bitmap, String url) {
                if(url.equals(holder.image.getTag())){
                    holder.image.setImageBitmap(bitmap);
                    holder.image.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });
        return view;
    }

    class ViewHolder{
        ImageView image;
        TextView title,name,time,number,price;
    }
}
