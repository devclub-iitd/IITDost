package com.example.iitdost.BookAppointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iitdost.R;

public class ConfirmationFragment extends Fragment{

    public ConfirmationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking_confirmation, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }



    public void setVals(String department, String faculty, String date, String time, String purpose) {
    }
}
