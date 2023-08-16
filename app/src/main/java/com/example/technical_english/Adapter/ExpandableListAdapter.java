package com.example.technical_english.Adapter;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.technical_english.R;
import com.example.technical_english.database.Group_tu;
import com.example.technical_english.database.Tudien;
import com.example.technical_english.database.tudienDatabase;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private List<Group_tu> groupTuList;
    private Map<Group_tu,List<Tudien>> groupTuListMap;
    private  List<Tudien> listdata;
    boolean thu;

    public ExpandableListAdapter(List<Group_tu> groupTuList, Map<Group_tu, List<Tudien>> groupTuListMap) {
        this.groupTuList = groupTuList;
        this.groupTuListMap = groupTuListMap;
    }

    @Override
    public int getGroupCount() {
        if(groupTuList!= null){
            return groupTuList.size();
        }
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        if(groupTuListMap!=null && groupTuList!=null){
            return groupTuListMap.get(groupTuList.get(i)).size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return groupTuList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return groupTuListMap.get(groupTuList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        Group_tu group_tu=groupTuList.get(i);
        return group_tu.getId();
    }

    @Override
    public long getChildId(int i, int i1) {
        Tudien tudien= groupTuListMap.get(groupTuList.get(i)).get(i1);
        return Integer.parseInt(tudien.getMa());
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_group,viewGroup,false);
        }
        TextView tvgruop= view.findViewById(R.id.group);
        Group_tu group_tu=groupTuList.get(i);
        tvgruop.setText(group_tu.getName());
        ImageView imageView= view.findViewById(R.id.image_group);
        // chuyen anh
        Glide.with(viewGroup.getContext()).load(group_tu.getHinhanh()).into(imageView);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view==null){
            view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_tu,viewGroup,false);
        }
        TextView tu=view.findViewById(R.id.tu_group);
        TextView nghia=view.findViewById(R.id.nghia_group);
        Button tym= view.findViewById(R.id.tym1);
        Tudien tudien=groupTuListMap.get(groupTuList.get(i)).get(i1);
        listdata= tudienDatabase.getInstance(viewGroup.getContext()).data().getList();
        thu=false;
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
        tym.setOnClickListener(v -> {
            if(tym.getBackgroundTintList()==ColorStateList.valueOf(Color.YELLOW)){
                tudienDatabase.getInstance(v.getContext()).data().deleteTu(tudien.getMa());
                Toast.makeText(v.getContext(),"Delete Favorite",Toast.LENGTH_LONG).show();
                tym.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            }else {
                tudienDatabase.getInstance(v.getContext()).data().insert(tudien);
                Toast.makeText(v.getContext(),"Insert Favorite",Toast.LENGTH_LONG).show();
                tym.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
            }
        } );
        tu.setText(tudien.getTu());
        nghia.setText(tudien.getNghia());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
