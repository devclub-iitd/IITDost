package com.example.iitdost.BookAppointment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.iitdost.APICalls.BookAppointmentAPI;
import com.example.iitdost.Adapters.DepartmentAdapter;
import com.example.iitdost.HomeScreen.MainActivity;
import com.example.iitdost.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import static android.content.ContentValues.TAG;

/**
 * Created by ankurshaswat on 14/3/18.
 * Fragment to be used to show notification list.
 */

public class SelectDepartmentFragment extends Fragment {

    private SelectDepartmentFragment selectDepartmentFragment=this;
    private LinearLayoutManager llm;
    private RadioGroup radioGroup;
    private RecyclerView departmentListView;
    private List<String> departmentList;
    private ArrayList<String> academics,administrative,others;
    DepartmentAdapter departmentAdapter;
    ProgressDialog progress ;

    public SelectDepartmentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_department, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        radioGroup = view.findViewById(R.id.selProfRadioGrp);
        departmentListView = view.findViewById(R.id.departmentList);
        llm = new LinearLayoutManager(getContext());

        initializeView();

        super.onViewCreated(view, savedInstanceState);
    }

    private void initializeView(){
        academics = new ArrayList<>();
//                Arrays.asList("Applied Mechanics", "Biochemical", "Chemical","Civil","Computer Science","Design","Electrical");
        administrative = new ArrayList<>();
//                Arrays.asList("Accounts Section", "Alumni Affairs", "P.G. Section","Security Unit","Student Affairs","Training & Placement","U.G. Section");
        others = new ArrayList<>();
//                Arrays.asList("Student Hostels", "Guest House", "R & D Unit","Store & Purchase","Transport Unit","Sports Office");

        departmentList = Collections.emptyList();


        departmentAdapter=new DepartmentAdapter(departmentList,getActivity());
        departmentListView.setLayoutManager(llm);
        departmentListView.setAdapter(departmentAdapter);

        BookAppointmentAPI.getInstance().getDepartmentList(this, academics, administrative, others,getContext());
        showProgressDialog();


        radioGroup.check(R.id.academicRadio);
        radioGroup.setOnCheckedChangeListener(radioGroupListener);

    }

    RadioGroup.OnCheckedChangeListener radioGroupListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.academicRadio:
                    departmentList.clear();
                    departmentList.addAll(academics);
                    departmentAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged: Setting Academic Radio");
                    break;
                case R.id.administrativeRadio:
                    departmentList.clear();
                    departmentList.addAll(administrative);
                    departmentAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged: Setting Administrative Radio");
                    break;
                case R.id.otherRadio:
                    departmentList.clear();
                    departmentList.addAll(others);
                    departmentAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged: Setting departmentListView Radio");
                    break;
                default:
                    Log.d(TAG, "onCheckedChanged: In Default");
                    break;
            }
        }
    };

    public void onAPICallSuccess(Vector<ArrayList<String>> resultVector){
        academics=resultVector.get(0);
        administrative=resultVector.get(1);
        others=resultVector.get(2);
        departmentAdapter.notifyDataSetChanged();
        dismissProgressDialog();
    }

    public void onAPICallFailure(){
        dismissProgressDialog();
        AlertDialog.Builder alertBuilder=new AlertDialog.Builder(getContext());
        alertBuilder.setMessage("Error");
        alertBuilder.setTitle("Error Title");
        alertBuilder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BookAppointmentAPI.getInstance().getDepartmentList(selectDepartmentFragment, academics, administrative, others,getContext());
                showProgressDialog();
            }
        });
        alertBuilder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                selectDepartmentFragment.getActivity().finish();
            }
        });
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();

    }

    private void showProgressDialog(){
        if(progress==null){
            progress = new ProgressDialog(getContext());
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false);
        }
        progress.show();
    }

    private void dismissProgressDialog(){
        progress.dismiss();
    }
}

