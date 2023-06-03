package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_add_page);

        // 컴포넌트
        Button button_addUser = findViewById(R.id.userAdd_button_addUser);
        EditText editText_userName = findViewById(R.id.userAdd_editText_userName);

        // DB 컨트롤러
        MySQLite mySQLite = new MySQLite(this);
        SQLiteDatabase dbWriter = mySQLite.getWritableDatabase();


        // 사용자 추가 버튼 동작 구현
        button_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String querry = String.format("INSERT INTO USER VALUES('%s')", editText_userName.getText());
                dbWriter.execSQL(querry);
                try {
                    Toast.makeText(getApplicationContext(), "사용자가 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 뒤로가기
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "이미 사용중인 이름입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}