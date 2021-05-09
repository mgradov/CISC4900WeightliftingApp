package com.example.CISC4900.WeightliftingApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OneRepMaxFragment extends Fragment {

    /*private double oneRepMax    = EstimateRepMaxPercent.ONE_REP_MAX_BAECHLE    ;
    private double twoRepMax    = EstimateRepMaxPercent.TWO_REP_MAX_BAECHLE    ;
    private double threeRepMax  = EstimateRepMaxPercent.THREE_REP_MAX_BAECHLE  ;
    private double fourRepMax   = EstimateRepMaxPercent.FOUR_REP_MAX_BAECHLE   ;
    private double fiveRepMax   = EstimateRepMaxPercent.FIVE_REP_MAX_BAECHLE   ;
    private double sixRepMax    = EstimateRepMaxPercent.SIX_REP_MAX_BAECHLE    ;
    private double sevenRepMax  = EstimateRepMaxPercent.SEVEN_REP_MAX_BAECHLE  ;
    private double eightRepMax  = EstimateRepMaxPercent.EIGHT_REP_MAX_BAECHLE  ;
    private double nineRepMax   = EstimateRepMaxPercent.NINE_REP_MAX_BAECHLE   ;
    private double tenRepMax    = EstimateRepMaxPercent.TEN_REP_MAX_BAECHLE    ;
    private double elevenRepMax = EstimateRepMaxPercent.ELEVEN_REP_MAX_BAECHLE ;
    private double twelveRepMax = EstimateRepMaxPercent.TWELVE_REP_MAX_BAECHLE ;
    private double fifteenRepMax= EstimateRepMaxPercent.FIFTEEN_REP_MAX_BAECHLE;

    private double repMaxSelector = oneRepMax;*/

    EstimateRepMaxPercent eRMP = new EstimateRepMaxPercent();

    private int estimateRepMaxPercentSetting;

    private ImageView imageView;
    private TextView calculateText;

    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;

    private EditText weightText;
    private EditText repsText;

    private Double weight;
    private Double reps;

    private int repMaxSelection;

    private String[] repMaxes;
    private NumberPicker numberPickerRepMaxes;

    //Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one_rep_max, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //toolbar = getView().findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

                textView = getView().findViewById(R.id.textView);
        textView2 = getView().findViewById(R.id.textView2);
        textView3 = getView().findViewById(R.id.textView3);
        textView4 = getView().findViewById(R.id.textView4);
        textView5 = getView().findViewById(R.id.textView5);
        textView6 = getView().findViewById(R.id.textView6);
        textView7 = getView().findViewById(R.id.textView7);

        weightText = getView().findViewById(R.id.weightText);
        repsText = getView().findViewById(R.id.repsText);

        weightText.requestFocus();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        weightText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty() && !repsText.getText().toString().trim().isEmpty()){
                    eRMP.setRepMaxPercent(repMaxSelection);
                    calculate1RM();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        repsText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty() && !weightText.getText().toString().trim().isEmpty()){
                    eRMP.setRepMaxPercent(repMaxSelection);
                    calculate1RM();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        repsText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard();
                    handled = true;
                }
                return handled;
            }
        });

        repMaxes = getResources().getStringArray(R.array.repMaxes);
        numberPickerRepMaxes = getView().findViewById(R.id.numberPickerRepMaxes);
        numberPickerRepMaxes.setMinValue(0);
        numberPickerRepMaxes.setMaxValue(12);
        numberPickerRepMaxes.setValue(0);
        numberPickerRepMaxes.setDisplayedValues(repMaxes);
        numberPickerRepMaxes.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                repMaxSelection = newVal;
                eRMP.setRepMaxPercent(repMaxSelection);
                calculate1RM();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        hideKeyboard();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_one_rep_max_activity, menu);
        return true;
    }*/

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_one_rep_max_activity, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.estimate_reps_percent_baechle:
                eRMP.setDataSet(EstimateRepMaxPercent.BAECHLE);
                eRMP.setRepMaxPercent(repMaxSelection);
                calculate1RM();
                return true;
            case R.id.estimate_reps_percent_brzycki:
                eRMP.setDataSet(EstimateRepMaxPercent.BRZYCKI);
                eRMP.setRepMaxPercent(repMaxSelection);
                calculate1RM();
                return true;
            case R.id.estimate_reps_percent_dos_remedios:
                eRMP.setDataSet(EstimateRepMaxPercent.DOS_REMEDIOS);
                eRMP.setRepMaxPercent(repMaxSelection);
                calculate1RM();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clearResults() {
        textView.setText("");
        textView2.setText("");
        textView3.setText("");
        textView4.setText("");
        textView5.setText("");
        textView6.setText("");
        textView7.setText("");
    }

    public void calculate1RM() {

        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        if (weightText.getText().toString().trim().isEmpty() || repsText.getText().toString().trim().isEmpty()) {
            Toast.makeText(getActivity(), "Enter weight and reps", Toast.LENGTH_SHORT).show();
            return;
        } else {
            weight = Double.parseDouble(weightText.getText().toString());
            reps = Double.parseDouble(repsText.getText().toString());
        }

        textView.setText(epley(weight, reps));
        textView2.setText(brzycki(weight, reps));
        textView3.setText(lander(weight, reps));
        textView4.setText(mayhew(weight, reps));
        textView5.setText(lombardi(weight, reps));
        textView6.setText(wathen(weight, reps));
        textView7.setText(oConner(weight, reps));
    }

    public String epley(Double weight, Double reps) {
        //return "Epley: " + new BigDecimal(weight * (1 + reps / 30)).setScale(2, RoundingMode.HALF_UP) ;
        //int result = (int) ((weight * (1 + reps / 30)) * repMaxSelector);
        int result = (int) eRMP.getRepMax(new OneRepMax(weight, reps).epley());
        if (result == 0){
            return "N/A";
        }
        return "" + result;
    }

    public String brzycki(Double weight, Double reps) {
        //return "Brzycki: " + new BigDecimal(weight * (36 / (37 - reps))).setScale(2, RoundingMode.HALF_UP);
        //int result = (int) ((weight * (36 / (37 - reps))) * repMaxSelector);
        int result = (int) eRMP.getRepMax(new OneRepMax(weight, reps).brzycki());
        if (result == 0){
            return "N/A";
        }
        return "" + result;
    }

    public String lander(Double weight, Double reps) {
        //return "Lander: " + new BigDecimal(weight / (1.013 - 0.0267123 * reps)).setScale(2, RoundingMode.HALF_UP);
        //int result = (int) ((weight / (1.013 - 0.0267123 * reps)) * repMaxSelector);
        int result = (int) eRMP.getRepMax(new OneRepMax(weight, reps).lander());
        if (result == 0){
            return "N/A";
        }
        return "" + result;
    }

    public String mayhew(Double weight, Double reps) {
        //return "Mayhew: " + new BigDecimal(100 * weight / (52.2 + 41.9 * Math.exp(-0.055 * reps))).setScale(2, RoundingMode.HALF_UP);
        //int result = (int) ((100 * weight / (52.2 + 41.9 * Math.exp(-0.055 * reps))) * repMaxSelector);
        int result = (int) eRMP.getRepMax(new OneRepMax(weight, reps).mayhew());
        if (result == 0){
            return "N/A";
        }
        return "" + result;
    }

    public String lombardi(Double weight, Double reps) {
        //eturn "Lombardi: " + new BigDecimal(weight * Math.pow(reps, 0.10)).setScale(2, RoundingMode.HALF_UP);
        //int result =(int) ((weight * Math.pow(reps, 0.10)) * repMaxSelector);
        int result = (int) eRMP.getRepMax(new OneRepMax(weight, reps).lombardi());
        if (result == 0){
            return "N/A";
        }
        return "" + result;
    }

    public String wathen(Double weight, Double reps) {
        //return "Wathen: " + new BigDecimal((100 * weight) / (48.8 + 53.8 * Math.exp(-0.075 * reps))).setScale(2, RoundingMode.HALF_UP);
        //int result = (int) ((100 * weight) / (48.8 + 53.8 * Math.exp(-0.075 * reps)) * repMaxSelector);
        int result = (int) eRMP.getRepMax(new OneRepMax(weight, reps).wathen());
        if (result == 0){
            return "N/A";
        }
        return "" + result;
    }

    public String oConner(Double weight, Double reps) {
        //return "O'Conner: " + new BigDecimal(weight * (1 + 0.025 * reps)).setScale(2, RoundingMode.HALF_UP);
        //int result = (int) (weight * (1 + 0.025 * reps) * repMaxSelector);
        int result = (int) eRMP.getRepMax(new OneRepMax(weight, reps).oConner());
        if (result == 0){
            return "N/A";
        }
        return "" + result;
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getRootView().getWindowToken(),0);
    }

    public void setEstimateRepMaxPercent(int i) {
        switch (i) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            default:


        }

    }
}