package com.example.lab2_mob204.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lab2_mob204.R;
import com.example.lab2_mob204.adapter.UserAdapter;
import com.example.lab2_mob204.dao.UserDAO;
import com.example.lab2_mob204.model.User;

import java.util.List;

public class ListUserActivity extends AppCompatActivity {
    private UserDAO userDAO;
    private RecyclerView rvListUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        rvListUser = findViewById(R.id.rvListND);
//        toolbarAddUser = findViewById(R.id.toolbarAddND);
//        toolbarAddUser.setTitle("NGƯỜI DÙNG");
//        setSupportActionBar(toolbarAddUser);
        setTitle("Nguoi dung");
        userDAO = new UserDAO(ListUserActivity.this);

        List<User> userList = userDAO.selectUser();
        UserAdapter userAdapter = new UserAdapter(ListUserActivity.this, userList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((ListUserActivity.this));
        rvListUser.setLayoutManager(linearLayoutManager);
        rvListUser.setAdapter(userAdapter);
    }


    public void openAddUser(View view) {
        Intent intent=new Intent(ListUserActivity.this,AddUserActivity.class);
        startActivity(intent);
    }
}
