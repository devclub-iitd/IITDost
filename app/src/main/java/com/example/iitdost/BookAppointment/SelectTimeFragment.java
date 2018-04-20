package com.example.iitdost.BookAppointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.iitdost.R;

public class SelectTimeFragment extends Fragment {

    LinearLayout timeSelect;
    View prevSelected;
    String faculty,date;
    public SelectTimeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_time, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        timeSelect=view.findViewById(R.id.timeList);

        populateTimeList();

    }

    private void populateTimeList() {
        for(int i=0;i<8;i++){

            View v=View.inflate(getContext(), R.layout.template_date_select, null);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(prevSelected==null){
                        prevSelected=view;
                    }else{
                        prevSelected.setSelected(false);
                        prevSelected=view;
                    }
                    view.setSelected(true);

                    ((BookAppointmentActivity)getActivity()).changeState(BookAppointmentActivity.State.SELECT_PURPOSE,"TIME passed here");
                }
            });

            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginEnd(10);
            layoutParams.setMarginStart(10);
            v.setLayoutParams(layoutParams);

            timeSelect.addView(v);
        }
    }

    public void setVals(String faculty, String date) {
    this.faculty=faculty;
    this.date=date;
    }
}
