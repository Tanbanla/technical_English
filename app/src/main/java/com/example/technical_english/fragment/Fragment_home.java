package com.example.technical_english.fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.technical_english.Adapter.TudienAdapter;
import com.example.technical_english.R;
import com.example.technical_english.database.Tudien;
import com.example.technical_english.database.tudienDatabase;
import com.example.technical_english.mainactivity.MainActivity_layout1;
import com.example.technical_english.mainactivity.MainActivity_layout2;
import com.example.technical_english.mainactivity.MainActivity_nhap;
import com.example.technical_english.mainactivity.MainActivity_setting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Fragment_home extends Fragment {
    private static final int REQUEST_CODE_SPEECH_INPUT =1000 ;
    TextView traVE,dichvb,tvnhap;
    private AutoCompleteTextView autoCompleteTextView;
    ImageView home_mic;
    private List<Tudien>l= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        traVE=view.findViewById(R.id.tra_viet_anh);
        dichvb=view.findViewById(R.id.dichvb);
        autoCompleteTextView=view.findViewById(R.id.autoComplete);
        home_mic=view.findViewById(R.id.home_mic);
        tvnhap=view.findViewById(R.id.textView2);
        traVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MainActivity_layout2.class);
                startActivity(intent);
            }
        });
        dichvb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MainActivity_layout1.class);
                startActivity(intent);
            }
        });
        home_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
        //
        //
        //đến giao diện nhập
//        tvnhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent2=new Intent(getContext(), MainActivity_nhap.class);
//                startActivity(intent2);
//            }
//        });
        lay();
        return view;
    }
    // noi chuyen chuyen thanh van ban
    private void speak(){
        Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Đang nghe");

        try{
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
        }catch (Exception e){
            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // recevie voice input and handle it
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if( null!=data){
                    // get text array from vocie intent
                    ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(getContext(), ""+result, Toast.LENGTH_SHORT).show();
                    // set to text view
                    autoCompleteTextView.setText(result.get(0),true);
                }
                break;
            }
        }
    }
    private void lay() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_english");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Tudien tudien = dataSnapshot.getValue(Tudien.class);
                    l.add(tudien);
                }
                TudienAdapter adapter =new TudienAdapter(getContext(), R.layout.layout_item_tu2,l);
                autoCompleteTextView.setAdapter(adapter);
                autoCompleteTextView.setThreshold(1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Nỗi kết nối", Toast.LENGTH_LONG).show();
            }
        });
    }
}