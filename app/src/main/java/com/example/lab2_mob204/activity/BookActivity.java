package com.example.lab2_mob204.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lab2_mob204.R;
import com.example.lab2_mob204.adapter.BookAdapter;
import com.example.lab2_mob204.adapter.UserAdapter;
import com.example.lab2_mob204.dao.BookDAO;
import com.example.lab2_mob204.dao.UserDAO;
import com.example.lab2_mob204.model.Book;
import com.example.lab2_mob204.model.User;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    private BookDAO bookDAO;
    private RecyclerView rvListBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        rvListBook = findViewById(R.id.rvBook);
//        Book book=new Book("tt","tt","tt","tt","tt","tt","tt");

        setTitle("Book");
        bookDAO = new BookDAO(BookActivity.this);

        List<Book> bookList = bookDAO.selectBook();

//      Log.e("bookList", bookList.size() + "");
//       bookList.add(book);
//        for (int i=0;i<bookList.size();i++) {
//            Log.e("abc", bookList.get(i).getMasach());
//        }
        BookAdapter bookAdapter = new BookAdapter(BookActivity.this,bookList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((BookActivity.this));
        rvListBook.setLayoutManager(linearLayoutManager);
        rvListBook.setAdapter(bookAdapter);
    }


    public void openAddBook(View view) {
        Intent intent=new Intent(BookActivity.this,AddBookActivity.class);
        startActivity(intent);
    }
}
