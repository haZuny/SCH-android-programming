package com.example.assign3_timer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    int min = 0;
    int sec = 0;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 초 세터
    void setTime(int min, int sec) {
        this.min = min;
        this.sec = sec;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        // 공간 크기
        int w = getWidth();
        int h = getHeight();

        // 중싱
        int x = w / 2;
        int y = h / 2;

        // 반지름
        int rMin = (int) (Math.min(w, h) * 0.4);
        int rSec = (int) (Math.min(w, h) * 0.25);

        // 분 그리기
        paint.setColor(Color.BLUE);
        canvas.drawArc(new RectF(x - rMin, y - rMin, x + rMin, y + rMin), -90, min * 6, true, paint);

        // 초 그리기
        paint.setColor(Color.GREEN);
        canvas.drawArc(new RectF(x - rSec, y - rSec, x + rSec, y + rSec), -90, sec * 6, true, paint);

        // 시계 테두리
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(x, y, rMin, paint);
        canvas.drawCircle(x, y, rSec, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, 10, paint);

    }
}
