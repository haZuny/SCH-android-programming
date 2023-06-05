package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
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

        // 리스트뷰 설정
        ViewDayListAdapter myAdapter = new ViewDayListAdapter((Context) this, (ArrayList<Plan>) globalVar.getDayPlanList());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            System.out.println("bbbbbbbbbbbbbbbbbbb");
        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View v, int position, long id) {
//                System.out.println("aaaaaaaaaaaaaaaaaaaaa");
//                // 일정 추가 다이얼로그
//                Dialog addPlanDialog = new Dialog(ViewDayActivity.this);
//                addPlanDialog.setContentView(R.layout.plan_add_dialog);
//                addPlanDialog.show();
//            }
//        });
    }
}