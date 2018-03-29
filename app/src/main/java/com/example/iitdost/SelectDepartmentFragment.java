package com.example.iitdost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ankurshaswat on 14/3/18.
 * Fragment to be used to show notification list.
 */

public class SelectDepartmentFragment extends Fragment {

    private LinearLayoutManager llm;
    private RadioGroup radioGroup;
    private RecyclerView departmentListView;

    public SelectDepartmentFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_select_department, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        radioGroup = view.findViewById(R.id.selProfRadioGrp);
        departmentListView = view.findViewById(R.id.departmentList);

        llm = new LinearLayoutManager(getContext());


        initializeView();


        super.onViewCreated(view, savedInstanceState);

    }


    private void initializeView(){
        List<String> academics = Arrays.asList("Applied Mechanics", "Biochemical", "Chemical","Civil"
                ,"Computer Science","Design","Electrical");
        List<String> administrative = Arrays.asList("Accounts Section", "Alumni Affairs", "P.G. Section","Security Unit"
                ,"Student Affairs","Training & Placement","U.G. Section");
        List<String> others = Arrays.asList("Student Hostels", "Guest House", "R & D Unit","Store & Purchase"
                ,"Transport Unit","Sports Office");


        final DepartmentAdapter academicsAdapter=new DepartmentAdapter(academics,getActivity());
        final DepartmentAdapter administrativeAdapter=new DepartmentAdapter(administrative,getActivity());
        final DepartmentAdapter othersAdapter=new DepartmentAdapter(others,getActivity());



        departmentListView.setLayoutManager(llm);
        departmentListView.setAdapter(academicsAdapter);

        radioGroup.check(R.id.academicRadio);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.academicRadio:
                        Log.d(TAG, "onCheckedChanged: Setting Academic Radio");
                        departmentListView.setAdapter(academicsAdapter);
                        break;
                    case R.id.administrativeRadio:
                        departmentListView.setAdapter(administrativeAdapter);
                        break;
                    case R.id.otherRadio:
                        departmentListView.setAdapter(othersAdapter);
                        break;
                    default:
                        Log.d(TAG, "onCheckedChanged: In Default");
                        break;
                }
            }
        });

//        RadioButton newButton=getLayoutInflater().inflate(R.id.)
//        radioGroup.addView();
    }
}

