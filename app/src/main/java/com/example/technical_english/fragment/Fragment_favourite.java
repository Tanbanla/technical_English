package com.example.technical_english.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.technical_english.Adapter.Adaptertudien;
import com.example.technical_english.R;
import com.example.technical_english.database.Tudien;
import com.example.technical_english.database.tudienDatabase;

import java.util.ArrayList;
import java.util.List;


public class Fragment_favourite extends Fragment  implements SearchView.OnQueryTextListener{
    SearchView searchView;
    RecyclerView recyclerView;
    List<Tudien> list= new ArrayList<>();
    Adaptertudien adaptertudien;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favourite, container, false);
        searchView=view.findViewById(R.id.tutra_fw);
        searchView.setOnQueryTextListener(this);
        recyclerView=view.findViewById(R.id.rv_fw);
        list= tudienDatabase.getInstance(getContext()).data().getList();
        adaptertudien= new Adaptertudien(list, container.getContext());
        recyclerView.setAdapter(adaptertudien);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        adaptertudien.getFilter().filter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adaptertudien.getFilter().filter(s);
        return false;
    }
}