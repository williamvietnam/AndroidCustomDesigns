package com.material.design.components.dialog;

import android.content.Context;

import com.material.design.databinding.ComponentDialogOptionsBinding;

public class OptionsDialog {

    private ComponentDialogOptionsBinding binding;
    private static OptionsDialog instance;

    public static OptionsDialog getInstance(Context context) {
        if (instance == null) {
            instance = new OptionsDialog(context);
        }

        return instance;
    }

    public OptionsDialog(Context context) {

    }
}
