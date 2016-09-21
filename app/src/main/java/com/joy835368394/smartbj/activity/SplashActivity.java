package com.joy835368394.smartbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.joy835368394.smartbj.R;
import com.joy835368394.smartbj.util.MyConstants;
import com.joy835368394.smartbj.util.SpTools;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class SplashActivity extends Activity {

    private ImageView iv_mainview;
    private AnimationSet as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        startAnimation();
        initEvent();
    }

    private void initEvent() {
        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (SpTools.getBoolean(getApplicationContext(), MyConstants.ISSETUP,false)){
                    Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void startAnimation() {
        as = new AnimationSet(false);

        AlphaAnimation aa = new AlphaAnimation(0,1);
        aa.setDuration(3000);
        aa.setFillAfter(true);
        as.addAnimation(aa);

        RotateAnimation ra = new RotateAnimation(0,360,
                            Animation.RELATIVE_TO_SELF,0.5f,
                            Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(3000);
        ra.setFillAfter(true);
        as.addAnimation(ra);

        ScaleAnimation sa = new ScaleAnimation(0,1,0,1,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(3000);
        sa.setFillAfter(true);
        as.addAnimation(sa);
        iv_mainview.startAnimation(as);
    }

    private void initView() {
        setContentView(R.layout.activity_splash);
        iv_mainview = (ImageView) findViewById(R.id.iv_splash_mainview);

    }
}
