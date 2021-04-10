package com.example.CISC4900.WeightliftingApp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEditExerciseActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_DATE = "EXTRA_DATE";
    public static final String EXTRA_EXERCISE = "EXTRA_EXERCISE";
    public static final String EXTRA_WEIGHT = "EXTRA_WEIGHT";
    public static final String EXTRA_SETS = "EXTRA_SETS";
    public static final String EXTRA_REPS = "EXTRA_REPS";

    String[] numberPickerWeightArray;
    String[] exercises;

    int sets = 4;
    int reps = 8;
    int weight = 26;

    NumberPicker numberPickerWeight;
    NumberPicker numberPickerSets;
    NumberPicker numberPickerReps;

    Calendar calendar;
    Date date;

    Toolbar toolbar;

    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        /*db = Room.databaseBuilder(getApplicationContext(), RecordAppDatabase.class, "record_database").allowMainThreadQueries().build();
        List<Record> records = db.recordDao().getAll();*/

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        exercises = getResources().getStringArray(R.array.exercises);
        numberPickerSets = findViewById(R.id.numberPickerSets);
        numberPickerReps = findViewById(R.id.numberPickerReps);
        numberPickerWeight = findViewById(R.id.numberPickerWeight);
        numberPickerWeightArray = new String[81];
        for (int i = 1; i <= numberPickerWeightArray.length; i++) {
            numberPickerWeightArray[i - 1] = String.valueOf(i * 5);
        }

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);

        autoCompleteTextView.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, exercises);
        autoCompleteTextView.setAdapter(adapter);

        numberPickerWeight.setMinValue(0);
        numberPickerWeight.setMaxValue(80);
        numberPickerWeight.setValue(26);
        numberPickerWeight.setDisplayedValues(numberPickerWeightArray);

        numberPickerWeight.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                weight = newVal;
            }
        });

        numberPickerSets.setMinValue(1);
        numberPickerSets.setMaxValue(10);
        numberPickerSets.setValue(4);

        numberPickerSets.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                sets = newVal;
            }
        });

        numberPickerReps.setMinValue(1);
        numberPickerReps.setMaxValue(30);
        numberPickerReps.setValue(8);

        numberPickerReps.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                reps = newVal;
            }
        });

        //When clicking on weight number picker number keyboard pops up
        EditText input = findInput(numberPickerWeight);
        input.setRawInputType(InputType.TYPE_CLASS_NUMBER);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Exercise");
            autoCompleteTextView.setHint(intent.getStringExtra(EXTRA_EXERCISE));
            numberPickerWeight.setValue((intent.getIntExtra(EXTRA_WEIGHT, 26)) / 5 - 1);
            numberPickerSets.setValue(intent.getIntExtra(EXTRA_SETS, 1));
            numberPickerReps.setValue(intent.getIntExtra(EXTRA_REPS, 1));
        } else {
            setTitle("Add Exercise");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_exercise_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changeDate:
                pickDate();
                return true;
            case R.id.save:
                if (autoCompleteTextView.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "Type exercise to continue", Toast.LENGTH_SHORT).show();
                    return true;
                }
                //saveRecord();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*private void saveRecord() {
        Intent data = new Intent(getBaseContext(), LogFragment.class);
        data.putExtra(EXTRA_DATE, date.getTime());
        data.putExtra(EXTRA_EXERCISE, autoCompleteTextView.getText().toString());
        data.putExtra(EXTRA_WEIGHT, weight * 5 + 5);
        data.putExtra(EXTRA_SETS, sets);
        data.putExtra(EXTRA_REPS, reps);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }*/

    //When clicking on weight number picker number keyboard pops up
    private EditText findInput(ViewGroup np) {
        int count = np.getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = np.getChildAt(i);
            if (child instanceof ViewGroup) {
                findInput((ViewGroup) child);
            } else if (child instanceof EditText) {
                return (EditText) child;
            }
        }
        return null;
    }

    public void pickDate() {
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.show(getSupportFragmentManager(), "hello");
    }

    @Override
    public void onFinishDialog(Date date) {
        this.date = date;
        //Toast.makeText(this, "Selected Date: "+ formatDate(date), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Selected Date: "+ new SimpleDateFormat("dd/MM/yyyy").format(date), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Selected Date: " + new SimpleDateFormat("EEEE, MMMM d, yyyy").format(date), Toast.LENGTH_SHORT).show();

    }
    /*public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }*/
}
