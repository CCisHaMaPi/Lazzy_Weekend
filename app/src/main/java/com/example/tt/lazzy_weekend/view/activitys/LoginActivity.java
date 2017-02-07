package com.example.tt.lazzy_weekend.view.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.tt.lazzy_weekend.R;
import com.example.tt.lazzy_weekend.utils.MyPageTransformer;
import com.example.tt.lazzy_weekend.utils.ViewPagerScroller;

import java.util.ArrayList;

/**
 * Created by my on 2017/1/13.
 */

public class LoginActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ArrayList<ImageView> imageList;
    private MyPageTransformer transformer;
    private int mCurrentPage = 0;   //轮播页码
    private long firstTime=0;   //用户点击返回键的时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    public void onClickLogin(View view) {
        startActivity(new Intent(this, UserInfoActivity.class));
    }

    private void initView() {
        imageList = new ArrayList<>();
        initDate(R.mipmap.pic1);
        initDate(R.mipmap.pic2);
        initDate(R.mipmap.pic3);
        initDate(R.mipmap.pic4);
        initDate(R.mipmap.pic1);

        final ViewPager pager = (ViewPager) findViewById(R.id.login_viewpager);
        transformer = new MyPageTransformer();
        transformer.setCurrentItem(this, 0, imageList);

        //添加页面切换动画效果实现淡入淡出,以及适配器与滚动器
        pager.setPageTransformer(true, transformer);
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageList == null ? 0: imageList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(imageList.get(position));
                return imageList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageList.get(position));
            }
        });

        ViewPagerScroller scroller = new ViewPagerScroller(this);
        scroller.setScrollDuration(2000);
        scroller.initViewPagerScroll(pager);

        //利用handlers实现自动轮播
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCurrentPage == imageList.size()-1){
                    mCurrentPage = 0;
                    pager.setCurrentItem(mCurrentPage, false);
                    handler.postDelayed(this,0);
                }else {
                    mCurrentPage ++;
                    pager.setCurrentItem(mCurrentPage);
                    handler.postDelayed(this,2000);
                }

            }
        }, 2000);
    }


    private void initDate(int mipmapID){
        ImageView image = new ImageView(this);
        image.setImageResource(mipmapID);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageList.add(image);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * splash页面自动销毁,该界面处于栈底,
     * 为了防止用户误点返回直接结束程序.
     * 对用户进行友好提醒,并实现用户在两秒内连续双击返回键退出程序.
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            //用户首次点击,或者间隔两秒后点击
            if (System.currentTimeMillis()-firstTime>2000){
                showToast("再点一次,离开懒人周末~");
                firstTime=System.currentTimeMillis();
            }else {
                //双击结束页面
                removeActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
