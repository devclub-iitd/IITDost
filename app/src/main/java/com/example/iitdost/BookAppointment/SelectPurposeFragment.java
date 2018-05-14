package com.example.iitdost.BookAppointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.iitdost.R;

public class SelectPurposeFragment extends Fragment {

    private RadioGroup purposeRadio;

    public SelectPurposeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.framgent_select_purpose, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        purposeRadio = view.findViewById(R.id.purposeRadio);

        purposeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb=radioGroup.findViewById(i);

                String purpose=rb.getText().toString();

                ((BookAppointmentActivity)getActivity()).changeState(BookAppointmentActivity.State.BOOK,purpose);
            }
        });
    }
}
