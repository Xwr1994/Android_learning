package com.baidu.xuwanran.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by xuwanran on 2018/8/7.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
