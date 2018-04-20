package com.example.iitdost.HomeScreen;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iitdost.R;

/**
 * Created by ankurshaswat on 14/3/18.
 * Fragment to be used to display requests history which can be opened by using bottom navigation bar.
 */

    public class RequestsFragment extends Fragment {

        public RequestsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_requests_main, parent, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
        }
    }

