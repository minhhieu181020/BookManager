package com.example.lab2_mob204.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.example.lab2_mob204.R;
import com.example.lab2_mob204.dao.UserDAO;
import com.example.lab2_mob204.model.User;

public class AddUserActivity extends AppCompatActivity {
    private EditText edUserName, edPass, edPhone, edFullname;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setTitle("Them Nguoi DUng");

        edUserName = findViewById(R.id.edUserName);
        edPass = findViewById(R.id.edPass);
        edPhone = findViewById(R.id.edPhone);
        edFullname = findViewById(R.id.edFullName);

    }


    public void addNewUser(View view) {
        userDAO = new UserDAO(AddUserActivity.this);

        String username = edUserName.getText().toString();
        String password = edPass.getText().toString();
        String phone = edPhone.getText().toString();
        String hoten = edFullname.getText().toString();

        User user = new User(username, password, phone,hoten);

        if (userDAO.insertUser(user)) {

            Toast.makeText(this, " Them Thanh cong", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Them That bai", Toast.LENGTH_SHORT).show();

        }

    }

    public void openListUser(View view) {
        Intent intent = new Intent(AddUserActivity.this, ListUserActivity.class);
        startActivity(intent);
    }
}
