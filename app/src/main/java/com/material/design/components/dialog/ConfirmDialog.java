package com.material.design.components.dialog;

import android.content.Context;

import com.material.design.databinding.ComponentDialogConfirmBinding;

public class ConfirmDialog {

    private ComponentDialogConfirmBinding binding;
    private static ConfirmDialog instance;

    public static ConfirmDialog getInstance(Context context) {
        if (instance == null) {
            instance = new ConfirmDialog(context);
        }
        return instance;
    }

    public ConfirmDialog(Context context) {

    }

    public void show(){

    }
}
