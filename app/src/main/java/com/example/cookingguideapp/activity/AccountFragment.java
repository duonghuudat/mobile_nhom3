package com.example.cookingguideapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.cookingguideapp.R;
import com.example.cookingguideapp.databinding.FragmentAccountBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.Nullable;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {
    private FirebaseAuth mAuth;
    private TextView txtSave, txtRePassword;
    private EditText edtFullName, edtEmail, edtPassword;
    private Button btnLogout;
    private ImageButton eyeButton;
    CircleImageView  img;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        mAuth = FirebaseAuth.getInstance();


        // Khởi tạo các thành phần của layout
        txtSave = view.findViewById(R.id.txtSave_account);
        img = (CircleImageView) view.findViewById(R.id.imgView_icon_account);

        txtRePassword = view.findViewById(R.id.txtRePassword_account);
        edtFullName = view.findViewById(R.id.edtFullName_account);
        edtEmail = view.findViewById(R.id.edtEmail_account);
        edtPassword = view.findViewById(R.id.edtPassword_account);
        btnLogout = view.findViewById(R.id.btnLogout_account);
        eyeButton = view.findViewById(R.id.imageButton);

        // Đặt bộ lắng nghe cho các nút hoặc các tác vụ khác cần thực hiện
        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý lưu thông tin tài khoản
            }
        });



        txtRePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị giao diện để thay đổi mật khẩu
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
            }
        });


        eyeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị hoặc ẩn mật khẩu
            }
        });

        return view;
    }

}