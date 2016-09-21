package com.joy835368394.smartbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.joy835368394.smartbj.R;
import com.joy835368394.smartbj.util.DensityUtil;
import com.joy835368394.smartbj.util.MyConstants;
import com.joy835368394.smartbj.util.SpTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class GuideActivity extends Activity {

    private ViewPager vp_pager;
    private List<ImageView> guides;
    private LinearLayout ll_points;
    private Button bt_startexp;
    private float dispoint;
    private View v_redpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        ll_points.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                dispoint = (ll_points.getChildAt(1).getLeft() - ll_points.getChildAt(0).getLeft());
            }
        });
        bt_startexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpTools.setBoolean(GuideActivity.this,MyConstants.ISSETUP,true);
                Intent intent = new Intent(GuideActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        vp_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float leftMargin = dispoint * ( position + positionOffset );
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v_redpoint.getLayoutParams();
                params.leftMargin = Math.round(leftMargin);
                v_redpoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position ==guides.size()-1){
                    bt_startexp.setVisibility(View.VISIBLE);
                }else {
                    bt_startexp.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        int [] images = new int[]{R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};
        guides = new ArrayList<ImageView>();
        for (int i = 0; i < images.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            guides.add(imageView);
            View view = new View(this);
            view.setBackgroundResource(R.drawable.gray_point);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DensityUtil.dip2px(getApplicationContext(),10),DensityUtil.dip2px(getApplicationContext(),10));
            if (i != 0) {
                params.leftMargin = 10;
            }
            view.setLayoutParams(params);
            ll_points.addView(view);
        }
        bt_startexp.setVisibility(View.GONE);
        MyAdapter adapter = new MyAdapter();
        vp_pager.setAdapter(adapter);
    }

    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return guides.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = guides.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView imageView = guides.get(position);
            container.removeView(imageView);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_guide);
        vp_pager = (ViewPager) findViewById(R.id.vp_guide_pager);
        ll_points = (LinearLayout) findViewById(R.id.ll_guide_points);
        bt_startexp = (Button) findViewById(R.id.bt_guide_startexp);
        v_redpoint = findViewById(R.id.v_guide_redpoint);
    }
}
