package com.example.digital.daveloo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    int numberOfTabs;
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.numberOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Noticias tab1 = new Noticias();
                return tab1;
            case 1:
                Telefonos tab2 = new Telefonos();
                return tab2;
            case 2:
                Contacto tab3 = new Contacto();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}