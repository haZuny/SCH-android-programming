package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        // 전역 변수
        GlobalVar globalVar = (GlobalVar) getApplicationContext();

        // DB 컨트롤러
        MySQLite mySQLite = new MySQLite(this);
        SQLiteDatabase dbWriter = mySQLite.getWritableDatabase();
        SQLiteDatabase dbReader = mySQLite.getWritableDatabase();

        // 현재 날짜
        LocalDate now = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            now = LocalDate.now();
        }

        // 일정 추가 다이얼로그
        Dialog addPlanDialog = new Dialog(MainActivity.this);
        addPlanDialog.setContentView(R.layout.plan_add_dialog);


        // 컴포넌트
        CalendarView calendarView = findViewById(R.id.main_calender);
        TextView textView_userName = findViewById(R.id.main_text_userName);
        Button addPlanBtn = findViewById(R.id.main_button_addPlan);
        Button viewTodayPlanBtn = findViewById(R.id.main_button_viewTodayPlan);
        ListView listView = findViewById(R.id.main_listView);

        // 초기화
        textView_userName.setText("사용자: "+globalVar.getUserName());
        // 초기화 >> 현재 날짜 정보
        globalVar.setSelectedDay(now.toString());
        // 초기화 >> 일정 업데이트
        Cursor cursor1 = dbReader.rawQuery(String.format("SELECT * FROM PLAN WHERE day = '%s' AND user_name = '%s'", globalVar.getSelectedDay(), globalVar.getUserName()), null);
        List<Plan> planList1 = new ArrayList<>();
        List<String> planTitleList1 = new ArrayList<>();
        while (cursor1.moveToNext()) {
            Plan temp = new Plan(cursor1.getInt(0), cursor1.getString(2), cursor1.getString(3),
                    cursor1.getString(4), cursor1.getString(5), cursor1.getInt(6), cursor1.getString(7));
            planList1.add(temp);
            planTitleList1.add(temp.title);
        }
        globalVar.setDayPlanList(planList1);
        // 리스트뷰 어댑터 추가
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1, planTitleList1);
        listView.setAdapter(arrayAdapter1);

        // 날짜 이동시 이벤트
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // 선택된 날짜 변경
            String selectedDay = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
            globalVar.setSelectedDay(selectedDay);
            // db에서 선택된 날짜 정보 읽어오기
            Cursor cursor = dbReader.rawQuery(String.format("SELECT * FROM PLAN WHERE day = '%s' AND user_name = '%s'", globalVar.getSelectedDay(), globalVar.getUserName()), null);
            List<Plan> planList = new ArrayList<>();
            List<String> planTitleList = new ArrayList<>();
            while (cursor.moveToNext()) {
                Plan temp = new Plan(cursor.getInt(0), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7));
                planList.add(temp);
                planTitleList.add(temp.title);
            }
            globalVar.setDayPlanList(planList);
            // 리스트뷰 어댑터 추가
            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1, planTitleList);
            listView.setAdapter(arrayAdapter);
        });


        // 리스트뷰 클릭 이벤트 구현
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View v, int position, long id) {
//
//            }
//        });

        // 일정 추가 버튼
        addPlanBtn.setOnClickListener(v -> {
            addPlanDialog.show();

            // 컴포넌트
            EditText editText_title = addPlanDialog.findViewById(R.id.planAdd_editText_title);
            EditText editText_money = addPlanDialog.findViewById(R.id.planAdd_editText_money);
            Button button_stime = addPlanDialog.findViewById(R.id.planAdd_button_stime);
            Button button_etime = addPlanDialog.findViewById(R.id.planAdd_button_etime);
            Button button_ok = addPlanDialog.findViewById(R.id.planAdd_button_ok);
            Button button_cancle = addPlanDialog.findViewById(R.id.planAdd_button_cancle);

            // 시작 시간 이벤트
            button_stime.setOnClickListener(v1 -> {
                Dialog timePicker = new Dialog(this);
                timePicker.setContentView(R.layout.time_picker);
                timePicker.show();
                TimePicker picker = timePicker.findViewById(R.id.timePicker_picker);
                Button btn = timePicker.findViewById(R.id.timePicker_ok);
                btn.setOnClickListener(v2 -> {
                    String time = String.format("%02d:%02d", picker.getHour(), picker.getMinute());
                    button_stime.setText(time);
                    timePicker.dismiss();
                });
            });

            // 종료 시간 이벤트
            button_etime.setOnClickListener(v1 -> {
                Dialog timePicker = new Dialog(this);
                timePicker.setContentView(R.layout.time_picker);
                timePicker.show();
                TimePicker picker = timePicker.findViewById(R.id.timePicker_picker);
                Button btn = timePicker.findViewById(R.id.timePicker_ok);
                btn.setOnClickListener(v2 -> {
                    String time = String.format("%02d:%02d", picker.getHour(), picker.getMinute());
                    button_etime.setText(time);
                    timePicker.dismiss();
                });
            });

            // 확인 버튼 이벤트
            button_ok.setOnClickListener(v1 -> {
                // null 확인
                if ("".equals(editText_title.getText()) || "".equals(editText_money) || "".equals(button_stime.getText()) || "".equals(button_etime.getText())) {
                    Toast.makeText(getApplicationContext(), "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    String querry = String.format("INSERT INTO PLAN VALUES(NULL, '%s', '%s', '%s', '%s', '%s', %d, '%s')",
                            globalVar.getUserName(), editText_title.getText(), globalVar.getSelectedDay(),
                            button_stime.getText(), button_etime.getText(),
                            Integer.parseInt(String.valueOf(editText_money.getText())), "false");
                    dbWriter.execSQL(querry);
                    Toast.makeText(getApplicationContext(), "일정이 추가되었습니다.", Toast.LENGTH_SHORT).show();

                    // 일정 목록 업데이트
                    Cursor cursor2 = dbReader.rawQuery(String.format("SELECT * FROM PLAN WHERE day = '%s' AND user_name = '%s'", globalVar.getSelectedDay(), globalVar.getUserName()), null);
                    List<Plan> planList2 = new ArrayList<>();
                    List<String> planTitleList2 = new ArrayList<>();
                    while (cursor2.moveToNext()) {
                        Plan temp = new Plan(cursor2.getInt(0), cursor2.getString(2), cursor2.getString(3),
                                cursor2.getString(4), cursor2.getString(5), cursor2.getInt(6), cursor2.getString(7));
                        planList2.add(temp);
                        planTitleList2.add(temp.title);
                    }
                    globalVar.setDayPlanList(planList2);
                    // 리스트뷰 어댑터 추가
                    ArrayAdapter arrayAdapter2 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1, planTitleList2);
                    listView.setAdapter(arrayAdapter2);

                    // 초기 위치로
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class); // 이동할 페이지 인텐트 생성
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

            });

            // 취소 버튼 이벤트
            button_cancle.setOnClickListener(v1 -> {
                addPlanDialog.dismiss();
            });
        });

        // 오늘 일정 보기 이벤트
        viewTodayPlanBtn.setOnClickListener(view -> {
            // 페이지 이동
            Intent intent = new Intent(getApplicationContext(), ViewDayActivity.class); // 이동할 페이지 인텐트 생성
            startActivity(intent);
        });

    }
}