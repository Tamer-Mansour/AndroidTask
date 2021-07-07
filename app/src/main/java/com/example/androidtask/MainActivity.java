package com.example.androidtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fbtnAdd;
    Button btnSubmit,btnSave;
    ArrayList<EmployeeData> mEmployeeData;
    private RecyclerView mRecyclerView;
    private EmployeeAdapter mEmployeeAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fbtnAdd= findViewById(R.id.fbtnAdd);
        btnSubmit = findViewById(R.id.btnSubmit);


//        buildRecyclerView();
//        saveDataButton();
        loadData();

        fbtnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEmployeeActivity.class);
            startActivity(intent);
        });
    }


    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mEmployeeData);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(gson.toJson(mEmployeeData), null);
        Type type = new TypeToken<ArrayList<EmployeeData>>() {}.getType();
        mEmployeeData = gson.fromJson(json, type);
        if (mEmployeeData == null) {
            mEmployeeData = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mEmployeeAdapter = new EmployeeAdapter(mEmployeeData);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mEmployeeAdapter);
    }
    public void saveDataButton(){
        btnSubmit.setOnClickListener(v -> {
            EditText etName = findViewById(R.id.etName);
            EditText etSalary = findViewById(R.id.etSalary);
            createData(etName.getText().toString(), etSalary.getText().toString());
        });

    }
    public void createData(String name ,String salary){
        mEmployeeData.add(new EmployeeData(name,salary));
        mEmployeeAdapter.notifyItemInserted(mEmployeeData.size());

    }
}