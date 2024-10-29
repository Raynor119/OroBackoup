package com.pixels.orobackoup.View.Estadistica.TabLayout.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pixels.orobackoup.R;

public class FragmentPrueba  extends Fragment {
    public FragmentPrueba(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_merma, container, false);
        return rootView;
    }
}
