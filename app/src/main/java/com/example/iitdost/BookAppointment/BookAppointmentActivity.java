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

    String department,faculty,date,time,purpose;

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
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_left_arrow);

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
                department=val;
                launchSelFaculty(val);
                break;
            case SELECT_DATE:
                faculty=val;
                launchSelDate();
                break;
            case CONFIRM_DATE:
                date=val;
                launchConfirmDate();
                break;
            case SELECT_TIME:
                launchSelTime();
                break;
            case SELECT_PURPOSE:
                time=val;
                launchSelPurpose();
                break;
            case BOOK:
                purpose=val;
                launchBook();
                break;
            case CONFIRMATION:
                launchConfirmation(department,faculty,date,time,purpose);
            default:
                    break;
        }
    }

    private void launchSelDep(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mSelDep==null){
            mSelDep=new SelectDepartmentFragment();
        }
        ft.replace(R.id.appointmentFragSpace1, mSelDep, "selDep").commit();
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

    private void launchSelDate() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentByTag("selDate");
        if (frag != null) {
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (mSelDate == null) {
            mSelDate = new DateAppointmentFragment();
        }
        ft.add(R.id.appointmentFragSpace2, mSelDate, "selDate").addToBackStack("selDate").commit();
    }

    private void launchConfirmDate(){
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

    private void launchSelTime(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentByTag("selTime");
        if (frag != null) {
            mSelTime.setVals(faculty,date);
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (mSelTime == null) {
            mSelTime = new SelectTimeFragment();
            mSelTime.setVals(faculty,date);
        }
        ft.detach(mSelDate);
        ft.detach(mConfDate);
        ft.replace(R.id.appointmentFragSpace1, mSelTime, "selTime").addToBackStack("selTime").commit();
    }

    private void launchSelPurpose(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentByTag("selPurpose");
        if (frag != null) {
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (mSelPurpose == null) {
            mSelPurpose = new SelectPurposeFragment();
        }
        ft.add(R.id.appointmentFragSpace2, mSelPurpose, "selPurpose").addToBackStack("selPurpose").commit();

    }

    private void launchBook(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentByTag("book");
        if (frag != null) {
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (mBook == null) {
            mBook = new ConfirmBookFragment();
        }
        ft.add(R.id.appointmentFragSpace3, mBook, "book").addToBackStack("book").commit();

    }

    private void launchConfirmation(String department, String faculty, String date, String time, String purpose){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentByTag("confirmation");
        if (frag != null) {
            mConfirm.setVals(department,faculty,date,time,purpose);
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (mConfirm == null) {
            mConfirm = new ConfirmationFragment();
        }
        ft.detach(mSelPurpose);
        ft.detach(mBook);
        ft.replace(R.id.appointmentFragSpace1, mConfirm, "confirmation").addToBackStack("confirmation").commit();
    }


}
