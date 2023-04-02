package com.example.smartphone_assign1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 변수
    int num;
    int life;
    boolean start = false;
    int startTime = 0;
    int currentTime = 0;

    // 컴포넌트
    Button button_reset;
    Button button_geuss;
    TextView textView_life;
    TextView textView_UpDown;
    TextView textView_time;
    EditText editText_num;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button
        button_reset = (Button) findViewById(R.id.button_reset);
        button_geuss = (Button) findViewById(R.id.button_guess);
        // Text
        textView_life = (TextView) findViewById(R.id.textView_life);
        textView_UpDown = (TextView) findViewById(R.id.textView_updown);
        textView_time = (TextView) findViewById(R.id.textView_time);
        // EditText
        editText_num = (EditText) findViewById(R.id.editText_Number);
        // Image
        imageView = (ImageView) findViewById(R.id.imageView);

        // 필드 초기화
        num = (int) (Math.random() * 100);   // 난수 생성
        life = 10;
        editText_num.setText("0");
        textView_UpDown.setText("Start");
        textView_life.setText(String.format("남은 목숨: %d", life));
        textView_time.setText(String.format("남은 시간: %d", currentTime - startTime));
        imageView.setImageResource(R.drawable.intro);

        // 시간 측정 시작
        TimeThread t = new TimeThread();
        t.start();

        // guess 버튼 이벤트 정의
        button_geuss.setOnClickListener((e) -> {
            // 시작한 시간 측정
            if (life == 10) {
                startTime = (int) (System.currentTimeMillis() / 1000);
                start = true;
            }
            // 게임 오버
            else if (life == 1){
                textView_life.setText(String.format("남은 목숨: %d", 0));
                imageView.setImageResource(R.drawable.fail);
                textView_UpDown.setText("Fail");
                start = false;
                return;
            }

            if(start){
                // 목숨 빼기
                life -= 1;
                textView_life.setText(String.format("남은 목숨: %d", life));

                // 숫자 가져오기
                int guessNum = Integer.parseInt(String.valueOf(editText_num.getText()));

                // Up
                if (guessNum > num){
                    imageView.setImageResource(R.drawable.down);
                    textView_UpDown.setText("Down");
                }
                // Down
                else if(guessNum < num){
                    imageView.setImageResource(R.drawable.up);
                    textView_UpDown.setText("Up");
                }
                // Success
                else{
                    imageView.setImageResource(R.drawable.success);
                    textView_UpDown.setText("Success");
                    start = false;
                }
            }

        });

        // Reset 버튼 이벤트 리스너
        button_reset.setOnClickListener((e) -> {
            start = false;
            num = (int) (Math.random() * 100);   // 난수 생성
            life = 10;
            editText_num.setText("0");
            textView_UpDown.setText("Start");
            textView_life.setText(String.format("남은 목숨: %d", life));
            textView_time.setText(String.format("남은 시간: %d", 0));
            imageView.setImageResource(R.drawable.intro);
        });
    }

    // 시간 측정 Thread
    class TimeThread extends Thread {
        public void run() {
            while (true) {
                if (start) {
                    currentTime = (int) (System.currentTimeMillis() / 1000);
                    // 안드로이드 리소스 변경은 처음 생성된 thread에서만 변경 가능.
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView_time.setText(String.format("시간: %d", currentTime - startTime));
                        }
                    });
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}