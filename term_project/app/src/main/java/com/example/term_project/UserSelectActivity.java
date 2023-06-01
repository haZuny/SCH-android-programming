package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_select_page);
        
        // 컴포넌트
        Button button_add_user = findViewById(R.id.button_add_user);
        
        // 사용자 추가 버튼 동작 구현
        button_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserAddActivity.class); // 이동할 페이지 인텐트 생성
                startActivity(intent);
            }
        });
        
    }
}