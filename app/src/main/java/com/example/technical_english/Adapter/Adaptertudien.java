package com.example.technical_english.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.technical_english.R;
import com.example.technical_english.database.Tudien;
import com.example.technical_english.database.tudienDatabase;

import java.util.ArrayList;
import java.util.List;

public class Adaptertudien extends RecyclerView.Adapter<Adaptertudien.Mytudien> implements Filterable {
    private List<Tudien> tudiens;
    private List<Tudien> tudienList;
    private  List<Tudien> listdata;
    private Context context;
    boolean thu;
    public Adaptertudien(List<Tudien> tudiens,Context context) {
        this.tudiens = tudiens;
        this.tudienList = tudiens;
        this.context=context;
    }

    @NonNull
    @Override
    public Mytudien onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_tu2, parent, false);
        return new Mytudien(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Mytudien holder, int position) {
        Tudien tudien= tudienList.get(position);
        holder.tu.setText(tudien.getTu());
        thu=false;
        holder.nghia.setText(tudien.getNghia());
        holder.loai.setText(tudien.getLoaitu());
        listdata= tudienDatabase.getInstance(context).data().getList();
            for(Tudien t: listdata){
                if((t.getMa().toLowerCase()).equals(tudien.getMa().toLowerCase())){
                    thu=true;
                }
            }
            if (thu){
                holder.tym.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
            }else {
                holder.tym.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            }
        holder.tym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.tym.getBackgroundTintList()==ColorStateList.valueOf(Color.YELLOW)){
                    tudienDatabase.getInstance(view.getContext()).data().deleteTu(tudien.getMa());
                    Toast.makeText(context,"Delete Favorite",Toast.LENGTH_LONG).show();
                    holder.tym.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                }else {
                    tudienDatabase.getInstance(view.getContext()).data().insert(tudien);
                    Toast.makeText(context,"Insert Favorite",Toast.LENGTH_LONG).show();
                    holder.tym.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tudienList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchString = constraint.toString();
                if (searchString.isEmpty()) {
                    tudienList= new ArrayList<>(tudiens);
                } else {
                    List<Tudien> filteredList = new ArrayList<>();
                    for (Tudien item : tudiens) {
                        if (item.getTu().toLowerCase().contains(searchString.toLowerCase())
                                ||item.getNghia().toLowerCase().contains(searchString.toLowerCase())) {
                            filteredList.add(item);
                        }
                    }
                    tudienList= filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = tudienList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                tudienList = (List<Tudien>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class Mytudien extends RecyclerView.ViewHolder {
        TextView tu, nghia, loai;
        public Button tym;
        public Mytudien(@NonNull View itemView) {
            super(itemView);
            tu= itemView.findViewById(R.id.tu_tim);
            nghia=itemView.findViewById(R.id.nghia_tim);
            loai=itemView.findViewById(R.id.loai_tim);
            tym=itemView.findViewById(R.id.tym2);
        }
    }
}
