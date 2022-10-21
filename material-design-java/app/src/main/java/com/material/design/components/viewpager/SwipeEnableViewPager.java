package com.material.design.components.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class SwipeEnableViewPager extends ViewPager {

    private boolean isSwipeEnable = true;

    public SwipeEnableViewPager(Context context) {
        super(context);
    }

    public SwipeEnableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.isSwipeEnable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.isSwipeEnable && super.onInterceptTouchEvent(ev);
    }

    /**
     * set enable swipe or not
     */
    public void setSwipeEnable(boolean isEnable) {
        this.isSwipeEnable = isEnable;
    }
}