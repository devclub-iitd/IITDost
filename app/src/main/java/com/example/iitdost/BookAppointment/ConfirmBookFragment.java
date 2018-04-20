package com.example.iitdost.BookAppointment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.iitdost.HomeScreen.MainActivity;
import com.example.iitdost.R;

public class ConfirmBookFragment extends Fragment {

    Button book,cancel;
    ConfirmBookFragment confirmBookFragment=this;

    public ConfirmBookFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_buttons, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        cancel=view.findViewById(R.id.btnCancelBook);
        book=view.findViewById(R.id.btnBook);



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                confirmBookFragment.getActivity().finish();
            }
        });


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BookAppointmentActivity) getActivity()).changeState(BookAppointmentActivity.State.CONFIRMATION,"");
            }
        });
    }
}
