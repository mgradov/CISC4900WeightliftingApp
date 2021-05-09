package com.example.CISC4900.WeightliftingApp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordRepository {
    private RecordDao recordDao;
    private LiveData<List<Record>> allRecords;
    private LiveData<List<Record>> selectedRecords;

    public RecordRepository(Application application) {
        RecordDatabase database = RecordDatabase.getInstance(application);
        recordDao = database.recordDao();
        allRecords = recordDao.getAllRecords();
    }

    public void insert(Record record) {
        new InsertRecordAsyncTask(recordDao).execute(record);
    }

    public void update(Record record) {
        new UpdateRecordAsyncTask(recordDao).execute(record);
    }

    public void delete(Record record){
        new DeleteRecordAsyncTask(recordDao).execute(record);
    }

    public void deleteAllRecords(){
        new DeleteAllRecordsAsyncTask(recordDao).execute();
    }

    public LiveData<List<Record>> getAllRecords(){
        return allRecords;
    }

    public LiveData<List<Record>> getSelectedRecords(long start, long end){
        return selectedRecords = recordDao.getSelectedRecords(start, end);
    }

    private static class InsertRecordAsyncTask extends AsyncTask<Record, Void, Void> {
        private RecordDao recordDao;

        private InsertRecordAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.insert(records[0]);
            return null;
        }
    }

    private static class UpdateRecordAsyncTask extends AsyncTask<Record, Void, Void> {
        private RecordDao recordDao;

        private UpdateRecordAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.update(records[0]);
            return null;
        }
    }

    private static class DeleteRecordAsyncTask extends AsyncTask<Record, Void, Void> {
        private RecordDao recordDao;

        private DeleteRecordAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            recordDao.delete(records[0]);
            return null;
        }
    }

    private static class DeleteAllRecordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private RecordDao recordDao;

        private DeleteAllRecordsAsyncTask(RecordDao recordDao) {
            this.recordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recordDao.deleteAllRecords();
            return null;
        }
    }

}
