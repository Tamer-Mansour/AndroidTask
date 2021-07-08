package com.example.androidtask;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;


public class AddEmployeeActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button btnSave;
    ArrayList<Employee> employeeList = new ArrayList<>();
    DatePicker btnDate;
    public final static String key = "data";
    EditText etName, etSalary;
    RadioGroup rgGende;
    CheckBox cbActive;
    EmployeeAdapter employeeAdapter;
    RadioButton radioBtnMale, radioBtnFemale;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee_activity);
        rgGende = findViewById(R.id.rgGender);

        initDataPiker();
        setSubmetButton();
        btnDate = findViewById(R.id.btnDate);

        radioBtnMale = findViewById(R.id.radioBtnMale);
        radioBtnFemale = findViewById(R.id.radioBtnFemale);

        btnSave = findViewById(R.id.btnSave);
        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);
        cbActive = findViewById(R.id.cbActive);


        btnSave.setOnClickListener(v -> {
            saveData();
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
                    str.append("Active");
                } else {
                    str.append("InActive");
                }
                break;
        }

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String name = etName.getText().toString();
        double salary = Double.parseDouble(etSalary.getText().toString());
        boolean active = cbActive.isChecked();
        LocalDate date = LocalDate.of(btnDate.getYear(), btnDate.getMonth(), btnDate.getDayOfMonth());
        Employee.Gender gender;
        Employee employee = new Employee(name, salary, active, date, Employee.Gender.Male);
        Log.e("TAG", employee.getName() + " " + employee.getSalary() + " " + employee.getDate() + " " + employee.getGender() + " " + employee.getActive());


        Gson gson = new Gson();
        employeeList.add(employee);
        String json = gson.toJson(employeeList);
        Log.e(" ", json);
        editor.putString(key, json);
        editor.apply();


    }

    private void setSubmetButton() {
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            insertItem(etName.getText().toString(), Double.parseDouble(etSalary.getText().toString()), cbActive.isChecked(), LocalDate.now(), Employee.Gender.Male);

        });
    }

    private void insertItem(String name, double salary, boolean active, LocalDate date, Employee.Gender gender) {
        employeeList.add(new Employee(name, salary, active, date, gender));
        employeeAdapter.notifyItemInserted(employeeList.size());
    }
}