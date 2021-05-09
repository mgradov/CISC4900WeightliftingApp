package com.example.CISC4900.WeightliftingApp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        LineChart chart = findViewById(R.id.chart);

        RecordDatabase database = RecordDatabase.getInstance(getApplicationContext());
        RecordDao recordDao = database.recordDao();
        List<Record> dataObjects = recordDao.getAllRecordsTest();
        List<Entry> entries = new ArrayList<>();
        /*for (Record data : dataObjects) {
            // turn your data into Entry objects
            entries.add(new Entry((float) data.getDate().getTime(), (float) data.getWeight()));
        }*/

        /*dataObjects.add(new Record());
        List<Integer> groupByDateArray = new ArrayList<>();
        groupByDateArray.add(dataObjects.get(0).getWeight());
        for (int i = 0; i < dataObjects.size() - 1; i++) {
            System.out.println(groupByDateArray);
            if (dataObjects.get(i).getDate().getTime() == dataObjects.get(i + 1).getDate().getTime()) {
                groupByDateArray.add(dataObjects.get(i + 1).getWeight());
            } else {
                int sum = 0;
                for (Integer integer : groupByDateArray) {
                    sum += integer;
                }
                int avg = sum / groupByDateArray.size();
                entries.add(new Entry((float) dataObjects.get(i).getDate().getTime(), (float) avg));
                groupByDateArray.clear();
                groupByDateArray.add(dataObjects.get(i + 1).getWeight());
            }
        }*/

        for (Record r: dataObjects             ) {
            System.out.println(r.getOneRepMax());
        }

        dataObjects.add(new Record());
        List<Double> groupByDateArray = new ArrayList<>();
        groupByDateArray.add(dataObjects.get(0).getOneRepMax());
        for (int i = 0; i < dataObjects.size() - 1; i++) {
            if (dataObjects.get(i).getDate().getTime() == dataObjects.get(i + 1).getDate().getTime()) {
                groupByDateArray.add(dataObjects.get(i + 1).getOneRepMax());
            } else {
                int sum = 0;
                for (Double d : groupByDateArray) {
                    sum += d;
                }
                int avg = sum / groupByDateArray.size();
                entries.add(new Entry((float) dataObjects.get(i).getDate().getTime(), (float) avg));
                groupByDateArray.clear();
                groupByDateArray.add(dataObjects.get(i + 1).getOneRepMax());
            }
        }

        /*dataObjects.add(new Record());
        List<Integer> groupByDateArray = new ArrayList<>();
        groupByDateArray.add(dataObjects.get(0).getWeight());
        for (int i = 0; i < dataObjects.size() - 1; i++) {
            System.out.println(groupByDateArray);
            if (dataObjects.get(i).getDate().getTime() == dataObjects.get(i + 1).getDate().getTime()) {
                groupByDateArray.add(dataObjects.get(i + 1).getWeight());
            } else {
                int sum = 0;
                for (Integer integer : groupByDateArray) {
                    sum += integer;
                }
                int avg = sum / groupByDateArray.size();
                entries.add(new Entry((float) dataObjects.get(i).getDate().getTime(), (float) avg));
                groupByDateArray.clear();
                groupByDateArray.add(dataObjects.get(i + 1).getWeight());
            }
        }*/

        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setTextSize(14f);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(14f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Date date = new Date((long) value);
                SimpleDateFormat sdf = new SimpleDateFormat("M-d-yy");
                return sdf.format(date);
            }
        });
        //xAxis.setGranularityEnabled(true);
        //xAxis.setGranularity(43200000);

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setValueTextSize(14f);
        dataSet.setLineWidth(4f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        dataSet.setCircleColor(Color.BLACK);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int) value + " lbs";
            }
        });

        LineData lineData = new LineData(dataSet);
        chart.setExtraOffsets(5, 10, 30, 10);
        chart.setData(lineData);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        //chart.invalidate(); // refresh
        chart.animateY(3000, Easing.EaseInOutCubic);
    }
}
