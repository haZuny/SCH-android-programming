package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserSelectActivity extends AppCompatActivity {
    List<String> usersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_select_page);
        
        // 컴포넌트
        Button button_addUser = findViewById(R.id.userSelect_button_addUser);
        Button button_manageUser = findViewById(R.id.userSelect_button_manageUser);
        ListView userList = findViewById(R.id.userSelect_list_userList);

        // DB 컨트롤러
        MySQLite mySQLite = new MySQLite(this);
        SQLiteDatabase dbReader = mySQLite.getWritableDatabase();
        Cursor cursor = dbReader.rawQuery("SELECT * FROM USER", null);

        // DB에서 사용자 정보 읽어오기, 리스트뷰에 추가
        while(cursor.moveToNext()){
            usersList.add(cursor.getString(0));
        }

        // 리스트뷰 어댑터 추가
        ArrayAdapter arrayAdapter=new ArrayAdapter(UserSelectActivity.this, android.R.layout.simple_expandable_list_item_1,usersList);
        userList.setAdapter(arrayAdapter);

        // 사용자 추가 버튼 동작 구현
        button_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserAddActivity.class); // 이동할 페이지 인텐트 생성
                startActivity(intent);
            }
        });

        // 사용자 관리 버튼 동작 구현
        button_manageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "text: "+usersList.get(0), Toast.LENGTH_SHORT).show();
            }
        });
    }
}