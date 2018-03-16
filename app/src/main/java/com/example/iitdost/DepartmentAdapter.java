package com.example.iitdost;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ankurshaswat on 16/3/18.
 */

public class DepartmentAdapter  extends RecyclerView.Adapter<DepartmentAdapter.MyViewHolder> {

        private List<String> departmentList;
        /**
         * View holder class
         * */
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView department;

            public MyViewHolder(View view) {
                super(view);
                department = (TextView) view.findViewById(R.id.department);
            }
        }

        public DepartmentAdapter(List<String> departmentList) {
            this.departmentList = departmentList;

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            String department = departmentList.get(position);
            holder.department.setText(department);
        }

        @Override
        public int getItemCount() {
            return departmentList.size();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.box_department,parent, false);
            v.setOnClickListener(mOnClickListener);
            return new MyViewHolder(v);
        }




    View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

}


