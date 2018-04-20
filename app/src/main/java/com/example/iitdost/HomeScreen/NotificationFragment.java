package com.example.iitdost.HomeScreen;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iitdost.R;

/**
 * Created by ankurshaswat on 14/3/18.
 * Fragment to be used to show notification list.
 */

public class NotificationFragment extends Fragment {

    public NotificationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications_main, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }
}

