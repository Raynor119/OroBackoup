package com.pixels.orobackoup.View.Estadistica.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pixels.orobackoup.View.Estadistica.TabLayout.Fragment.FragmentAnual;
import com.pixels.orobackoup.View.Estadistica.TabLayout.Fragment.FragmentDiarias;
import com.pixels.orobackoup.View.Estadistica.TabLayout.Fragment.FragmentMes;

public class PagerController extends FragmentPagerAdapter {
    private int numeroTab;
    public PagerController(FragmentManager fm, int behavior) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numeroTab=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentDiarias();
            case 1:
                return new FragmentMes();
            case 2:
                return new FragmentAnual();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "DIARIAS";
            case 1:
                return "MENSUALES";
            case 2:
                return "ANUALES";
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return numeroTab;
    }
}
