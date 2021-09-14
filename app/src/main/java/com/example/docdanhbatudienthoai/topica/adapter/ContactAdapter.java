package com.example.docdanhbatudienthoai.topica.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.docdanhbatudienthoai.R;
import com.example.docdanhbatudienthoai.SmsActivity;


import java.util.List;

import com.example.docdanhbatudienthoai.topica.model.Contact;


public class ContactAdapter extends ArrayAdapter {
    Activity context;
    int resource;
    List <Contact> objects;

    public ContactAdapter(@NonNull Activity context, int resource, @NonNull List <Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource= resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        TextView txtTen = row.findViewById(R.id.txtTenItem);
        TextView txtPhone = row.findViewById(R.id.txtPhoneItem);
        ImageButton btnCall = row.findViewById(R.id.btnCall);
        ImageButton btnSms = row.findViewById(R.id.btnSms);
        ImageButton btnDelete = row.findViewById(R.id.btnDelelte);

        Contact contact = this.objects.get(position);
        txtTen.setText(contact.getName());
        txtPhone.setText(contact.getPhone());

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyCall(contact);
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLySms(contact);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDelete(contact);
            }
        });
        return row;
    }

    private void xuLyDelete(Contact contact) {
        this.remove(contact);
    }

    private void xuLySms(Contact contact) {
        Intent i = new Intent(this.context, SmsActivity.class);
        i.putExtra("CONTACT", contact);
        this.context.startActivity(i);
    }

    private void xuLyCall(Contact contact) {
        Intent i=new Intent(Intent.ACTION_CALL);
        Uri u= Uri.parse("tel:"+ contact.getPhone());
        i.setData(u);
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            return;
        }

        this.context.startActivity(i);
    }
}
