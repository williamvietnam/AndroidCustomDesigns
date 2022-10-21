package com.material.design.components.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class SwipeDisabledViewPager extends ViewPager {

    private boolean isSwipeEnable = false;

    public SwipeDisabledViewPager(Context context) {
        super(context);
        isSwipeEnable = false;
    }

    public SwipeDisabledViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        isSwipeEnable = false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isSwipeEnable) {
            return super.onTouchEvent(ev);
        } else {
            // returning false will not propagate the swipe event
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isSwipeEnable) {
            return onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    /**
     * set enable swipe or not
     */
    public void setSwipeEnable(boolean isEnable) {
        this.isSwipeEnable = isEnable;
    }
}