package com.example.iitdost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Created by Shashwat Shivam on 25-3-18.
 */

public class WhomToMeetFragment extends Fragment {

    String department;

    public WhomToMeetFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle
            savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_whom_to_meet, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here

        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        RadioGroup selProf = view.findViewById(R.id.selProfRadioGrp);


        selProf.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ((BookAppointmentActivity) getActivity()).launchSelDate("faculty name");
            }
        });
    }


    void setDepartment(String department) {
        this.department = department;
    }
}
