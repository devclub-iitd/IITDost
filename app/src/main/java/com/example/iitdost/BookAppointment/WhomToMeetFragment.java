package com.example.iitdost.BookAppointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.iitdost.R;

/**
 * Created by Shashwat Shivam on 25-3-18.
 */

public class WhomToMeetFragment extends Fragment {

    String department;
    RadioGroup typeOfStaffRadioGrp;
    RadioGroup staffDisplayList;

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

        initializeView(view);
    }


    void setDepartment(String department) {
        this.department = department;
    }

    private void initializeView(View view) {
        typeOfStaffRadioGrp = view.findViewById(R.id.selProfRadioGrp);
        staffDisplayList = view.findViewById(R.id.selectStaff);


        typeOfStaffRadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
//TODO : optimize this based on state (Not refill if already in correct state (Depending on previous state)

                    case R.id.facultyRadio:
                        staffDisplayList.removeAllViews();
//                        TODO: addChildViews(array,staffDisplayList);
                        break;
                    case R.id.otherStaffRadio:
                        staffDisplayList.removeAllViews();
//                        TODO: addChildViews(array,staffDisplayList);
                        break;
                    default:
                        break;
                }

            }
        });


    }

}
