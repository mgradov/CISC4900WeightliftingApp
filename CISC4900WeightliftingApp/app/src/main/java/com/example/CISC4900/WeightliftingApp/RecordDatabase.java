package com.example.CISC4900.WeightliftingApp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Record.class, version = 2)
@TypeConverters({Converters.class})
public abstract class RecordDatabase extends RoomDatabase {

    private static RecordDatabase instance;

    public abstract RecordDao recordDao();

    public static synchronized RecordDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),RecordDatabase.class, "record_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
