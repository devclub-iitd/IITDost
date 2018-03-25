package com.example.iitdost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

public class BookAppointmentActivity extends AppCompatActivity {


    SelectDepartmentFragment mSelDep;
    WhomToMeetFragment mSelProf;
    DateAppointmentFragment mSelDate;
    FrameLayout frame1, frame2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        Toolbar toolbar = findViewById(R.id.toolbarBookAppointment);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setHomeAsUpIndicator(R.drawable.);

        frame1 = findViewById(R.id.book_appointment_frag_space);
        frame2 = findViewById(R.id.book_appointment_frag_space_sel_date);

        launchSelDep();
    }



    void launchSelDep(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mSelDep==null){
            mSelDep=new SelectDepartmentFragment();
        }
        ft.replace(R.id.book_appointment_frag_space, mSelDep, "selDep").addToBackStack("selDep").commit();
        frame2.removeAllViews();
    }

    void launchSelProf(String department){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mSelProf==null){
            mSelProf=new WhomToMeetFragment();
        }
        mSelProf.setDepartment(department);
        ft.replace(R.id.book_appointment_frag_space, mSelProf, "selProf").addToBackStack("selProf").commit();
        frame2.removeAllViews();
    }

    void launchSelDate(String facultyName) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment selDateFrag = fm.findFragmentByTag("selDate");
        if (selDateFrag != null) {
            mSelDate.setFacultyName(facultyName);
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (mSelDate == null) {
            mSelDate = new DateAppointmentFragment();
        }
        mSelDate.setFacultyName(facultyName);
        ft.add(R.id.book_appointment_frag_space_sel_date, mSelDate, "selDate").addToBackStack("selDate").commit();
    }



}
