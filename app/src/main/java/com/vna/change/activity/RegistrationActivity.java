package com.vna.change.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.vna.change.R;

public class RegistrationActivity extends AppCompatActivity {

    TextView tvDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        anhXa();
        xuLy();
    }

    public void anhXa(){
        tvDangNhap = (TextView) findViewById(R.id.tvLogin);
    }

    public  void xuLy(){
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}