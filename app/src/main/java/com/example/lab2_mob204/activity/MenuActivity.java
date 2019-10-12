package com.example.lab2_mob204.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.lab2_mob204.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusetting, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ChangePass:

                AlertDialog.Builder alertdialog=new AlertDialog.Builder(this);
                alertdialog.setView(R.layout.activity_changpassword);
                AlertDialog alertDialog=alertdialog.show();

                break;
            case R.id.Logout:
                System.exit(0);
                break;
            case R.id.Exit:
                Intent startMain=new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void changePassword() {

    }

    public void openUser(View view) {
        Intent intent = new Intent(MenuActivity.this, ListUserActivity.class);
        startActivity(intent);

    }

    public void openTheloai(View view) {
        Intent intent = new Intent(MenuActivity.this, TheloaiActivity.class);
        startActivity(intent);
    }

    public void openBook(View view) {
        Intent intent = new Intent(MenuActivity.this, BookActivity.class);
        startActivity(intent);
    }

//    public void openBill(View view) {
//        Intent intent = new Intent(MenuActivity.this, BillActivity.class);
//        startActivity(intent);
//    }
}
