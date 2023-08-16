package com.example.technical_english.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.technical_english.R;
import com.example.technical_english.database.Tudien;
import com.example.technical_english.database.tudienDatabase;

import java.util.ArrayList;
import java.util.List;

public class TudienAdapter extends ArrayAdapter<Tudien> {
    private boolean thu;
    List<Tudien> listdata=new ArrayList<>();
    List<Tudien> l=new ArrayList<>();
    public TudienAdapter(@NonNull Context context, int resource, @NonNull List<Tudien> objects) {
        super(context, resource, objects);
        if(context!=null&&objects!=null){
            l= new ArrayList<>(objects);
        }
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Tudien> lold= new ArrayList<>();
                if(charSequence==null|| charSequence.length()==0){
                    lold.addAll(l);
                }else {
                    String filter= charSequence.toString().toLowerCase().trim();
                    for(Tudien t: l){
                        if(t.getTu().toLowerCase().contains(filter) || t.getNghia().toLowerCase().contains(filter)){
                            lold.add(t);
                        }
                    }
                }
                FilterResults filterResults= new FilterResults();
                filterResults.values=lold;
                filterResults.count=lold.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                clear();
                addAll((List<Tudien>)filterResults.values);
                notifyDataSetChanged();
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((Tudien) resultValue).getTu();
            }
        };
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        thu=false;
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_tu2, parent, false);
        TextView tu = view.findViewById(R.id.tu_tim);
        Tudien tudien=getItem(position);
        TextView nghia = view.findViewById(R.id.nghia_tim);
        TextView loai=view.findViewById(R.id.loai_tim);
        Button tym= view.findViewById(R.id.tym2);
        tu.setText(tudien.getTu());
        nghia.setText(tudien.getNghia());
        loai.setText(tudien.getLoaitu());
        listdata= tudienDatabase.getInstance(parent.getContext()).data().getList();
        for(Tudien t: listdata){
            if((t.getMa().toLowerCase()).equals(tudien.getMa().toLowerCase())){
                thu=true;
            }
        }
        if (thu){
            tym.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
        }else {
            tym.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        }
        tym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tym.getBackgroundTintList()==ColorStateList.valueOf(Color.YELLOW)){
                    tudienDatabase.getInstance(view.getContext()).data().deleteTu(tudien.getMa());
                    Toast.makeText(parent.getContext(),"Delete Favorite",Toast.LENGTH_LONG).show();
                    tym.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                }else {
                    tudienDatabase.getInstance(view.getContext()).data().insert(tudien);
                    Toast.makeText(parent.getContext(),"Insert Favorite",Toast.LENGTH_LONG).show();
                    tym.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
                }
            }
        });
        return view;
    }
}
