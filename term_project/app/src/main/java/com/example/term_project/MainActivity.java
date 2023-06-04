package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        // 전역 변수
        GlobalVar globalVar = (GlobalVar)getApplicationContext();

        // 현재 날짜
        LocalDate now = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            now = LocalDate.now();
        }

        // 컴포넌트
        CalendarView calendarView = findViewById(R.id.main_calender);
        TextView textView_userName = findViewById(R.id.main_text_userName);

        // 초기화
        textView_userName.setText(globalVar.getUserName());
        globalVar.setSelectedDay(now.toString());

        // 날짜 이동시 선택 날짜 변경
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDay = String.format("%04d-%02d-%02d", year, month, dayOfMonth);
            globalVar.setSelectedDay(selectedDay);
        });

    }
}