package com.example.lab2_mob204.adapter;

import android.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_mob204.R;

class UserHloder extends RecyclerView.ViewHolder {
    public TextView tvText1, tvText2;
    public ImageView imgXoa;
    public UserHloder(@NonNull View itemView) {
        super(itemView);
        tvText1 = itemView.findViewById(R.id.tvText1);
        tvText2 = itemView.findViewById(R.id.tvText2);
        imgXoa= itemView.findViewById(R.id.imgXoa);

    }
}
