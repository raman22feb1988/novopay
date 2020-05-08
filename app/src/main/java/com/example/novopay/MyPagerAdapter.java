package com.example.novopay;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    int NUM_ITEMS = 5;

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        return TabContent.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Bitcoin";
            case 1:
                return "US\nBusiness";
            case 2:
                return "Apple";
            case 3:
                return "Tech\nCrunch";
            case 4:
                return "Wall St.\nJournal";
            default:
                return null;
        }
    }
}