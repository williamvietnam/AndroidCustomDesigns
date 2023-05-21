package com.designs.java.components.dialog;

import android.content.Context;

import com.designs.java.databinding.ComponentDialogOptionsBinding;

/**
 * Author: William Nguyen
 */
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
