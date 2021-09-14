package com.example.docdanhbatudienthoai;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.docdanhbatudienthoai.topica.adapter.ContactAdapter;
import com.example.docdanhbatudienthoai.topica.model.Contact;

public class DanhBaActivity extends AppCompatActivity {
    ListView lvDanhBa;
    ArrayList<Contact> dsDanhBa;
    ArrayAdapter<Contact> adapterDanhBa;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);
        addControls();
        addEvents();
        showAllContactFromDevice();
    }

    private void showAllContactFromDevice() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        dsDanhBa.clear();
        while(cursor.moveToNext())
        {
            String tenCotName = ContactsContract.Contacts.DISPLAY_NAME;
            String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int vtTenCotName = cursor.getColumnIndex(tenCotName);
            int vtTenCotPhone = cursor.getColumnIndex(tenCotPhone);
            String name = cursor.getString(vtTenCotName);
            String phone = cursor.getString(vtTenCotPhone);
            Contact contact = new Contact(name,phone);
            dsDanhBa.add(contact);

        }
        adapterDanhBa.notifyDataSetChanged();
    }

    private void addEvents() {

    }

    private void addControls() {
        lvDanhBa = findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();

        adapterDanhBa = new ContactAdapter(
                DanhBaActivity.this,
                R.layout.item,
                dsDanhBa);
        lvDanhBa.setAdapter(adapterDanhBa);

    }
}