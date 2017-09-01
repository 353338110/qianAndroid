package com.qian.utils.fragmentUtil;


import android.support.v4.app.Fragment;

/**
 * Created by SHCai on 2017/8/24.
 */

public abstract class BackHandledFragment extends Fragment implements FragmentBackHandler{
    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }
}
