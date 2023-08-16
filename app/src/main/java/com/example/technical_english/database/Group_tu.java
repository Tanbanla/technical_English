package com.example.technical_english.database;

public class Group_tu {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    private String hinhanh;
    public Group_tu(int id, String name,String hinhanh) {
        this.id = id;
        this.name = name;
        this.hinhanh=hinhanh;
    }
}
