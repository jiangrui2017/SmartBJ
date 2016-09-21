package com.joy835368394.smartbj.activity;

import android.app.Activity;
import android.os.Bundle;

import com.joy835368394.smartbj.R;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_home);
    }
}
