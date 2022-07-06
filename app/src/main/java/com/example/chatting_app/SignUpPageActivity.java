package com.example.chatting_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatting_app.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class SignUpPageActivity extends AppCompatActivity {
    private static final int PICK_FROM_ALBUM = 10;
    TextView txtIdPw;
    EditText edtId, edtPw, edtName;
    Button btnSignUp, btnSignIn;
    ImageView imgProfile;
    Uri imgUri;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);

        edtId = findViewById(R.id.edtId);
        edtPw = findViewById(R.id.edtPw);
        edtName = findViewById(R.id.edtName);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        txtIdPw = findViewById(R.id.txtIdPw);
        imgProfile = findViewById(R.id.imgProfile);
        mAuth = FirebaseAuth.getInstance();


        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, PICK_FROM_ALBUM);
            }
        });



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = edtId.getText().toString();
                password = edtPw.getText().toString();

                if (email.length() ==0 | password.length() ==0){
                    txtIdPw.setText("이메일과 비밀번호를 입력하세요.");
                    txtIdPw.setVisibility(View.VISIBLE);
                }
                else if (password.length() < 6)
                    txtIdPw.setText("비밀번호는 최소 여섯 자리 입니다.");
                else {
                    txtIdPw.setVisibility(View.INVISIBLE);
                    createAccount(email, password);
                }
            }
        });





    }
    private void createAccount(String email, String password){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUpPageActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        String uid = task.getResult().getUser().getUid();
                        if (task.isSuccessful()){
                            FirebaseStorage.getInstance().getReference().child("userImages").child(uid).putFile(imgUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    String imgUrl = task.getResult().getStorage().getDownloadUrl().toString();



                                    UserModel userModel = new UserModel();
                                    userModel.userName = edtName.getText().toString();
                                    FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel);
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            /*
                            UserModel userModel = new UserModel();
                            userModel.userName = edtName.getText().toString();



                            FirebaseDatabase.getInstance().getReference().child("users").child(uid).setValue(userModel);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                            */
                        }else {
                            Toast.makeText(getApplicationContext(), "가입 실패.", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_FROM_ALBUM && resultCode == RESULT_OK){
            imgProfile.setImageURI(data.getData());
            imgUri = data.getData(); // 원본 이미지 경로
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}