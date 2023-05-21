package com.designs.java.components.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.designs.java.R;
import com.designs.java.databinding.ToolbarV2Binding;

/**
 * Author: William Nguyen
 */
public class ToolbarVersion2Component extends FrameLayout {

    private ToolbarV2Binding binding;
    private Context context;

    public ToolbarVersion2Component(@NonNull Context context) {
        super(context);
        initialize(context, null);
    }

    public ToolbarVersion2Component(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public ToolbarVersion2Component(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, @Nullable AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = ToolbarV2Binding.inflate(inflater, this, true);
        if (attrs != null) {
            this.context = context;
            final TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.ToolbarVersion2Component, 0, 0);
        }
    }
}
