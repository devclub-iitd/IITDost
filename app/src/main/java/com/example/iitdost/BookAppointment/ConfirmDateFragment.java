package com.example.iitdost.BookAppointment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.iitdost.HomeScreen.MainActivity;
import com.example.iitdost.R;

public class ConfirmDateFragment extends Fragment {

    private ConfirmDateFragment confirmDateFragment=this;


    public ConfirmDateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirm_date, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button proceed=view.findViewById(R.id.btnProceed);
        Button cancel=view.findViewById(R.id.btnCancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                confirmDateFragment.getActivity().finish();
            }
        });


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BookAppointmentActivity) getActivity()).changeState(BookAppointmentActivity.State.SELECT_TIME,"");
            }
        });
    }
}
