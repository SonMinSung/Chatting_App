package com.example.chatting_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    EditText edtId, edtPw;
    Button btnSignUp, btnSignIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("채팅 앱 테스트 중");

        edtId = findViewById(R.id.edtId);
        edtPw = findViewById(R.id.edtPw);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        mAuth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() { //로그인 버튼이 눌리면 DB에서 정보를 읽어와 ID, PW가 같은 경우 로그인 성공 아니면 ID또는 PW가 틀립니다. Toast 하기
            @Override
            public void onClick(View v) {
                String email, password;
                email = edtId.getText().toString();
                password = edtPw.getText().toString();
                signIn(email, password);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() { //회원가입 페이지로 넘어간 후 정보 처리, 가입 성공 후 이름, ID, PW는 거기서 DB로 연동하든 메인페이지로 가져와서 처리하든 해결
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpPageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    // Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    Intent intent2 = new Intent(getApplicationContext(), ChattingPageActivity.class);
                    startActivity(intent2);

                } else {
                    // If sign in fails, display a message to the user.
                    // Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), "로그인 실패.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
            private void updateUI(FirebaseUser user) {
            }
        });
        // [END sign_in_with_email]


    }
}