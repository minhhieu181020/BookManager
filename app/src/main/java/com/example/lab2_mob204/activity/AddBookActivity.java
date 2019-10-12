package com.example.lab2_mob204.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab2_mob204.R;
import com.example.lab2_mob204.dao.BookDAO;
import com.example.lab2_mob204.model.Book;

public class AddBookActivity extends AppCompatActivity {
private EditText edMaSach,edTieuDe,edTacGia,edNXB,edSoluong,edMaTheLoai,edThanhTien;
private BookDAO bookDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        setTitle("Them Sach");
        edMaSach=findViewById(R.id.edMaSach);
        edMaTheLoai=findViewById(R.id.edMaTheLoai);
        edNXB=findViewById(R.id.edNXB);
        edTacGia=findViewById(R.id.edTacgia);
        edThanhTien=findViewById(R.id.edThanhTien);
        edSoluong=findViewById(R.id.edSoLuong);
        edTieuDe=findViewById(R.id.edTieuDe);

    }

    public void openListBook(View view) {
        Intent intent=new Intent(AddBookActivity.this, BookActivity.class);
        startActivity(intent);
    }

    public void addNewBook(View view) {

        BookDAO bookDAO = new BookDAO(AddBookActivity.this);
        String masach=edMaSach.getText().toString();
        String matheloai=edMaTheLoai.getText().toString();
        String tieude=edTieuDe.getText().toString();
        String tacgia=edTacGia.getText().toString();
        String nhaxuatban=edNXB.getText().toString();
        String soluong=edSoluong.getText().toString();
        String thanhtien=edThanhTien.getText().toString();

        Book book=new Book(masach,matheloai,tieude,tacgia,nhaxuatban,soluong,thanhtien);

        if (bookDAO.insertBook(book)){

            Toast.makeText(this, " Them Thanh cong", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Them That bai", Toast.LENGTH_SHORT).show();

        }
        }
    }

