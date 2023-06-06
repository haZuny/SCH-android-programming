package com.example.term_project;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewDayListAdapter extends BaseAdapter {
    Context myContext;
    LayoutInflater myLayoutInflater;
    ArrayList<Plan> planList;

    // DB 컨트롤러
    MySQLite mySQLite;
    SQLiteDatabase dbWriter;

    ViewDayListAdapter(Context context, ArrayList<Plan> planList) {
        myContext = context;
        myLayoutInflater = LayoutInflater.from(myContext);
        this.planList = planList;

        // DB 컨트롤러
        mySQLite = new MySQLite(context);
        dbWriter = mySQLite.getWritableDatabase();
    }

    @Override
    public int getCount() {
        return planList.size();
    }

    @Override
    public Object getItem(int i) {
        return planList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return planList.get(i).id;
    }

    // 레이아웃 모습 구현
    @Override
    public View getView(int i, View coverView, ViewGroup viewGroup) {
        View view = myLayoutInflater.inflate(R.layout.view_day_page_listview, null);

        // 컴포넌트
        TextView text_title = view.findViewById(R.id.viewDayList_text_title);
        TextView text_time = view.findViewById(R.id.viewDayList_text_time);
        TextView text_expense = view.findViewById(R.id.viewDayList_text_expense);
        Switch switch_isDone = view.findViewById(R.id.viewDayList_switch);
        Button button_detail = view.findViewById(R.id.viewDayList_button);

        // DB 컨트롤러
        MySQLite mySQLite = new MySQLite(myContext);
        SQLiteDatabase dbWriter = mySQLite.getWritableDatabase();

        // 자세히 보기 다이얼로그
        Dialog viewPlanDialog = new Dialog(myContext);
        viewPlanDialog.setContentView(R.layout.view_plan_detail);

        // 수정 다이얼로그
        Dialog editPlanDialog = new Dialog(myContext);
        editPlanDialog.setContentView(R.layout.plan_add_dialog);

        // 초기화
        text_title.setText(planList.get(i).title);
        text_time.setText(planList.get(i).sTime + " ~ " + planList.get(i).eTime);
        text_expense.setText(String.valueOf(planList.get(i).expense));
        if (planList.get(i).isDone.equals("TRUE")) {
            switch_isDone.setChecked(true);
        } else {
            switch_isDone.setChecked(false);
        }

        // 스위치 이벤트
        switch_isDone.setOnClickListener(view1 -> {
            if (switch_isDone.isChecked()) {
                String querry = String.format("UPDATE PLAN SET is_done = 'TRUE' WHERE id = %d", planList.get(i).id);
                dbWriter.execSQL(querry);
            } else {
                String querry = String.format("UPDATE PLAN SET is_done = 'FALSE' WHERE id = %d", planList.get(i).id);
                dbWriter.execSQL(querry);
            }
        });

        // 자세히 버튼 이벤트
        button_detail.setOnClickListener(v -> {
            viewPlanDialog.show();

            // 컴포넌트
            TextView text_planTitle = viewPlanDialog.findViewById(R.id.viewPlanDetail_text_title);
            TextView text_planDay = viewPlanDialog.findViewById(R.id.viewPlanDetail_text_day);
            TextView text_planTime = viewPlanDialog.findViewById(R.id.viewPlanDetail_text_time);
            TextView text_planExpense = viewPlanDialog.findViewById(R.id.viewPlanDetail_text_expense);
            TextView text_planIsDone = viewPlanDialog.findViewById(R.id.viewPlanDetail_text_isDone);
            Button button_planEdit = viewPlanDialog.findViewById(R.id.viewPlanDetail_button_edit);
            Button button_planDelete = viewPlanDialog.findViewById(R.id.viewPlanDetail_button_delete);
            Button button_planBack = viewPlanDialog.findViewById(R.id.viewPlanDetail_button_back);

            // 초기화
            text_planTitle.setText(planList.get(i).title);
            text_planDay.setText(planList.get(i).day);
            text_planTime.setText(planList.get(i).sTime + " ~ " + planList.get(i).eTime);
            text_planExpense.setText(String.valueOf(planList.get(i).expense));
            text_planIsDone.setText(planList.get(i).isDone);

            // 수정 버튼 이벤트
            button_planEdit.setOnClickListener(v1 -> {
                editPlanDialog.show();

                // 컴포넌트
                EditText editText_title = editPlanDialog.findViewById(R.id.planAdd_editText_title);
                EditText editText_money = editPlanDialog.findViewById(R.id.planAdd_editText_money);
                Button button_stime = editPlanDialog.findViewById(R.id.planAdd_button_stime);
                Button button_etime = editPlanDialog.findViewById(R.id.planAdd_button_etime);
                Button button_ok = editPlanDialog.findViewById(R.id.planAdd_button_ok);
                Button button_cancle = editPlanDialog.findViewById(R.id.planAdd_button_cancle);

                // 초기화
                editText_title.setText(planList.get(i).title);
                editText_money.setText(String.valueOf(planList.get(i).expense));
                button_stime.setText(planList.get(i).sTime);
                button_etime.setText(planList.get(i).eTime);

                // 시작 시간 이벤트
                button_stime.setOnClickListener(v3 -> {
                    Dialog timePicker = new Dialog(myContext);
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
                button_etime.setOnClickListener(v3 -> {
                    Dialog timePicker = new Dialog(myContext);
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
                button_ok.setOnClickListener(v3 -> {
                    // null 확인
                    if ("".equals(editText_title.getText()) || "".equals(editText_money) || "".equals(button_stime.getText()) || "".equals(button_etime.getText())) {
                        Toast.makeText(myContext, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
//                        String querry = String.format("INSERT INTO PLAN VALUES(NULL, '%s', '%s', '%s', '%s', '%s', %d, '%s')", globalVar.getUserName(), editText_title.getText(), globalVar.getSelectedDay(), button_stime.getText(), button_etime.getText(), Integer.parseInt(String.valueOf(editText_money.getText())), "false");
//                        dbWriter.execSQL(querry);
//                        Toast.makeText(myContext, "일정이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                        // 초기 위치로
                        Intent intent = new Intent(myContext, MainActivity.class); // 이동할 페이지 인텐트 생성
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        myContext.startActivity(intent);
                    }
                });


                // 취소 버튼 이벤트
                button_cancle.setOnClickListener(v3 -> {
                    editPlanDialog.dismiss();
                });
            });

            // 삭제 버튼 이벤트
            button_planDelete.setOnClickListener(v1 -> {
                String querry = String.format("DELETE FROM PLAN WHERE id = %d", planList.get(i).id);
                dbWriter.execSQL(querry);
                Toast.makeText(myContext, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                // 초기 위치로
                Intent intent = new Intent(myContext, MainActivity.class); // 이동할 페이지 인텐트 생성
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myContext.startActivity(intent);
            });

            // 뒤로 버튼 이벤트
            button_planBack.setOnClickListener(v1 -> {
                viewPlanDialog.dismiss();
            });

        });

        return view;
    }
}
