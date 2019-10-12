package com.example.lab2_mob204.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab2_mob204.R;
import com.example.lab2_mob204.dao.UserDAO;
import com.example.lab2_mob204.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText edUserNameLogin, edPassWordLogin;
    private UserDAO userDAO;
    private CheckBox chkRememberPassWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserNameLogin = findViewById(R.id.edUserNameLogin);
        edPassWordLogin = findViewById(R.id.edPassWordLogin);
        chkRememberPassWord=findViewById(R.id.chkRememberPassword);

        SharedPreferences preferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String name=preferences.getString("USERNAME","");
        String pass=preferences.getString("PASSWORD","");
        boolean status=preferences.getBoolean("REMEMBER",false);

        edUserNameLogin.setText(name);
        edPassWordLogin.setText(pass);
        chkRememberPassWord.setChecked(status);
    }

    public void openMenu(View view) {
        if (edUserNameLogin.getText().toString().equals("")) {
            Toast.makeText(this, "User Empty", Toast.LENGTH_SHORT).show();
            edUserNameLogin.requestFocus();
            return;
        } else if (edPassWordLogin.getText().toString().equals("")) {
            Toast.makeText(this, "PassWord Empty", Toast.LENGTH_SHORT).show();
        } else if (edUserNameLogin.getText().toString().equals("admin") || edPassWordLogin.getText().toString().equals("admin")) {
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
        } else {

            userDAO = new UserDAO(this);


            String username = edUserNameLogin.getText().toString().trim();
            String password = edPassWordLogin.getText().toString().trim();


            User user = new User(username, password, null, null);


            boolean result = userDAO.islogin(user);
            if (result) {
                Toast.makeText(getApplicationContext(), "Welcom to BookManager", Toast.LENGTH_SHORT).show();
                rememberUser(username,password,chkRememberPassWord.isChecked());
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);

                startActivity(intent);
                rememberUser(username,password,chkRememberPassWord.isChecked());

            } else {
                Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                return;
            }


        }
    }
    public void rememberUser(String user,String pass,boolean status){
        SharedPreferences preferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if (!status){
           editor.clear();

        }else {
            editor.putString("USERNAME",user);
            editor.putString("PASSWORD",pass);
            editor.putBoolean("REMEMBER",status);

        }
        editor.commit();
    }

    public void Cacel(View view) {
//        edUserNameLogin.setText("");
//        edPassWordLogin.setText("");
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}




