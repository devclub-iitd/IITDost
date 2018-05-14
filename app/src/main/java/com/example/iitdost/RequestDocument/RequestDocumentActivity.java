package com.example.iitdost.RequestDocument;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.iitdost.R;

public class RequestDocumentActivity extends AppCompatActivity {


    public enum State {
        SELECT_DOCUMENT_TYPE,
        SELECT_SEMSTER,
        PAYMENT,
        CONFIRMATION
    }

    String document,semester;

    SelectDocumentFragment mSelDoc;
    SelectSemesterFragment mSelSem;

    FrameLayout frame1, frame2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_document);

        Toolbar toolbar = findViewById(R.id.toolbarRequestDocument);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_left_arrow);

        frame1 = findViewById(R.id.appointmentFragSpace1);
        frame2 = findViewById(R.id.appointmentFragSpace2);

        launchSelDoc();
    }


    public void changeState(State state,String val) {
        switch (state) {
            case SELECT_DOCUMENT_TYPE:
                launchSelDoc();
                break;
            case SELECT_SEMSTER:
                document=val;
                launchSelSem();
                break;
            default:
                break;
        }
    }

    private void launchSelDoc(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mSelDoc==null){
            mSelDoc=new SelectDocumentFragment();
        }
        ft.replace(R.id.appointmentFragSpace1, mSelDoc, "selDoc").commit();
        frame2.removeAllViews();
    }

    private void launchSelSem(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mSelSem==null){
            mSelSem=new SelectSemesterFragment();
        }
        ft.replace(R.id.appointmentFragSpace1, mSelSem, "selSem").addToBackStack("selSem").commit();
        frame2.removeAllViews();
    }

}
