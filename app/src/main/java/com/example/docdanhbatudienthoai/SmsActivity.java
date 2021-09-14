package com.example.docdanhbatudienthoai;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.topica.model.Contact;


public class SmsActivity extends AppCompatActivity {
    TextView txtNguoiNhan, txtSMSContent;
    ImageButton btnGui;
    Contact selectedContact = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTinNhan();
            }
        });
    }

    private void xuLyTinNhan() {
        // lấy mặc định SmsManager
        final SmsManager sms = SmsManager.getDefault();
        Intent msgSent = new Intent("ACTION_MSG_SENT");
        // Khai báo pendingintent để kiểm tra
        final PendingIntent pendingMsgSent = PendingIntent.getBroadcast(this,0,msgSent,0);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg = "Gửi tin nhắn thành công";
                if (result != Activity.RESULT_OK) {
                    msg = "Gửi tin nhắn thất bại";
                }
                Toast.makeText(SmsActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter("ACTION_MSG_SENT"));
        sms.sendTextMessage(selectedContact.getPhone(),null,txtSMSContent.getText()+"",pendingMsgSent,null);

    }

    private void addControls() {
        txtNguoiNhan = findViewById(R.id.txtNguoiNhan);
        txtSMSContent = findViewById(R.id.txtSmsContent);
        btnGui = findViewById(R.id.btnGui);


        Intent i = getIntent();
        selectedContact = (Contact) i.getSerializableExtra("CONTACT");
        txtNguoiNhan.setText(selectedContact.getName()+"-"+selectedContact.getPhone());


    }
}