package com.ufida.john.applearn.base;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import org.w3c.dom.ProcessingInstruction;

import java.security.KeyStore;

/**
 * Created by john on 2015/8/31.
 */
public class BaseApplication extends Application {
    private  static  String PARAM_IS_AUTO_RECOMMEND = "PARAM_IS_AUTO_LOAD_RECOMMEND";
    private static  final String PARAM_IS_NIGHT_MODE = "PARAM_IS_NIGHT_MODE";
    private static final String PARAM_PAGE_SIZE = "PARAM_PAGE_SIZE";
    private static final String PARAM_IS_BIG_FONT = "PARAM_IS_BIG_FONT";
    private static final String PARAM_IMAGE_POOL_SIZE = "PARAM_IMAGE_POOL_SIZE";
    private static final String PARAM_SCREEN_WIDTH = "PARAM_SCREEN_WIDTH";
    private static final String PARAM_SCREEN_HEIGHT = "PARAM_SCREEN_HEIGHT";
    private static final String PARAM_SCREEN_WIDTH_DP = "PARAM_SCREEN_WIDTH_DP";

    private SharedPreferences mSp;
    private int mImageCachePoolSize;
    private boolean mIsNightMode;
    private  boolean mIsImgOnlyWifi;
    private boolean mIsAutoLoadRecommend;
    private  boolean mIsBigFont;
    private  int mScreenWidth;
    private int mScreenHeight;

    private  ConfigConstant.NetworkStatus mNetWorkStatus;
    private  int mScreenWidthDP;
    private  String mFileRootDir;

    @Override
    public void onCreate() {
        super.onCreate();
        mSp = PreferenceManager.getDefaultSharedPreferences(this);
        mIsAutoLoadRecommend = mSp.getBoolean(PARAM_IS_AUTO_RECOMMEND, true);
        mIsNightMode = mSp.getBoolean(PARAM_IS_NIGHT_MODE, false);
        mPageSize = mSp.getInt(PARAM_PAGE_SIZE, 15);
        mIsImgOnlyWifi = mSp.getBoolean(PARAM_IS_IMG_ONLY_WIFI, false);
        mIsBigFont = mSp.getBoolean(PARAM_IS_BIG_FONT, false);
//        mNetWorkStatus = Utils.getcon
        mFileRootDir = Environment.isExternalStorageEmulated() ? getExternalFilesDir(null).getAbsolutePath() : getFilesDir().getAbsolutePath();
        mScreenWidth = mSp.getInt(PARAM_SCREEN_WIDTH,0);
        mScreenHeight = mSp.getInt(PARAM_SCREEN_WIDTH_DP,0);
        mScreenWidthDP = mSp.getInt(PARAM_SCREEN_WIDTH_DP,0);

        if(mImageCachePoolSize == 0)
            setImageCachePoolSize();
    }

    public boolean ismIsAutoLoadRecommend() {
        return mIsAutoLoadRecommend;
    }

    public void setmIsAutoLoadRecommend(boolean IsAutoLoadRecommend) {
        if(mIsAutoLoadRecommend == isAutoLoadRecommend)
            return;
        mIsAutoLoadRecommend = ismIsAutoLoadRecommend();
        mSp.edit().putBoolean(PARAM_IS_AUTO_RECOMMEND,mIsAutoLoadRecommend);
    }

    public boolean isBigFont() {
        return mIsBigFont;
    }

    public void setIsBigFont(boolean isBigFont) {
        if (mIsBigFont == isBigFont)
            return;

        mIsBigFont = isBigFont;
        mSp.edit().putBoolean(PARAM_IS_BIG_FONT, mIsBigFont).apply();
    }

    public boolean isNightMode() {
        return mIsNightMode;
    }

    public void setIsNightMode(boolean isNightMode) {
        if (mIsNightMode == isNightMode)
            return;

        mIsNightMode = isNightMode;
        mSp.edit().putBoolean(PARAM_IS_NIGHT_MODE, mIsNightMode).apply();
    }

    public int getPageSize() {
        return mPageSize;
    }

    public void setPageSize(int pageSize) {
        if (mPageSize == pageSize)
            return;

        mPageSize = pageSize;
        mSp.edit().putInt(PARAM_PAGE_SIZE, mPageSize).apply();
    }

    public boolean isImgOnlyWifi() {
        return mIsImgOnlyWifi;
    }

    public void setIsImgOnlyWifi(boolean isImgOnlyWifi) {
        if (mIsImgOnlyWifi == isImgOnlyWifi)
            return;

        mIsImgOnlyWifi = isImgOnlyWifi;
        mSp.edit().putBoolean(PARAM_IS_IMG_ONLY_WIFI, mIsImgOnlyWifi).apply();
    }

    public ConfigConstant.NetworkStatus getNetworkStatus() {
        return mNetWorkStatus;
    }

    public void setNetworkStatus(ConfigConstant.NetworkStatus mNetworkStatus) {
        this.mNetWorkStatus = mNetworkStatus;
    }

    public boolean isNetworkAvailable() {
        return mNetWorkStatus != ConfigConstant.NetworkStatus.DisConnect;
    }

    public boolean isNetworkWifi() {
        return mNetWorkStatus == ConfigConstant.NetworkStatus.Wifi;
    }

    public boolean canRequestImage() {
        return mNetWorkStatus == ConfigConstant.NetworkStatus.Wifi ||
                (mNetWorkStatus == ConfigConstant.NetworkStatus.Mobile && !mIsImgOnlyWifi);
    }


    public int getScreenWidthInDP() {
        return mScreenWidthDP;
    }

    public void setScreenWidthInDP(int screenWidthInDP) {
        if (mScreenWidthDP == screenWidthInDP)
            return;

        mScreenWidthDP = screenWidthInDP;
        mSp.edit().putInt(PARAM_SCREEN_WIDTH_DP, mScreenWidthDP).apply();
    }

    public String getFileRootDir() {
        return mFileRootDir;
    }

    public int getImageCachePoolSize() {
        return mImageCachePoolSize;
    }

    private void setImageCachePoolSize() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        mImageCachePoolSize = activityManager.getMemoryClass() * 1024 * 1024 / 8;
        mSp.edit().putInt(PARAM_IMAGE_POOL_SIZE, mImageCachePoolSize).apply();
    }


    public int getScreenWidth() {
        return mScreenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        if (mScreenWidth == screenWidth)
            return;

        mScreenWidth = screenWidth;
        mSp.edit().putInt(PARAM_SCREEN_WIDTH, mScreenWidth).apply();
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        if (mScreenHeight == screenHeight)
            return;

        mScreenHeight = screenHeight;
        mSp.edit().putInt(PARAM_SCREEN_HEIGHT, mScreenHeight).apply();
    }
}
