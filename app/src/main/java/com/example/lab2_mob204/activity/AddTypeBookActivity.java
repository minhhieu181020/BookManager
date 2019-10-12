package com.example.lab2_mob204.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab2_mob204.R;
import com.example.lab2_mob204.dao.TypeBookDAO;
import com.example.lab2_mob204.model.TypeBook;

public class AddTypeBookActivity extends AppCompatActivity {
private EditText edMaTheLoai,edTenTheLoai,edMoTa,edViTri;
private TypeBookDAO typeBookDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type_book);
        edMaTheLoai=findViewById(R.id.edMaTheLoaiType);
        edTenTheLoai=findViewById(R.id.edTenTheLoai);
        edMoTa=findViewById(R.id.edMoTa);
        edViTri=findViewById(R.id.edViTri);

    }

    public void AddNewType(View view) {
       typeBookDAO=new TypeBookDAO(AddTypeBookActivity.this);

       String matheloaitype=edMaTheLoai.getText().toString();
       String tentheloai1=edTenTheLoai.getText().toString();
       String mota2=edMoTa.getText().toString();
       String vitri3=edViTri.getText().toString();

        TypeBook typeBook=new TypeBook(matheloaitype,tentheloai1,mota2,vitri3);

        if (typeBookDAO.insertTypeBook(typeBook)){
            Toast.makeText(this, " Them Thanh cong", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Them That bai", Toast.LENGTH_SHORT).show();

        }

    }

    public void openListtheloai(View view) {
        Intent intent=new Intent(AddTypeBookActivity.this, TheloaiActivity.class);
        startActivity(intent);
    }
}
