package com.example.chatting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ChattingPageActivity extends AppCompatActivity {
    Button btnSend;
    EditText edtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting_page);
        btnSend = findViewById(R.id.btnSend);
        edtMsg = findViewById(R.id.edtMsg);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "아직 미구현", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
