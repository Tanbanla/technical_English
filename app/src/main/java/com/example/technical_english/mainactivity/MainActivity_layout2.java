package com.example.technical_english.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.technical_english.Adapter.ExpandableListAdapter;
import com.example.technical_english.R;
import com.example.technical_english.database.Group_tu;
import com.example.technical_english.database.Tudien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity_layout2 extends AppCompatActivity {
    Toolbar toolbar;
    private ExpandableListAdapter expandableListAdapter;
    private List<Group_tu> groupTuList;
    private Map<Group_tu,List<Tudien>> groupTuListMap;
    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout2);
        toolbar=findViewById(R.id.toolbardichvb);
        ActionToolbar();
        getId();
        groupTuListMap=getData();
        groupTuList=new ArrayList<>(groupTuListMap.keySet());
        expandableListAdapter=new ExpandableListAdapter(groupTuList,groupTuListMap);
        expandableListView.setAdapter(expandableListAdapter);

    }
    // xu ly toolbar
    private void ActionToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    // get id
    private void getId(){
        expandableListView=findViewById(R.id.elistview);
    }
    // xu ly du lieu
    private Map<Group_tu,List<Tudien>> getData(){
        Map<Group_tu,List<Tudien>> listMap=new HashMap<>();
        Group_tu groupTu= new Group_tu(1,"Quần","https://cdn-icons-png.flaticon.com/128/3345/3345597.png");
        Group_tu groupTu1= new Group_tu(2,"Áo","https://cdn-icons-png.flaticon.com/128/2503/2503380.png");
        Group_tu groupTu2= new Group_tu(3,"Tính chất vật liệu may","https://t4.ftcdn.net/jpg/03/92/99/77/240_F_392997782_PKS9HpAYfUlG15le7PRSwnuMcdKcyVkE.jpg");
        Group_tu groupTu3= new Group_tu(4,"Thời trang","https://cdn-icons-png.flaticon.com/128/1198/1198307.png");
        Group_tu groupTu4= new Group_tu(5,"Các đường may","https://cdn-icons-png.flaticon.com/128/4301/4301450.png");
        Group_tu groupTu5= new Group_tu(6,"Nguyên phụ, liệu","https://cdn-icons-png.flaticon.com/128/3813/3813693.png");
        Group_tu groupTu6= new Group_tu(7,"Thiết bị","https://cdn-icons-png.flaticon.com/128/5508/5508060.png");
        Group_tu groupTu7= new Group_tu(8,"Đơn hàng","https://cdn-icons-png.flaticon.com/128/3045/3045670.png");
        Group_tu groupTu8= new Group_tu(9,"Thông số kỹ thuật","https://cdn-icons-png.flaticon.com/128/10208/10208057.png");
        Group_tu groupTu9= new Group_tu(10,"Lĩnh vực may mặc","https://cdn-icons-png.flaticon.com/128/1867/1867627.png");
        Group_tu groupTu10= new Group_tu(11,"Xử lý nguyên phụ liệu","https://cdn-icons-png.flaticon.com/128/2099/2099941.png");
        Group_tu groupTu11= new Group_tu(12,"Thuật ngữ viết tắt","https://cdn-icons-png.flaticon.com/128/5104/5104087.png");
        //
        List<Tudien>t= new ArrayList<>();
        List<Tudien>t1= new ArrayList<>();
        List<Tudien>t2= new ArrayList<>();
        List<Tudien>t3= new ArrayList<>();
        List<Tudien>t4= new ArrayList<>();
        List<Tudien>t5= new ArrayList<>();
        List<Tudien>t6= new ArrayList<>();
        List<Tudien>t7= new ArrayList<>();
        List<Tudien>t8= new ArrayList<>();
        List<Tudien>t9= new ArrayList<>();
        List<Tudien>t10= new ArrayList<>();
        List<Tudien>t11= new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_english");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Tudien> tudiens= new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Tudien tudien = dataSnapshot.getValue(Tudien.class);
                    tudiens.add(tudien);
                }
                if(tudiens!=null){
                    for(int i=0;i<tudiens.size();i++){
                        if(tudiens.get(i).getLoaitu().contains("Quần")){
                            t.add(tudiens.get(i));
                        }else if(tudiens.get(i).getLoaitu().contains("Áo")){
                            t1.add(tudiens.get(i));
                        }else if(tudiens.get(i).getLoaitu().contains("Tính chất vật liệu may")){
                            t2.add(tudiens.get(i));
                        }else if(tudiens.get(i).getLoaitu().contains("Thời trang")){
                            t3.add(tudiens.get(i));
                        }else if(tudiens.get(i).getLoaitu().contains("Các đường may")){
                            t4.add(tudiens.get(i));
                        }else if(tudiens.get(i).getLoaitu().contains("Nguyên phụ, liệu")){
                            t5.add(tudiens.get(i));
                        }else if(tudiens.get(i).getLoaitu().contains("Thiết bị")){
                            t6.add(tudiens.get(i));
                        }else if(tudiens.get(i).getLoaitu().contains("Đơn hàng")){
                            t7.add(tudiens.get(i));
                        }else if(tudiens.get(i).getLoaitu().contains("Thông số KT")){
                            t8.add(tudiens.get(i));
                        }else if(tudiens.get(i).getLoaitu().contains("Lĩnh vực may mặc")){
                            t9.add(tudiens.get(i));
                        }else if(tudiens.get(i).getLoaitu().contains("Xử lí nguyên phụ liệu")) {
                            t10.add(tudiens.get(i));
                        }else {
                            t11.add(tudiens.get(i));
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity_layout2.this, "dai vuong oi", Toast.LENGTH_LONG).show();
            }
        });
        listMap.put(groupTu,t);
        listMap.put(groupTu1,t1);
        listMap.put(groupTu2,t2);
        listMap.put(groupTu3,t3);
        listMap.put(groupTu4,t4);
        listMap.put(groupTu5,t5);
        listMap.put(groupTu6,t6);
        listMap.put(groupTu7,t7);
        listMap.put(groupTu8,t8);
        listMap.put(groupTu9,t9);
        listMap.put(groupTu10,t10);
        listMap.put(groupTu11,t11);
        return listMap;
    }
}