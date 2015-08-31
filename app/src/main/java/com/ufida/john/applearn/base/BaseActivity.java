package com.ufida.john.applearn.base;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.ufida.john.applearn.R;

import java.util.Date;

/**
 * Created by john on 2015/8/31.
 */
public class BaseActivity extends AppCompatActivity {

    private BaseApplication mBaseApp = null;
    private WindowManager mWindowManager = null;
    private View mNightView = null;
    private LayoutParams mNightViewParams;
    private ActionBar mActionBar;

    private boolean mIsAddedView;
    private int mPreviousDeltaY =-1;
    private  boolean mIsActionbarHide;
    private  long mLastChangeTime;
    private BroadcastReceiver mNetworkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mBaseApp = (BaseApplication) getApplication();

        if(mBaseApp.isNightMode())
            setTheme(R.style.AppTheme_night);
        else
            setTheme(R.style.AppTheme_day);

        super.onCreate(savedInstanceState);

        mActionBar = getSupportActionBar();
        mIsAddedView =false;
        mLastChangeTime = new Date().getTime();

        if(mBaseApp.isNightMode()){

        }

        mNetworkChangeReceiver = new NetWorkChangeReceiver(getApp());
        //http://git.oschina.net/yso/CNBlogs/blob/master/app/src/main/java/zhexian/learn/cnblogs/base/BaseActivity.java?dir=0&filepath=app%2Fsrc%2Fmain%2Fjava%2Fzhexian%2Flearn%2Fcnblogs%2Fbase%2FBaseActivity.java&oid=0b4e23918a07704ea565f1faa36fe94326bc3aae&sha=f5fd10f8f3fe275b47a8b3b8ffe10544790ce718
    }
}
