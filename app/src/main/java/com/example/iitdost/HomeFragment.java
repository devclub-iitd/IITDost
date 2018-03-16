package com.example.iitdost;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ankurshaswat on 14/3/18.
 * Fragment to show main options on home screen.
 */

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_home_main, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
         Button bookAppointment= view.findViewById(R.id.bookAppointmentButton);
         bookAppointment.setOnClickListener(mListener);
    }


    //OnClickListener for all the buttons on this fragment (4 exist)
    private View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bookAppointmentButton:
                    Intent toBookAppointment = new Intent(getActivity(), BookAppointmentActivity.class);
                    startActivity(toBookAppointment);
                    // do stuff
                    break;
                case R.id.button1:
                    // do stuff
                    break;
                case R.id.button3:
                    // do stuff
                    break;
            }
        }
    };
}

