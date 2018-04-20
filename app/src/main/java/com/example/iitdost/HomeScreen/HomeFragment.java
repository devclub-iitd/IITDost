package com.example.iitdost.HomeScreen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.iitdost.BookAppointment.BookAppointmentActivity;
import com.example.iitdost.R;

/**
 * Created by ankurshaswat on 14/3/18.
 * Fragment to show main options on home screen.
 */

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_main, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button bookAppointmentBtn= view.findViewById(R.id.bookAppointmentButton);
        bookAppointmentBtn.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bookAppointmentButton:
                    Intent intent=new Intent(getContext(),BookAppointmentActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };
}

