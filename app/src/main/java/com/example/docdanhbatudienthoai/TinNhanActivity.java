package com.example.docdanhbatudienthoai;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TinNhanActivity extends AppCompatActivity {
    ListView lvTinNhan;
    ArrayList<String>dsTinNhan;
    ArrayAdapter<String>adapterTinNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_nhan);
        addControls();
        addEvents();
        docToanBoTinNhan();
    }

    private void docToanBoTinNhan() {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        dsTinNhan.clear();
        while (cursor.moveToNext())
        {
            int indexPhonNumber = cursor.getColumnIndex("address");
            int indexTimeStamp = cursor.getColumnIndex("date");
            int indexBody = cursor.getColumnIndex("body");

            String phoneNumber = cursor.getString(indexPhonNumber);
            String timeStamp = cursor.getString(indexTimeStamp);
            String body = cursor.getString(indexBody);

            dsTinNhan.add(phoneNumber+"\n"+ timeStamp+"\n"+body);

        }
        cursor.close();
        adapterTinNhan.notifyDataSetChanged();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvTinNhan = findViewById(R.id.lvTinNhan);
        dsTinNhan = new ArrayList<>();
        adapterTinNhan = new ArrayAdapter<String>(
                TinNhanActivity.this,
                android.R.layout.simple_list_item_1,
                dsTinNhan);
        lvTinNhan.setAdapter(adapterTinNhan);
    }
}