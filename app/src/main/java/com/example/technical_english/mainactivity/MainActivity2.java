package com.example.technical_english.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.technical_english.Adapter.Dialog_adapter;
import com.example.technical_english.Adapter.TudienAdapter;
import com.example.technical_english.database.Tudien;
import com.example.technical_english.fragment.Fragment_favourite;
import com.example.technical_english.R;
import com.example.technical_english.fragment.Fragment_home;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private Dialog_adapter dialog_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        dialog_adapter = new Dialog_adapter(this);
        dialog_adapter.show();
        if(isNetworkAvailable(this)){
            lay();
        }else{
            dialog_adapter.dismiss();
            Toast.makeText(MainActivity2.this,"KHÔNG CÓ KẾT NỐI INTERNET",Toast.LENGTH_LONG).show();
        }
        replaceFragament(new Fragment_home());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFragament(new Fragment_home());
                        break;
                    case R.id.favourite:
                        replaceFragament(new Fragment_favourite());
                        break;
                    case R.id.caidat:
                        replaceFragament(new MainActivity_setting());

                }
                return true;
            }
        });
    }
    private  void replaceFragament(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }
    private void lay() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_english");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Dữ liệu đã được load xong
                    // Xử lý dữ liệu ở đây
                    // Khi bạn muốn ẩn dialog loading:
                    dialog_adapter.dismiss();
                } else {
                    Toast.makeText(MainActivity2.this,"Đéo yêu anh Vũ Quang Cường thì k có đâu",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkCapabilities capabilities = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            }
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    // Thiết bị có kết nối Internet
                    return true;
                }
            }
        }
        // Thiết bị không có kết nối Internet
        return false;
    }
}