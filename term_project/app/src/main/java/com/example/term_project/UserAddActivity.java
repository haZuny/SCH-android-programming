package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserAddActivity extends AppCompatActivity {
    GlobalVar globalVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_add_page);
        
        // 전역변수 가져오기
        globalVar = (GlobalVar) getApplicationContext(); 

        // 컴포넌트
        Button button_addUser = findViewById(R.id.userAdd_button_addUser);
        Button button_back = findViewById(R.id.userAdd_button_back);
        EditText editText_userName = findViewById(R.id.userAdd_editText_userName);

        // DB 컨트롤러
        MySQLite mySQLite = new MySQLite(this);
        SQLiteDatabase dbWriter = mySQLite.getWritableDatabase();

        // 사용자 추가 버튼 동작 구현
        button_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = String.valueOf(editText_userName.getText());
                // 닉네임 사용중일 경우
                if (globalVar.getUserList().contains(text)){
                    Toast.makeText(getApplicationContext(), "이미 사용 중인 이름 입니다.", Toast.LENGTH_SHORT).show();
                }
                // 닉네임 추가
                else {
                    String querry = String.format("INSERT INTO USER VALUES('%s')", text);
                    dbWriter.execSQL(querry);
                    Toast.makeText(getApplicationContext(), "사용자가 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    // 유저 선택 페이지로 이동
                    Intent intent = new Intent(getApplicationContext(), UserSelectActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        // 뒤로가기 버튼 동작 구현
        button_back.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserSelectActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}