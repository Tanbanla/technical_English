package com.example.technical_english.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface database {
    @Insert
    void insert(Tudien tudien);
    @Query("SELECT*FROM tudien")
    List<Tudien> getList();
    @Delete
    void delete(Tudien tudien);
    @Query("DELETE FROM tudien WHERE ma = :tu")
    void deleteTu(String tu);
}
