package com.example.androidtask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EmplyeeProfile extends AppCompatActivity {
    TextView tvNameShow;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_profile);
        btnBack = findViewById(R.id.btnBack);
        tvNameShow = findViewById(R.id.tvNameShow);


        String name = AddEmployeeActivity.key;
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            name = extra.getString(AddEmployeeActivity.key);
        }
        tvNameShow.setText(name);








        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EmplyeeProfile.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
