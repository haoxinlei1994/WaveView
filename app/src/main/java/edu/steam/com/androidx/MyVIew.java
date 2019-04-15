package edu.steam.com.androidx;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MyVIew extends View {
    public MyVIew(Context context) {
        this(context, null);
    }

    public MyVIew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    Context context;
    Paint paint;
    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
    }

    int mWidth;
    int mHeight;
    int baseLine;
    int waveHeight = 100;
    int waveWidth;
    int offset = 0;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        baseLine = h/4;
        waveWidth = w/2;
    }

    //屏幕左边多出一个半周期所以是-3
    public Path getPath(){
        Path path = new Path();
        path.moveTo(-3 * waveWidth+offset,baseLine);
        for (int i = -3; i < 2; i++) {
            float startx = i * waveWidth;
            path.quadTo(startx+waveWidth/2+offset,getWaveHeight(i),startx+waveWidth+offset,baseLine);
        }
        path.lineTo(mWidth,getMeasuredHeight());
        path.lineTo(0,getMeasuredHeight());
        path.close();
        return path;
    }

    public float getWaveHeight(int i){
        if (i % 2 == 0)
            return baseLine+waveHeight;
        return baseLine-waveHeight;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        ValueAnimator animator = ValueAnimator.ofInt(0,getMeasuredWidth());
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawPath(getPath(),paint);
    }
}
