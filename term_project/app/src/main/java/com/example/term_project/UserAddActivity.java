package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_add_page);

        // 컴포넌트
        Button button_cmp_add_user = findViewById(R.id.button_cmp_add_user);

        // 사용자 추가 버튼 동작 구현
        button_cmp_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "긴 토스트 메시지입니다.", Toast.LENGTH_SHORT).show();
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 뒤로가기
            }
        });
    }
}