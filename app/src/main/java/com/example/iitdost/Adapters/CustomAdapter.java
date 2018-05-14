package com.example.iitdost.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iitdost.BookAppointment.BookAppointmentActivity;
import com.example.iitdost.R;
import com.example.iitdost.RequestDocument.RequestDocumentActivity;

import java.util.List;

/**
 * Created by ankurshaswat on 16/3/18.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private boolean sizing;
    private List<String> departmentList;
        private Activity parentActivity;
    private float size;

    /**
         * View holder class
         * */
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView department;

            MyViewHolder(View view) {
                super(view);
                department = view.findViewById(R.id.department);
            }
        }

        public CustomAdapter(List<String> departmentList, Activity parentActivity) {
            this.departmentList = departmentList;
            this.parentActivity=parentActivity;

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            String department = departmentList.get(position);
            holder.department.setText(department);
            if(sizing){
                holder.department.setTextSize(size);
            }
        }

        @Override
        public int getItemCount() {
            return departmentList.size();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.box_department, parent, false);
            v.setOnClickListener(mOnClickListener);
            return new MyViewHolder(v);

        }


    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView txt=view.findViewById(R.id.department);
            String selectedVal=txt.getText().toString();

            Activity act = parentActivity;
            if (act instanceof BookAppointmentActivity)
                ((BookAppointmentActivity) act).changeState(BookAppointmentActivity.State.SELECT_FACULTY,selectedVal);
            else if (act instanceof RequestDocumentActivity)
                ((RequestDocumentActivity) act).changeState(RequestDocumentActivity.State.SELECT_SEMSTER,selectedVal);
    }
    };


        public void setSize(float size){
            sizing=true;
            this.size=size;
        }

}


