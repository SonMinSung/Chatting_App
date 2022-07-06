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
    private FirebaseAuth.AuthStateListener authStateListener;

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
        mAuth.signOut();


        btnSignIn.setOnClickListener(new View.OnClickListener() { //로그인 정보 확인
            @Override
            public void onClick(View v) {
                String email, password;
                email = edtId.getText().toString();
                password = edtPw.getText().toString();
                signIn(email, password);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() { //회원가입 페이지로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpPageActivity.class);
                startActivity(intent);
            }
        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null){ //로그인
                    Intent intent2 = new Intent(getApplicationContext(), ChatMainPageActivity.class);
                    startActivity(intent2);
                    finish();
                }else{ //로그아웃

                }
            }
        };
    }


    private void signIn(String email, String password) {
        // [START sign_in_with_email]

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    // If sign in fails, display a message to the user.
                    // Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), "로그인 실패.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
            private void updateUI(FirebaseUser user) {
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
}