package com.ufida.john.applearn.main;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.ListView;

import com.ufida.john.applearn.base.BaseActivity;

/**
 * Created by john on 2015/8/31.
 */
public class NavigatorFragment  extends Fragment implements View.OnClickListener {
    private  static  final  String PARAM_IS_USER_LEARNED_NAVIGATOR ="PARAM_IS_USER_LEARNED_NAVIGATOR";

    private static  final  String PARAM_NAVIGATOR_SELECTED_POSITION = "PARAM_NAVIGATOR_SELECTED_POSITION";

    private BaseActivity mBaseActivity;
    private View mSettingBtn;
    private  View mFavoriteBtn;
    private ListView mNavigatorItemList;
    private ActionBarDrawerToggle mDrawerToggle;
    private INavigatorCallback mINavigatorCallBack;

    private int mCurrentSelectedPosition = 0;
    private boolean mIsUserLearnedNavigator = false;
    private boolean mIsFromSavedInstance = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mBaseActivity = activity;
    }
}
