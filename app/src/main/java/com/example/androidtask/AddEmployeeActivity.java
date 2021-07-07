package com.example.androidtask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.androidtask.EmployeeData.Gender.Male;

public class AddEmployeeActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button btnDate,btnSave,btnSubmit;

    EditText etName,etSalary;
    RadioGroup rgGende;
    CheckBox cbActive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee_activity);
        rgGende = findViewById(R.id.rgGender);

        initDataPiker();
        btnDate = findViewById(R.id.btnDate);
        btnDate.setText(getTodayDate());
        btnSave = findViewById(R.id.btnSave);
        btnSubmit = findViewById(R.id.btnSubmit);
        etName =findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);
        cbActive = findViewById(R.id.cbActive);

        btnSubmit.setOnClickListener(v -> {
            saveData();
        });
        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent(AddEmployeeActivity.this,MainActivity.class);
            startActivity(intent);
        });

    }
    public void onClick(View view){
        int tybe = 0;
        StringBuilder result = new StringBuilder();
        result.append("Gender");
        if (Male.isChecked()){
            result.append("Male");
        }else {
            result.append("Female");
        }
    }
    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month +=1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month ,year);
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
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat (month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUl";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";
        //defult
        return "JAN";

    }
    private void saveData() {
        ArrayList<String> mEmployeeData = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        mEmployeeData.add(etName.getText().toString() + etSalary.getText().toString() +rgGende.toString()+);
        editor.putString(" ", String.valueOf(mEmployeeData));
        editor.apply();


    }

    public void openDatePiker(View view) {
        datePickerDialog.show();
    }

}