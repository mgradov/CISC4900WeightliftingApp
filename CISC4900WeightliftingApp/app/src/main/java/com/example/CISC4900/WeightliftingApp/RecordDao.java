package com.example.CISC4900.WeightliftingApp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecordDao {

    @Insert
    void insert(Record record);

    @Update
    void update(Record record);

    @Delete
    void delete(Record record);

    @Query("DELETE FROM record_table")
    void deleteAllRecords();

    @Query("SELECT * FROM record_table ORDER BY date DESC, exercise ASC, weight DESC")
    LiveData<List<Record>> getAllRecords();

    @Query("SELECT * FROM record_table ORDER BY date ASC, exercise DESC, weight ASC")
    List<Record> getAllRecordsTest();

    @Query("SELECT * FROM record_table WHERE date BETWEEN :start AND :end ORDER BY date DESC, exercise ASC, weight DESC")
    LiveData<List<Record>> getSelectedRecords(long start,long end);
}
