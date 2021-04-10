package com.example.CISC4900.WeightliftingApp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragmentTo extends DialogFragment {

    private DatePicker datePicker;

    public interface DateDialogListener {
        void onFinishDialogTo(Date date);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);
        datePicker = v.findViewById(R.id.dialog_date_date_picker);
        return new androidx.appcompat.app.AlertDialog.Builder(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar)
                .setView(v)
                //.setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = datePicker.getYear();
                        int mon = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();
                        //Date date = new GregorianCalendar(year,mon,day).getTime();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        calendar.set(year, mon, day);
                        Date dateTo = calendar.getTime();
                        DateDialogListener activity = (DateDialogListener) getActivity();
                        activity.onFinishDialogTo(dateTo);
                        dismiss();
                    }
                })
                .create();
    }
}