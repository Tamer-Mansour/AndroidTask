package com.example.androidtask;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>  {
    private ArrayList<Employee> employeeList;
    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewLine1;
        public TextView textViewLine2;

    public EmployeeViewHolder(View itemView) {
        super(itemView);
        textViewLine1 = itemView.findViewById(R.id.textview_line1);
        textViewLine2 = itemView.findViewById(R.id.textview_line_2);
    }
    }

    public EmployeeAdapter(ArrayList<Employee> employeeLists) {
        employeeList = employeeLists;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee, parent, false);
        EmployeeViewHolder evh = new EmployeeViewHolder(v);
        return evh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.EmployeeViewHolder holder, int position) {
        Employee currentItem = employeeList.get(position);

        holder.textViewLine1.setText(currentItem.getName());
        holder.textViewLine2.setText(" " +currentItem.getSalary());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }


}
