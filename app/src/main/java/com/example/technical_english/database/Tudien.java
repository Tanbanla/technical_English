package com.example.technical_english.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "tudien")
public class Tudien {
    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    @PrimaryKey(autoGenerate = true)
    private  int k;
    private String ma;
    public Tudien(String ma, String tu, String nghia, String loaitu) {
        this.ma = ma;
        this.tu = tu;
        this.nghia = nghia;
        this.loaitu = loaitu;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
    }

    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }

    public String getLoaitu() {
        return loaitu;
    }

    public void setLoaitu(String loaitu) {
        this.loaitu = loaitu;
    }


    private String tu;

    private String nghia;
    private String loaitu;
    public Tudien(){

    }
    @Override
    public  String toString(){
        return tu;
    }
}
