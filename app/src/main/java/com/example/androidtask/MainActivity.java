package com.example.androidtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fbtnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbtnAdd= findViewById(R.id.fbtnAdd);

        fbtnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,EmployeePage.class);
            startActivity(intent);
        });
    }
}