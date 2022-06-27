package com.example.chatting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPageActivity extends AppCompatActivity {
    EditText edtId, edtPw, edtName;
    Button btnSignUp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);

        edtId = findViewById(R.id.edtId);
        edtPw = findViewById(R.id.edtPw);
        edtName = findViewById(R.id.edtName);
        btnSignUp2 = findViewById(R.id.btnSignUp2);

        btnSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtId.length() <6){
                    Toast.makeText(getApplicationContext(), "아이디의 최소 길이는 6글자입니다.", Toast.LENGTH_SHORT).show();
                    if (edtPw.length() > 20 | edtId.length() <8){
                        Toast.makeText(getApplicationContext(), "비밀번호의 최소 길이는 8글자입니다.", Toast.LENGTH_SHORT).show();
                        if (edtName.length() == 0)Toast.makeText(getApplicationContext(), "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}