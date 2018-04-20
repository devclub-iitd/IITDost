package com.example.iitdost.BookAppointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_whom_to_meet, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
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
                        addFaculty();
                        //TODO: addChildViews(array,staffDisplayList);
                        break;
                    case R.id.otherStaffRadio:
                        staffDisplayList.removeAllViews();
                        addFaculty();
                        //TODO: addChildViews(array,staffDisplayList);
                        break;
                    default:
                        break;
                }

            }
        });

        typeOfStaffRadioGrp.check(R.id.facultyRadio);


        staffDisplayList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                RadioButton rb = (RadioButton) radioGroup.getChildAt(i);

                ((BookAppointmentActivity) getActivity()).changeState(BookAppointmentActivity.State.SELECT_DATE, rb.getText().toString());
            }
        });



    }


    public void addFaculty() {

        for (int i = 0; i < 10; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(i);
            radioButton.setText("Proffff");
            staffDisplayList.addView(radioButton);
        }
    }

}
