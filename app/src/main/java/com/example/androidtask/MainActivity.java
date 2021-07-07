package com.example.androidtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fbtnAdd;
    ArrayList<EmployeeData> mEmployeeData;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbtnAdd = findViewById(R.id.fbtnAdd);
        Date date = new Date(5/6/20);
        mEmployeeData = new ArrayList<>();
        mEmployeeData.add(new EmployeeData("tamer",800.5,true,date, EmployeeData.Gender.Male));

        fbtnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEmployeeActivity.class);
            startActivity(intent);
        });

        RecyclerView recyclerView =findViewById(R.id.recyclerview);
        EmployeeAdapter adapter = new EmployeeAdapter(mEmployeeData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}



//    private void saveData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(mEmployeeData);
//        editor.putString("task list", json);
//        editor.apply();
//    }

//    private void loadData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString(gson.toJson(mEmployeeData), null);
//        Type type = new TypeToken<ArrayList<EmployeeData>>() {
//        }.getType();
//        mEmployeeData = gson.fromJson(json, type);
//        if (mEmployeeData == null) {
//            mEmployeeData = new ArrayList<>();
//        }
//    }

//    private void buildRecyclerView() {
//        mRecyclerView = findViewById(R.id.recyclerview);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
//        mEmployeeAdapter = new EmployeeAdapter(mEmployeeData);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mEmployeeAdapter);
//    }


//    public void createData(String name, String salary) {
//        mEmployeeData.add(new EmployeeData(name, salary));
//        mEmployeeAdapter.notifyItemInserted(mEmployeeData.size());
//
//    }