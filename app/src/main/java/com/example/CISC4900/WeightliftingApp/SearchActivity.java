package com.example.CISC4900.WeightliftingApp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchActivity extends AppCompatActivity implements DatePickerFragmentFrom.DateDialogListener, DatePickerFragmentTo.DateDialogListener {

    public static final String EXTRA_DATE_FROM = "EXTRA_DATE_FROM";
    public static final String EXTRA_DATE_TO = "EXTRA_DATE_TO";

    Date dateFrom;
    Date dateTo;

    //EditText editTextDateFrom;
    //EditText editTextDateTo;

    Button datePickerFrom;
    Button datePickerTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //editTextDateFrom = findViewById(R.id.dateEditText1);
        //editTextDateTo = findViewById(R.id.dateEditText2);

        datePickerFrom = findViewById(R.id.datePickerFrom);
        datePickerTo = findViewById(R.id.datePickerTo);

        datePickerFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateFrom();
            }
        });
        datePickerTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDateTo();
            }
        });

    }

    public void pickDateFrom() {
        DatePickerFragmentFrom dialog = new DatePickerFragmentFrom();
        dialog.show(getSupportFragmentManager(), "hello1");
    }

    public void pickDateTo() {
        DatePickerFragmentTo dialog = new DatePickerFragmentTo();
        dialog.show(getSupportFragmentManager(), "hello2");
    }


    @Override
    public void onFinishDialogFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
        //editTextDateFrom.setText(new SimpleDateFormat("EEEE, MMMM d, yyyy").format(dateFrom));
        Toast.makeText(this, "Selected Date: " + new SimpleDateFormat("EEEE, MMMM d, yyyy").format(dateFrom), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinishDialogTo(Date dateTo) {
        this.dateTo = dateTo;
        Toast.makeText(this, "Selected Date: " + new SimpleDateFormat("EEEE, MMMM d, yyyy").format(dateTo), Toast.LENGTH_SHORT).show();
    }

    public void displayLog(View view) {

        if (dateFrom != null & dateTo != null) {
            /*Intent data = new Intent(getBaseContext(), LogFragment.class);
            data.putExtra(EXTRA_DATE_FROM, dateFrom.getTime());
            data.putExtra(EXTRA_DATE_TO, dateTo.getTime());

            setResult(RESULT_OK, data);
            finish();*/
        } else if (dateFrom != null && dateTo == null || dateFrom == null && dateTo != null) {
            Toast.makeText(this, "implement 1 date selection", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "N/A", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayChart(View view) {

    }
}
