package com.example.chatting_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtId, edtPw;
    Button btnSignUp, btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("채팅 앱 테스트 중");
        edtId = findViewById(R.id.edtId);
        edtPw = findViewById(R.id.edtPw);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() { //로그인 버튼이 눌리면 DB에서 정보를 읽어와 ID, PW가 같은 경우 로그인 성공 아니면 ID또는 PW가 틀립니다. Toast 하기
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() { //회원가입 페이지로 넘어간 후 정보 처리, 가입 성공 후 이름, ID, PW는 거기서 DB로 연동하든 메인페이지로 가져와서 처리하든 해결
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}