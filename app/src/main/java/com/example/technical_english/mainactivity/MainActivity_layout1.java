package com.example.technical_english.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.technical_english.Adapter.Adaptertudien;
import com.example.technical_english.R;
import com.example.technical_english.database.Tudien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_layout1 extends AppCompatActivity implements SearchView.OnQueryTextListener {
    Toolbar toolbar;
    SearchView tratu;
    RecyclerView recyclerView;
    Adaptertudien adaptertudien;
    List<Tudien> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout1);
        toolbar=findViewById(R.id.toolbar);
        tratu=findViewById(R.id.tutra);
        ActionToolbar();
        tratu.setOnQueryTextListener(this);
        recyclerView=findViewById(R.id.tudien_tra);
        adaptertudien= new Adaptertudien(list,MainActivity_layout1.this);
        recyclerView.setAdapter(adaptertudien);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lay();
    }
    //
    private void lay() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_english");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Tudien user = dataSnapshot.getValue(Tudien.class);
                    list.add(user);
                }
                adaptertudien.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity_layout1.this, "dai vuong oi", Toast.LENGTH_LONG).show();
            }
        });
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