package com.example.iitdost.RequestDocument;


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

import com.example.iitdost.Adapters.CustomAdapter;
import com.example.iitdost.HomeScreen.MainActivity;
import com.example.iitdost.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectDocumentFragment extends Fragment {



    private SelectDocumentFragment selectDocumentFragment=this;
    private LinearLayoutManager llm;
    private RadioGroup radioGroup;
    private RecyclerView documentListView;
    private ArrayList<String> documentList;
    private ArrayList<String> transcriptsAndDegree,others;
    CustomAdapter documentAdapter;
    ProgressDialog progress ;
    private int checked;


    public SelectDocumentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_document, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        radioGroup = view.findViewById(R.id.selDocumentTypeRG);
        documentListView = view.findViewById(R.id.documentList);
        llm = new LinearLayoutManager(getContext());

        initializeView();

        radioGroup.check(R.id.transcriptsRadio);
        checked=R.id.transcriptsRadio;

        super.onViewCreated(view, savedInstanceState);
    }

    private void initializeView(){
        transcriptsAndDegree = new ArrayList<>();
        transcriptsAndDegree.addAll(new ArrayList<>(Arrays.asList("Semester Gradesheet (Duplicate)", "Degree Certificate (Original)", "Degree Certificate (Duplicate)", "Provisional Degree Certificate","Full Time to Part Time Conversion","Part Time to Full Time Conversion")));
        others = new ArrayList<>();
        others.addAll(new ArrayList<>(Arrays.asList("Identity Card (Duplicate)","Bona Fide Certificate","Character Certificate","Migration Certificate","Semester Drop Request","Document Verification")));

        documentList =new ArrayList<>();


        documentAdapter=new CustomAdapter(documentList,getActivity());

        documentAdapter.setSize(18);

        documentListView.setLayoutManager(llm);
        documentListView.setAdapter(documentAdapter);

//        BookAppointmentAPI.getInstance().getDocumentList(this, transcriptsAndDegree, others,getContext());
//        showProgressDialog();

        radioGroup.setOnCheckedChangeListener(radioGroupListener);

    }

    RadioGroup.OnCheckedChangeListener radioGroupListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(checked==i)
            {
                return;
            }else{
                checked=i;
            }
            switch (i){
                case R.id.transcriptsRadio:
                    documentList.clear();
                    documentList.addAll(transcriptsAndDegree);
                    documentAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged: Setting Transcripts Radio");
                    break;
                case R.id.documentRadio:
                    documentList.clear();
                    documentList.addAll(others);
                    documentAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged: Setting Others Radio");
                    break;
                default:
                    Log.d(TAG, "onCheckedChanged: In Default");
                    break;
            }
        }
    };

    public void onAPICallSuccess(Vector<ArrayList<String>> resultVector){
        transcriptsAndDegree=resultVector.get(0);
        others=resultVector.get(1);


        documentList.clear();

        switch (checked){
            case R.id.transcriptsRadio:
                documentList.addAll(transcriptsAndDegree);
                break;
            case R.id.documentRadio:
                documentList.addAll(others);
                break;
            default:
                Log.d(TAG, "onCheckedChanged: In Default");
                break;
        }



        documentAdapter.notifyDataSetChanged();

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
//                BookAppointmentAPI.getInstance().getDocumentList(selectDocumentFragment, transcriptsAndDegree, others,getContext());
                showProgressDialog();
            }
        });
        alertBuilder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                selectDocumentFragment.getActivity().finish();
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

    @Override
    public void onResume() {
        radioGroup.check(R.id.transcriptsRadio);
        super.onResume();
    }

}
