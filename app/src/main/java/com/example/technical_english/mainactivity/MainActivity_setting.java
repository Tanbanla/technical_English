package com.example.technical_english.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.technical_english.R;

import org.w3c.dom.Text;

public class MainActivity_setting extends Fragment {
    TextView gt, cs;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_main_setting, container, false);
        gt=view.findViewById(R.id.gioithieu);
        cs=view.findViewById(R.id.chinhsach);
        gt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gioithieu("Giới thiệu"
                        ," Ứng dụng tiếng Anh chuyên ngành cho khoa may là một phần mềm hỗ trợ học tập và nâng cao kỹ năng tiếng Anh cho sinh viên và giảng viên ngành may. " ,
                                " Ứng dụng cung cấp các bài học, bài tập, từ điển và video liên quan đến các chủ đề như vải, thiết kế, may mặc, kiểm tra chất lượng và xuất nhập khẩu." ,
                                " Ứng dụng giúp người dùng nắm vững các thuật ngữ chuyên ngành, cải thiện khả năng giao tiếp và đọc hiểu các tài liệu tiếng Anh trong lĩnh vực may." ,
                                " Ứng dụng có giao diện thân thiện, dễ sử dụng và có thể hoạt động trên nhiều thiết bị như máy tính, điện thoại và máy tính bảng.");
            }
        });
        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Chúc bạn may mắn lần sau",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
    //    // dialog
    private void gioithieu(String td,String nd,String nd1,String nd2,String nd3){
        final Dialog dialog= new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        Window window= dialog.getWindow();
        if(window== null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes= window.getAttributes();
        windowAttributes.gravity= Gravity.BOTTOM;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(true);
        // anh xa dialog
        TextView tieude= dialog.findViewById(R.id.tieude);
        TextView noidung=dialog.findViewById(R.id.noidung);
        TextView noidung1=dialog.findViewById(R.id.noidung1);
        TextView noidung2=dialog.findViewById(R.id.noidung2);
        TextView noidung3=dialog.findViewById(R.id.noidung3);
        Button nhan=dialog.findViewById(R.id.bt_dialog);
        tieude.setText(td);
        noidung.setText(nd);
        noidung1.setText(nd1);
        noidung2.setText(nd2);
        noidung3.setText(nd3);
        nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}