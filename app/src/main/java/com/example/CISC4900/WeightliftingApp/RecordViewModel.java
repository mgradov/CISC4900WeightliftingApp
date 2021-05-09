package com.example.CISC4900.WeightliftingApp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {
    private RecordRepository repository;
    private LiveData<List<Record>> allRecords;
    private LiveData<List<Record>> selectedRecords;

    public RecordViewModel(@NonNull Application application) {
        super(application);
        repository = new RecordRepository(application);
        allRecords = repository.getAllRecords();
    }

    public void insert(Record record) {
        repository.insert(record);
    }

    public void update(Record record) {
        repository.update(record);
    }

    public void delete(Record record) {
        repository.delete(record);
    }

    public void deleteAllRecords() {
        repository.deleteAllRecords();
    }

    public LiveData<List<Record>> getAllRecords() {
        return allRecords;
    }

    public LiveData<List<Record>> getSelectedRecords(long start, long end) {
        return repository.getSelectedRecords(start, end);
    }
}