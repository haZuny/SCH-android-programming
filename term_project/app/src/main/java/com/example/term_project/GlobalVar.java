package com.example.term_project;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class GlobalVar extends Application {
    List<String> userList = new ArrayList<>();  // 사용자 목록
    List<Plan> dayPlanList = new ArrayList<>(); // 선택된 날짜의 일정 목록
    List<String> dayPlanTitleList = new ArrayList<>();  // 선택된 날짜 일정 타이틀 목록
    String user_name = "";
    String selectedDay = "";



    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    // 사용자 리스트 추가
    int addUserToList(String userName){
        if (userList.contains(userName)){
            return -1;
        }
        userList.add(userName);
        return 0;
    }

    // Getter
    List<String> getUserList(){
        return userList;
    }
    List<Plan> getDayPlanList(){return dayPlanList;}
    List<String> getDayPlanTitleList(){return dayPlanTitleList;}
    String getUserName() { return  user_name;}
    String getSelectedDay(){return selectedDay;}

    // Setter
    void setDayPlanList(List<Plan> planList){this.dayPlanList = planList;}
    void setDayPlanTitleList(List<String> list){this.dayPlanTitleList = list;}
    void setUserName(String userName){this.user_name = userName;}
    void setSelectedDay(String day){selectedDay = day;}
}
