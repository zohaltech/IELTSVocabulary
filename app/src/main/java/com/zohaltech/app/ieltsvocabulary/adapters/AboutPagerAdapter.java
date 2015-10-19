package com.zohaltech.app.ieltsvocabulary.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zohaltech.app.ieltsvocabulary.fragments.AboutFragment;
import com.zohaltech.app.ieltsvocabulary.fragments.AuthorFragment;

public class AboutPagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles = new String[]{"About", "Author"};
    private int vocabId;

    public AboutPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return AboutFragment.newInstance();
        else
            return AuthorFragment.newInstance();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
