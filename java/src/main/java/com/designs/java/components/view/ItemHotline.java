package com.designs.java.components.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.designs.java.databinding.ItemHotlineBinding;

/**
 * Author: William Nguyen
 */
public class ItemHotline extends FrameLayout {
    private Context context;

    public ItemHotline(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ItemHotline(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ItemHotline(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ItemHotline(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(final Context context) {
        this.context = context;
        ItemHotlineBinding binding = ItemHotlineBinding.inflate(LayoutInflater.from(context), this, true);
        binding.llHotline.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_DIAL);
            String p = "tel:" + "XXX";
            i.setData(Uri.parse(p));
            context.startActivity(i);
        });
    }
}