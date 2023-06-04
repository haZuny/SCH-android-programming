package com.example.term_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

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

        // 일정 추가 다이얼로그
        Dialog addPlanDialog = new Dialog(MainActivity.this);
        addPlanDialog.setContentView(R.layout.plan_add_dialog);


        // 컴포넌트
        CalendarView calendarView = findViewById(R.id.main_calender);
        TextView textView_userName = findViewById(R.id.main_text_userName);
        Button addPlanBtn = findViewById(R.id.main_button_addPlan);

        // 초기화
        textView_userName.setText(globalVar.getUserName());
        globalVar.setSelectedDay(now.toString());

        // 날짜 이동시 선택 날짜 변경
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDay = String.format("%04d-%02d-%02d", year, month, dayOfMonth);
            globalVar.setSelectedDay(selectedDay);
        });

        // 일정 추가 버튼
        addPlanBtn.setOnClickListener(v -> {
            addPlanDialog.show();

            EditText editText_title = addPlanDialog.findViewById(R.id.planAdd_editText_title);
            EditText editText_money = addPlanDialog.findViewById(R.id.planAdd_editText_title);
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
            EditText editText_location = addPlanDialog.findViewById(R.id.planAdd_editText_title);
            Button button_ok = addPlanDialog.findViewById(R.id.planAdd_button_ok);
            Button button_cancle = addPlanDialog.findViewById(R.id.planAdd_button_cancle);

            // 취소 버튼 이벤트
            button_cancle.setOnClickListener(v1 -> {
                addPlanDialog.dismiss();
            });


        });


    }
}