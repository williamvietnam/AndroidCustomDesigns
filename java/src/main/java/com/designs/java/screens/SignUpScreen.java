package com.material.design.screens;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.material.design.R;
import com.material.design.databinding.ScreenSignUpBinding;

public class SignUpScreen extends LinearLayout {
    private ScreenSignUpBinding binding;
    private Context context;

    public SignUpScreen(Context context) {
        super(context);
        initialize(context, null);
    }

    public SignUpScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public SignUpScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, @Nullable AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = ScreenSignUpBinding.inflate(inflater, this, true);
        if (attrs != null) {
            this.context = context;
            final TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.SignUpScreen, 0, 0);

        }
    }
}