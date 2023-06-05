package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ViewDayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_day_page);

        // 전역변수
        GlobalVar globalVar = (GlobalVar) getApplicationContext();
        List<Plan> todayPlanList = globalVar.getDayPlanList();

        // 컴포넌트
        TextView text_day = findViewById(R.id.viewDay_text_day);
        TextView text_expense = findViewById(R.id.viewDay_text_expensse);
        ListView listView = findViewById(R.id.viewDay_listView);

        // 선택 날짜 글자 설정
        text_day.setText(globalVar.getSelectedDay());

        // 금액 글자 설정
        int todayExpense = 0;
        for (Plan plan : todayPlanList){
            todayExpense += plan.expense;
        }
        text_expense.setText(String.valueOf(todayExpense));


    }
}