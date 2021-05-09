package com.example.CISC4900.WeightliftingApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.List;

public class LogFragment extends Fragment {

    public static final int ADD_RECORD_REQUEST = 1;
    public static final int EDIT_RECORD_REQUEST = 2;
    public static final int SEARCH_REQUEST = 3;

    private RecordViewModel recordViewModel;

    final RecordAdapter adapter = new RecordAdapter();

    Record tempRecord;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        /*toolbar = getView().findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Exercise Log");*/
        setHasOptionsMenu(true);

        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        /*DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);*/

        recyclerView.setAdapter(adapter);

        recordViewModel = new ViewModelProvider(this).get(RecordViewModel.class);
        recordViewModel.getAllRecords().observe(this, new Observer<List<Record>>() {
            @Override
            public void onChanged(@Nullable List<Record> records) {
                adapter.submitList(records);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                tempRecord = adapter.getRecordAt(viewHolder.getAdapterPosition());
                recordViewModel.delete(tempRecord);
                //recordViewModel.delete(adapter.getRecordAt(viewHolder.getAdapterPosition()));
                //Toast.makeText(getActivity(), "Exercise deleted", Toast.LENGTH_SHORT).show();
                Snackbar.make(getView().findViewById(R.id.rootLayout),
                        getString(R.string.action_delete_item),
                        Snackbar.LENGTH_LONG)
                        .setAction(R.string.action_undo, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                recordViewModel.insert(tempRecord);
                            }
                        })
                        .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                super.onDismissed(transientBottomBar, event);
                                tempRecord = null;
                            }
                        })
                        .show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new RecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Record record) {
                Intent intent = new Intent(getActivity(), AddEditExerciseActivity.class);
                intent.putExtra(AddEditExerciseActivity.EXTRA_ID, record.getId());
                intent.putExtra(AddEditExerciseActivity.EXTRA_DATE, record.getDate());
                intent.putExtra(AddEditExerciseActivity.EXTRA_EXERCISE, record.getExercise());
                intent.putExtra(AddEditExerciseActivity.EXTRA_WEIGHT, record.getWeight());
                intent.putExtra(AddEditExerciseActivity.EXTRA_SETS, record.getSets());
                intent.putExtra(AddEditExerciseActivity.EXTRA_REPS, record.getReps());
                startActivityForResult(intent, EDIT_RECORD_REQUEST);
            }
        });

        /*Intent getData = getIntent();
        String date = getData.getStringExtra("date");
        String exercise = getData.getStringExtra("exercise");
        int weight = getData.getIntExtra("weight", 1);
        int sets = getData.getIntExtra("sets", 1);
        int reps = getData.getIntExtra("reps", 1);
        Record record = new Record(date, exercise, weight, sets, reps);
        recordViewModel.insert(record);*/

        /*Intent getData = getIntent();
        Record record = (Record) getData.getSerializableExtra("EXTRA_RECORD");
        recordViewModel.insert(record);*/

        /*String[] exercises = getResources().getStringArray(R.array.exercises);
        for (String s: exercises) {
            Record test = new Record("", s, 135, 4, 8);
            recordViewModel.insert(test);
        }*/

        /*for (char i = 0; i < 1000; i++) {
            Record test = new Record("", String.valueOf(i), 1, 2, 3);
            recordViewModel.insert(test);
        }*/

        //recordViewModel.deleteAllRecords();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_log_activity, menu);
        return true;
    }*/

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_log_activity, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.recordExercise:
                Intent addIntent = new Intent(getActivity(), AddEditExerciseActivity.class);
                startActivityForResult(addIntent, ADD_RECORD_REQUEST);
                return true;
            case R.id.search:
                Intent searchIntent = new Intent(getActivity(), SearchActivity.class);
                startActivityForResult(searchIntent, SEARCH_REQUEST);
                return true;
            case R.id.deleteAllRecords:
                recordViewModel.deleteAllRecords();
                Toast.makeText(getActivity(), "All exercises deleted", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.chart:
                startActivity(new Intent(getActivity(), ChartActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_RECORD_REQUEST && resultCode == Activity.RESULT_OK) {

            Date date = new Date(data.getLongExtra(AddEditExerciseActivity.EXTRA_DATE, 1));
            String exercise = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE);
            int weight = data.getIntExtra(AddEditExerciseActivity.EXTRA_WEIGHT, 1);
            int sets = data.getIntExtra(AddEditExerciseActivity.EXTRA_SETS, 1);
            int reps = data.getIntExtra(AddEditExerciseActivity.EXTRA_REPS, 1);

            Record record = new Record(date, exercise, weight, sets, reps);
            recordViewModel.insert(record);

            Toast.makeText(getActivity(), "Exercise saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_RECORD_REQUEST && resultCode == Activity.RESULT_OK) {
            int id = data.getIntExtra(AddEditExerciseActivity.EXTRA_ID, -1);
            System.out.println(id);

            if (id == -1) {
                Toast.makeText(getActivity(), "Exercise can not be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            Date date = new Date(data.getLongExtra(AddEditExerciseActivity.EXTRA_DATE, 1));
            String exercise = data.getStringExtra(AddEditExerciseActivity.EXTRA_EXERCISE);
            int weight = data.getIntExtra(AddEditExerciseActivity.EXTRA_WEIGHT, 1);
            int sets = data.getIntExtra(AddEditExerciseActivity.EXTRA_SETS, 1);
            int reps = data.getIntExtra(AddEditExerciseActivity.EXTRA_REPS, 1);

            Record record = new Record(date, exercise, weight, sets, reps);
            record.setId(id);
            recordViewModel.update(record);

            Toast.makeText(getActivity(), "Exercise updated", Toast.LENGTH_SHORT).show();
        } else if (requestCode == SEARCH_REQUEST && resultCode == Activity.RESULT_OK) {
            Date dateFrom = new Date(data.getLongExtra(SearchActivity.EXTRA_DATE_FROM, 1));
            Date dateTo = new Date(data.getLongExtra(SearchActivity.EXTRA_DATE_TO, 1));
            recordViewModel.getSelectedRecords(dateFrom.getTime(), dateTo.getTime()).observe(this, new Observer<List<Record>>() {
                @Override
                public void onChanged(@Nullable List<Record> records) {
                    adapter.submitList(records);
                }
            });
        } else {
            Toast.makeText(getActivity(), "Exercise not saved", Toast.LENGTH_SHORT).show();
        }
    }

    /*db = Room.databaseBuilder(getApplicationContext(), RecordDatabase.class, "record_database").allowMainThreadQueries().build();
        Intent intent = getIntent();
        Record record = (Record)intent.getSerializableExtra("EXTRA_RECORD");
        db.recordDao().insert(record);
        List<Record> records = db.recordDao().getAllRecords();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //layoutManager = new LinearLayoutManager(this);
        adapter = new RecordAdapter(records);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);*/
}

