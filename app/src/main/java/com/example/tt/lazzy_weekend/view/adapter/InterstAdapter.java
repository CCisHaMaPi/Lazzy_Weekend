package com.example.tt.lazzy_weekend.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import com.example.tt.lazzy_weekend.R;
import com.example.tt.lazzy_weekend.utils.SharedPreferencesTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2017/1/17.
 */

public class InterstAdapter extends BaseAdapter {

    private List<Interesting> mlist;
    private LayoutInflater myInflater;
    private Context context;

    public InterstAdapter(Context context) {
        this.context = context;

        myInflater = LayoutInflater.from(context);
        initData();
    }

    private void initData() {
        mlist = new ArrayList<>();
        mlist.add(new Interesting(R.drawable.interest_nearby_selector, "周边游"));
        mlist.add(new Interesting(R.drawable.interest_bar_selector, "酒吧"));
        mlist.add(new Interesting(R.drawable.interest_music_selector, "音乐"));
        mlist.add(new Interesting(R.drawable.interest_drama_selector, "戏剧"));
        mlist.add(new Interesting(R.drawable.interest_exhibition_selector, "展览"));
        mlist.add(new Interesting(R.drawable.interest_food_selector, "美食"));
        mlist.add(new Interesting(R.drawable.interest_shop_selector, "购物"));
        mlist.add(new Interesting(R.drawable.interest_film_selector, "电影"));
        mlist.add(new Interesting(R.drawable.interest_party_selector, "聚会"));
        mlist.add(new Interesting(R.drawable.interest_sport_selector, "运动"));
        mlist.add(new Interesting(R.drawable.interest_kind_selector, "公益"));
        mlist.add(new Interesting(R.drawable.interest_business_selector, "商业"));
    }

    @Override
    public int getCount() {
        return mlist == null ? 0 : mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        if(null == view){
            view = myInflater.inflate(R.layout.item_interest_grid, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.checkHead = (CheckBox) view.findViewById(R.id.checkbox_image);
            viewHolder.checkfoot = (CheckBox)view.findViewById(R.id.checkbox_text);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Interesting interesting = mlist.get(position);
        //设置文字和图片
        viewHolder.checkHead.setButtonDrawable(interesting.getResId());
        viewHolder.checkfoot.setText(interesting.getTitle());

        //关联二者,实现联动
        viewHolder.checkHead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewHolder.checkfoot.setChecked(isChecked);
                SharedPreferencesTools.putBoolean(context,"interestIsCheck",
                        viewHolder.checkfoot.isChecked());

            }
        });
        viewHolder.checkfoot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                viewHolder.checkHead.setChecked(isChecked);
                SharedPreferencesTools.putBoolean(context,"interestIsCheck",
                        viewHolder.checkfoot.isChecked());
            }
        });
        return view;
    }

    public class ViewHolder{
        public CheckBox checkHead;
        public CheckBox checkfoot;
    }

    class Interesting{
        private int resId;
        private String title;

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Interesting(int resId, String title) {
            this.resId = resId;
            this.title = title;
        }

        public Interesting() {
        }

        @Override
        public String toString() {
            return "Interesting{" +
                    "resId=" + resId +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
