package com.example.androidtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fbtnAdd;
    ArrayList<Employee> employeeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private EmployeeAdapter employeeAdapter;
    private EmployeeAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        buildRecyclerView();

        fbtnAdd = findViewById(R.id.fbtnAdd);

        fbtnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEmployeeActivity.class);
            startActivity(intent);
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(AddEmployeeActivity.key, " ");
        Type type = new TypeToken<ArrayList<Employee>>() {
        }.getType();
        employeeList = gson.fromJson(json, type);

        if (employeeList == null) {
            employeeList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        setOnClickListener();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        employeeAdapter = new EmployeeAdapter(employeeList,listener);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(employeeAdapter);
    }

    private void setOnClickListener() {
        listener  = (v, position) -> {

            Intent intent = new Intent(getApplicationContext(),EmplyeeProfile.class);
            intent.putExtra(AddEmployeeActivity.key ,"Name Is : "+employeeList.get(position).getName()+" \n\n "+"Salary Is : "+employeeList.get(position).getSalary()
                    +" \n\n "+"Gender Is : "+employeeList.get(position).getGender() +" \n\n "+"Date Is : "+employeeList.get(position).getDate()
                    +" \n\n "+"State Is : "+employeeList.get(position).getActive());

            startActivity(intent);
        };
    }
}
