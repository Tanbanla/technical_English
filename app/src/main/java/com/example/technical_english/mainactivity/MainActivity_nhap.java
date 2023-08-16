package com.example.technical_english.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.technical_english.R;
import com.example.technical_english.database.Tudien;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity_nhap extends AppCompatActivity {
    EditText tu,nghia;
    Button nhap;
    int ma=1311;
    String loai;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nhap);
        getId();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Xử lý sự kiện khi người dùng chọn một tùy chọn từ Spinner
                loai=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý sự kiện khi người dùng không chọn bất kỳ tùy chọn nào từ Spinner
            }
        });
        nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a=tu.getText().toString();
                String b=nghia.getText().toString();
                Tudien tudien=new Tudien(""+ma,a,b,loai);
                day(tudien);
                ma++;
                Toast.makeText(MainActivity_nhap.this,"ok: "+ma,Toast.LENGTH_LONG).show();
                tu.setText("");
                nghia.setText("");
            }
        });
    }
    private void getId(){
        tu=findViewById(R.id.tunhap);
        nghia=findViewById(R.id.nghianhap);
        spinner=findViewById(R.id.spinner);
        nhap =findViewById(R.id.nhapfire);
        String[] chon={"Thuật ngữ viết tắt"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,chon);
        spinner.setAdapter(adapter);

    }
    // firebase
    private void day(Tudien tudien) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_english");
        String p = String.valueOf(tudien.getMa());
        myRef.child(p).setValue(tudien, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
            }
        });
    }
}