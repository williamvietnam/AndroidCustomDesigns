package com.material.design.components.dialog;

import android.content.Context;

import com.material.design.databinding.ComponentDialogFullScreenBinding;

public class FullScreenDialog {

    private ComponentDialogFullScreenBinding binding;
    private static FullScreenDialog instance;

    public static FullScreenDialog getInstance(Context context) {
        if (instance == null) {
            instance = new FullScreenDialog(context);
        }
        return instance;
    }

    public FullScreenDialog(Context context) {

    }
}
