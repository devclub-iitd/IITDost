package com.example.iitdost;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BookAppointmentActivity extends AppCompatActivity {


    SelectDepartmentFragment mSelDep;
    WhomToMeetFragment mSelProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        launchSelDep();
    }



    void launchSelDep(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mSelDep==null){
            mSelDep=new SelectDepartmentFragment();
        }
        ft.replace(R.id.book_appointment_frag_space, mSelDep).addToBackStack("selDep").commit();
    }

    void launchSelProf(String department){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mSelProf==null){
            mSelProf=new WhomToMeetFragment();
        }
        mSelProf.setDepartment(department);
        ft.replace(R.id.book_appointment_frag_space, mSelProf).addToBackStack("selProf").commit();
    }



}
