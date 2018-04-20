package com.example.iitdost.BookAppointment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.iitdost.HomeScreen.MainActivity;
import com.example.iitdost.R;

public class ConfirmationFragment extends Fragment{

    Button cancel;
    ImageButton book;
    ConfirmationFragment confirmationFragment=this;


    public ConfirmationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking_confirmation, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        cancel = view.findViewById(R.id.btnCancel);
        book = view.findViewById(R.id.btnConfirm);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Cancel Sent Request
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                confirmationFragment.getActivity().finish();
            }
        });


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                confirmationFragment.getActivity().finish();
            }
        });
    }


    public void setVals(String department, String faculty, String date, String time, String purpose) {
    }
}
