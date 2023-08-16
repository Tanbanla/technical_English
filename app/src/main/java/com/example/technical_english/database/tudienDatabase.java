package com.example.technical_english.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Tudien.class},version = 1)
public  abstract class tudienDatabase extends RoomDatabase {
    private static final String data_name="tudien.db";
    private  static tudienDatabase instance;
    public static synchronized tudienDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),tudienDatabase.class,data_name)
                    .allowMainThreadQueries()
                    .build();
        }
        return  instance;
    }
    public  abstract database data();
}
