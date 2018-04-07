package com.example.iitdost.BookAppointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.iitdost.R;

import java.util.Enumeration;

public class BookAppointmentActivity extends AppCompatActivity {


    public enum State {
        SELECT_DEPARTMENT,
        SELECT_FACULTY,
        SELECT_DATE,
        CONFIRM_DATE,
        SELECT_TIME,
        SELECT_PURPOSE,
        BOOK,
        CONFIRMATION
    }

    SelectDepartmentFragment mSelDep;
    WhomToMeetFragment mSelProf;
    DateAppointmentFragment mSelDate;
    ConfirmDateFragment mConfDate;
    SelectTimeFragment mSelTime;
    SelectPurposeFragment mSelPurpose;
    ConfirmBookFragment mBook;
    ConfirmationFragment mConfirm;
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

        frame1 = findViewById(R.id.appointmentFragSpace1);
        frame2 = findViewById(R.id.appointmentFragSpace2);

        launchSelDep();
    }

    public void changeState(State state,String val) {
        switch (state) {
            case SELECT_DEPARTMENT:
                launchSelDep();
                break;
            case SELECT_FACULTY:
                launchSelFaculty(val);
                break;
            case SELECT_DATE:
                launchSelDate(val);
                break;
            case CONFIRM_DATE:
                launchConfirmDate(val);
                break;
            case SELECT_TIME:
                launchSelTime(val);
                break;
            case SELECT_PURPOSE:
                break;
            case BOOK:
                break;
            case CONFIRMATION:
                break;

                default:
                    break;
        }
    }

    private void launchSelDep(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mSelDep==null){
            mSelDep=new SelectDepartmentFragment();
        }
        ft.replace(R.id.appointmentFragSpace1, mSelDep, "selDep").addToBackStack("selDep").commit();
        frame2.removeAllViews();
    }

    private void launchSelFaculty(String department){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mSelProf==null){
            mSelProf=new WhomToMeetFragment();
        }
        mSelProf.setDepartment(department);
        ft.replace(R.id.appointmentFragSpace1, mSelProf, "selProf").addToBackStack("selProf").commit();
        frame2.removeAllViews();
    }

    private void launchSelDate(String facultyName) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentByTag("selDate");
        if (frag != null) {
            mSelDate.setFacultyName(facultyName);
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (mSelDate == null) {
            mSelDate = new DateAppointmentFragment();
        }
        mSelDate.setFacultyName(facultyName);
        ft.add(R.id.appointmentFragSpace2, mSelDate, "selDate").addToBackStack("selDate").commit();
    }

    private void launchConfirmDate(String date){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentByTag("confDate");
        if (frag != null) {
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (mConfDate == null) {
            mConfDate = new ConfirmDateFragment();
        }
        ft.add(R.id.appointmentFragSpace3, mConfDate, "confDate").addToBackStack("confDate").commit();
    }

    private void launchSelTime(String date){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentByTag("confDate");
        if (frag != null) {
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (mConfDate == null) {
            mConfDate = new ConfirmDateFragment();
        }
        ft.detach(mSelDate);
        ft.detach(mConfDate);
        ft.replace(R.id.appointmentFragSpace1, mSelTime, "selTime").addToBackStack("selTime").commit();
    }





}
