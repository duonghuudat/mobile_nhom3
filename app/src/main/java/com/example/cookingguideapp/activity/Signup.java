package com.example.cookingguideapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookingguideapp.R;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class Signup extends AppCompatActivity {
    private EditText  email_login, passwordLogin;
    private Button signUpBtnLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();


        // Ánh xạ các thành phần giao diện
//        emailLogin = findViewById(R.id.email_login);
        email_login = findViewById(R.id.email_login);
        passwordLogin = findViewById(R.id.password_login);
//        repassword = findViewById(R.id.repassword);
//        imgBtnPass1 = findViewById(R.id.imgbtnpass1);
//        imgBtn2 = findViewById(R.id.imgbtn2);
        signUpBtnLogin = findViewById(R.id.signupbtn_login);
//        info = findViewById(R.id.info);

        // Bắt sự kiện khi click vào nút Đăng ký
        signUpBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();
            }
        });
    }

    private void register() {
        String hoten, emailname, password, repass;
//        hoten = emailLogin.getText().toString();
        emailname = email_login.getText().toString();
        password = passwordLogin.getText().toString();
//        repass = repassword.getText().toString();

//        if(TextUtils.isEmpty(hoten)) {
//            Toast.makeText(this, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if(TextUtils.isEmpty(emailname)) {
            Toast.makeText(this, "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }
//        if(TextUtils.isEmpty(repass)) {
//            Toast.makeText(this, "Vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
//            return;
//        }
        mAuth.createUserWithEmailAndPassword(emailname, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup.this,Login.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Tạo tài khoản không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}