package com.example.lab2_mob204.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lab2_mob204.R;
import com.example.lab2_mob204.adapter.TypeBookApdapter;
import com.example.lab2_mob204.dao.TypeBookDAO;
import com.example.lab2_mob204.model.TypeBook;

import java.util.List;

public class TheloaiActivity extends AppCompatActivity {
private TypeBookDAO typeBookDAO;
private RecyclerView rvType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloai);
        rvType=findViewById(R.id.rvTypeBook);
        setTitle("Type Book");

        typeBookDAO=new TypeBookDAO(TheloaiActivity.this);
        List<TypeBook> typeBookList=typeBookDAO.selectTypeBook();

        TypeBookApdapter typeBookApdapter=new TypeBookApdapter(TheloaiActivity.this,typeBookList);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(TheloaiActivity.this);
        rvType.setLayoutManager(linearLayoutManager);
        rvType.setAdapter(typeBookApdapter);
    }

    public void openListtheloai(View view) {
        Intent intent=new Intent(TheloaiActivity.this, TheloaiActivity.class);
        startActivity(intent);
    }

    public void openAddTypeBook(View view) {
        Intent intent=new Intent(TheloaiActivity.this, AddTypeBookActivity.class);
        startActivity(intent);
    }
}
