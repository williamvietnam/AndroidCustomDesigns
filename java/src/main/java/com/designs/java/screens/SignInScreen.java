package com.designs.java.screens;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.designs.java.R;
import com.designs.java.databinding.ScreenSignInBinding;

public class SignInScreen extends LinearLayout {

    private ScreenSignInBinding binding;
    private Context context;

    public SignInScreen(Context context) {
        super(context);
        initialize(context, null);
    }

    public SignInScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public SignInScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, @Nullable AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = ScreenSignInBinding.inflate(inflater, this, true);
        if (attrs != null) {
            this.context = context;
            final TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.SignInScreen, 0, 0);
        }
    }
}