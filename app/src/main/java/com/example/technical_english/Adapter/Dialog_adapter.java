package com.example.technical_english.Adapter;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.technical_english.R;

public class Dialog_adapter extends Dialog {
    public Dialog_adapter(@NonNull Context context) {
        super(context);
        setContentView(R.layout.layout_loading);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }
}
