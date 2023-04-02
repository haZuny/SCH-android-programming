package com.example.assign3_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView textViewMin;
    TextView textViewSec;
    EditText editTextMin;
    EditText editTextSec;
    Button buttonStart;
    Button buttonStop;
    CustomView customView;

    int min = 0;
    int sec = 0;

    Timer timer;
    TimerTask timerTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMin = findViewById(R.id.textView_min);
        textViewSec = findViewById(R.id.textView_sec);
        editTextMin = findViewById(R.id.editTextText_min);
        editTextSec = findViewById(R.id.editTextText_sec);
        buttonStart = findViewById(R.id.button_start);
        buttonStop = findViewById(R.id.button_stop);
        customView = findViewById(R.id.custumView);

        // Start button 이벤트 등록
        buttonStart.setOnClickListener((e) -> {
            try {
                min = Integer.parseInt(editTextMin.getText().toString());
                sec = Integer.parseInt(editTextSec.getText().toString());
            } catch (Exception err) {
            }

            textViewMin.setText(String.valueOf(min));

            // 타이머가 중지 상태일 경우
            if (timer == null) {
                timer = new Timer();
                timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        // UI 업데이트
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textViewMin.setText(String.valueOf(min));
                                textViewSec.setText(String.valueOf(sec));
                            }
                        });

                        // 시간 바꾸기기
                        if (sec > 0) {
                            sec--;
                        } else {
                            if (min > 0) {
                                min--;
                                sec = 60;
                            } else {
                                timer.cancel();
                                timer = null;
                            }
                        }
                        customView.setTime(min, sec);
                        customView.invalidate();
                    }
                };
                timer.schedule(timerTask, 0, 1000);
            }
            // 타이머가 동작중일 경우
            else {
            }
        });

        // End 버튼 이벤트 등록
        buttonStop.setOnClickListener(((e) -> {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        }));
    }
}