package com.example.docdanhbatudienthoai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuLyMoManHinhDanhBa(View view) {
        Intent intent = new Intent(MainActivity.this, DanhBaActivity.class);
        startActivity(intent);
    }

    public void xuLyDocTinNhan(View view) {
        Intent intent = new Intent(MainActivity.this, TinNhanActivity.class);
        startActivity(intent);
    }
}