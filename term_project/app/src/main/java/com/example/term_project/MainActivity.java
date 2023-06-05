package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
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
        GlobalVar globalVar = (GlobalVar)getApplicationContext();

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

        // 초기화
        textView_userName.setText(globalVar.getUserName());
        globalVar.setSelectedDay(now.toString());
        // 현재 날짜 정보
        Cursor cursor1 = dbReader.rawQuery("SELECT * FROM PLAN WHERE day = "+globalVar.getSelectedDay(), null);
        List<Plan> planList1 = new ArrayList<>();
        while (cursor1.moveToNext()) {
            Plan temp = new Plan(cursor1.getInt(0), cursor1.getString(2), cursor1.getString(3),
                    cursor1.getString(4), cursor1.getString(5), cursor1.getInt(6), cursor1.getString(7));
            planList1.add(temp);
        }
        globalVar.setDayPlanList(planList1);

        // 날짜 이동시 이벤트
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // 선택된 날짜 변경
            String selectedDay = String.format("%04d-%02d-%02d", year, month, dayOfMonth);
            globalVar.setSelectedDay(selectedDay);
            // db에서 선택된 날짜 정보 읽어오기
//            Cursor cursor = dbReader.rawQuery("SELECT * FROM PLAN WHERE day = "+selectedDay, null);
            Cursor cursor = dbReader.rawQuery(String.format("SELECT * FROM PLAN WHERE day = '%s'", selectedDay), null);
            System.out.println(selectedDay);
            List<Plan> planList = new ArrayList<>();
            while (cursor.moveToNext()) {
                Plan temp = new Plan(cursor.getInt(0), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getString(7));
                planList.add(temp);
                System.out.println(String.format("%d, %s, %s", temp.id, temp.day, temp.title));
            }
            globalVar.setDayPlanList(planList);
        });

        // 일정 추가 버튼
        addPlanBtn.setOnClickListener(v -> {
            addPlanDialog.show();

            EditText editText_title = addPlanDialog.findViewById(R.id.planAdd_editText_title);
            EditText editText_money = addPlanDialog.findViewById(R.id.planAdd_editText_money);
            Button button_stime = addPlanDialog.findViewById(R.id.planAdd_button_stime);
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
            Button button_etime = addPlanDialog.findViewById(R.id.planAdd_button_etime);
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
            EditText editText_location = addPlanDialog.findViewById(R.id.planAdd_editText_location);
            Button button_ok = addPlanDialog.findViewById(R.id.planAdd_button_ok);
            // 확인 버튼 이벤트
            button_ok.setOnClickListener(v1 -> {
                // null 확인
                if ("".equals(editText_title.getText()) || "".equals(editText_money) || "".equals(editText_location) || "".equals(button_stime.getText()) || "".equals(button_etime.getText())) {
                    Toast.makeText(getApplicationContext(), "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    String querry = String.format("INSERT INTO PLAN VALUES(NULL, '%s', '%s', '%s', '%s', '%s', %d, '%s')",
                            globalVar.getUserName(), editText_title.getText(), globalVar.getSelectedDay(),
                            button_stime.getText(), button_etime.getText(),
                            Integer.parseInt(String.valueOf(editText_money.getText())), "false");
                    dbWriter.execSQL(querry);
                    Toast.makeText(getApplicationContext(), "일정이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    addPlanDialog.dismiss();
                }
            });
            Button button_cancle = addPlanDialog.findViewById(R.id.planAdd_button_cancle);
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