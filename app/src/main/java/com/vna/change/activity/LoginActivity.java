package com.vna.change.activity;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.vna.change.R;
import com.vna.change.retrofit.API;
import com.vna.change.retrofit.model.DataReponcesUser;
import com.vna.change.retrofit.RetrofitDuLieu;
import com.google.android.material.textfield.TextInputEditText;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    TextView tvSkip, tvQuenPass, tvDangKyTK;
    TextInputEditText  tipMK;
    EditText tipTenTK;
    ImageView ivShow;
    Button btDangnhap;
    API api;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        xuLy();
    }

    private void init(){
        tvSkip = findViewById(R.id.tvSkip);
        tvQuenPass = findViewById(R.id.tvForgetPassword);
        tvDangKyTK = findViewById(R.id.tvRegister);
        tipTenTK = findViewById(R.id.tipUsername);
        tipMK = findViewById(R.id.tipPassword);
        ivShow = findViewById(R.id.ivShowPass);
        btDangnhap = findViewById(R.id.btLogin);
        api = RetrofitDuLieu.getInstance().create(API.class);
    }

    private void xuLy(){
        final Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });

        tvDangKyTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                    intent1.setClass(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        tvQuenPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Hy", Toast.LENGTH_SHORT).show();
            }
        });

        btDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int tam = 0;

                if(tipTenTK.getText().toString().trim().equals("")){
                    Toast.makeText(LoginActivity.this, "Bạn chưa nhập tên đăng nhập!", Toast.LENGTH_SHORT).show();
                    tipTenTK.setError("Bạn chưa nhập tên đăng nhập");
                    tam++;
                }
                if(tipMK.getText().toString().trim().equals("")){
                    Toast.makeText(LoginActivity.this, "Bạn chưa nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                    tipMK.setError("Bạn chưa mật khẩu");
                    tam++;
                }
//                else {
//                    if(!checkPassword(tipMK.getText().toString().trim()).equals("")){
//                        tipMK.setError(checkPassword(tipMK.getText().toString().trim()));
//                        tam++;
//                    }
//                }

                if(tam == 0){
                    final String username = tipTenTK.getText().toString().trim();
                    final String pass = tipMK.getText().toString().trim();
                    Disposable disposable = api.login(username, pass).subscribeOn(Schedulers.io())
                                                                .observeOn(AndroidSchedulers.mainThread())
                                                                .subscribe(new Consumer<DataReponcesUser>() {
                                                                    @Override public void accept(DataReponcesUser user) throws Exception {

//                                                                        Toast.makeText(LoginActivity.this,"DN", Toast.LENGTH_SHORT).show();
//                                                                        Log.d("DN", "accept: "+username+"\n"+pass);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }, new Consumer<Throwable>() {
                                                                    @Override
                                                                    public void accept(Throwable throwable) throws Exception {

                                                                    }
                                                                });

                    compositeDisposable.add(disposable);
                }

            }
        });
    }

    public static String checkPassword(String pass) {
        String error = "Mật khẩu phải bao gồm:\n";
        int soKyTu = 0, soChuSo = 0;
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isUpperCase(pass.charAt(i))||Character.isLowerCase(pass.charAt(i))) {
                soKyTu++;
            } else if (Character.isDigit(pass.charAt(i))) {
                soChuSo++;
            }
        }
        if (soKyTu==0)
            error += "ký tự chữ\n";
        if (soChuSo == 0)
            error += "ký tự số\n";

        if (soChuSo != 0 && soKyTu != 0)
            return "";
        else
            return error;
    }
}