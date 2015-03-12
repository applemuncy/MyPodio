package com.just4me.apple.testing;

/**
 * Created by apple on 3/12/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentSplash extends Fragment {
    private String TAG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TAG = getClass().toString();
        Log.d(TAG, "onCreateView");
        try {
            return inflater.inflate(R.layout.fragment_splash, container, false);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return container;
    }
}