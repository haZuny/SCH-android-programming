package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class UserSelectActivity extends AppCompatActivity {
    // 전역변수
    GlobalVar globalVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_select_page);

        // 전역변수 가져오기
        globalVal = (GlobalVar) getApplicationContext();

        // 컴포넌트
        Button button_addUser = findViewById(R.id.userSelect_button_addUser);
        Button button_manageUser = findViewById(R.id.userSelect_button_manageUser);
        ListView userListView = findViewById(R.id.userSelect_list_userList);

        // DB 컨트롤러
        MySQLite mySQLite = new MySQLite(this);
        SQLiteDatabase dbReader = mySQLite.getWritableDatabase();
        Cursor cursor = dbReader.rawQuery("SELECT * FROM USER", null);

        // DB에서 사용자 정보 읽어오기, 리스트뷰에 추가
        while (cursor.moveToNext()) {
            globalVal.addUserToList(cursor.getString(0));
        }

        // 리스트뷰 어댑터 추가
        ArrayAdapter arrayAdapter = new ArrayAdapter(UserSelectActivity.this, android.R.layout.simple_expandable_list_item_1, globalVal.getUserList());
        userListView.setAdapter(arrayAdapter);
        // 리스트뷰 클릭 이벤트 구현
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // 유저 이름 설정
                globalVal.setUserName(globalVal.getUserList().get(position));
                // 페이지 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class); // 이동할 페이지 인텐트 생성
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

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
                Toast.makeText(getApplicationContext(), "text: " + globalVal.getUserList().get(0), Toast.LENGTH_SHORT).show();
            }
        });
    }
}