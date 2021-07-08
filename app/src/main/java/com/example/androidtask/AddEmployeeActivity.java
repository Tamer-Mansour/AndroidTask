package com.example.androidtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AddEmployeeActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button btnDate, btnSave, btnSubmit;
    ArrayList<Employee> employeeList = new ArrayList<>();
    private String key = "data";
    EditText etName, etSalary;
    RadioGroup rgGende;
    CheckBox cbActive;
    EmployeeAdapter employeeAdapter;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee_activity);
        rgGende = findViewById(R.id.rgGender);

        initDataPiker();
        setSubmetButton();
        btnDate = findViewById(R.id.btnDate);
        btnDate.setText(getTodayDate());
        btnSave = findViewById(R.id.btnSave);
        btnSubmit = findViewById(R.id.btnSubmit);
        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);
        cbActive = findViewById(R.id.cbActive);

        btnSubmit.setOnClickListener(v -> {
            saveData();
        });
        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent(AddEmployeeActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month += 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDataPiker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month += 1;
                String date = makeDateString(day, month, year);
                btnDate.setText(date);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUl";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        return "JAN";

    }


    public void openDatePiker(View view) {
        datePickerDialog.show();
    }

    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        StringBuilder str = new StringBuilder(cbActive.getText().toString());
        switch (view.getId()) {
            case R.id.cbActive:
                if (checked) {
                    str.append(" " + employeeList);
                } else {
                    str.append(" " + employeeList);
                }
                break;
        }

    }

    private void saveData() {

        String name = etName.getText().toString();
        Double salary = Double.parseDouble(etSalary.getText().toString());
        boolean active = Boolean.parseBoolean(cbActive.getText().toString());
        DatePicker date = datePickerDialog.getDatePicker();

        Employee employee = new Employee(name, salary, active, date, Employee.Gender.Male);
        Log.e("TAG", employee.getName() + " ");

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString(key,employee.getName());
        Gson gson = new Gson();
        String json = gson.toJson(employeeList);
        editor.putString(key, json);
        editor.apply();

    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Employee>>() {
        }.getType();
        employeeList = gson.fromJson(json, type);

        if (employeeList == null) {
            employeeList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        employeeAdapter = new EmployeeAdapter(employeeList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(employeeAdapter);
    }

    private void setSubmetButton() {
        Button buttonSubmet = findViewById(R.id.btnSubmit);
        buttonSubmet.setOnClickListener(v -> {

            insertItem(etName.getText().toString(), etSalary.getText().toString(), ,datePickerDialog.getDatePicker().toString());
        });
    }

    private void insertItem(String name, double salary, Boolean active, DatePicker date, Employee.Gender gender) {
        employeeList.add(new Employee(name, salary, active, date, gender));
        employeeAdapter.notifyItemInserted(employeeList.size());
    }
}