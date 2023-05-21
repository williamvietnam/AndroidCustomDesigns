package com.designs.java.components.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.designs.java.databinding.ComponentDialogConfirmBinding;

/**
 * Author: William Nguyen
 */
public class ConfirmDialog {

    private ComponentDialogConfirmBinding binding;
    private static ConfirmDialog instance;
    private AlertDialog alertDialog = null;
    private final Context context;

    public static ConfirmDialog getInstance(Context context) {
        if (instance == null) {
            instance = new ConfirmDialog(context);
        }
        return instance;
    }

    public ConfirmDialog(Context context) {
        this.context = context;
        if (context == null || (alertDialog != null && alertDialog.isShowing())) {
            return;
        }
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = ComponentDialogConfirmBinding.inflate(inflater);

        View view = binding.getRoot();
        dialogBuilder.setView(view);
        dialogBuilder.setCancelable(true);
        alertDialog = dialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding.leftClick.setOnClickListener(v -> {
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        });
    }

    public void show(String title, String description,
                     String textLeftButton, String textRightButton) {
        binding.textTitle.setText(title);
        binding.textDescription.setText(description);
        binding.leftClick.setText(textLeftButton);
        binding.rightClick.setText(textRightButton);
        if (alertDialog != null && !alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

    public void show(@StringRes int title, @StringRes int description,
                     @StringRes int textLeftButton, @StringRes int textRightButton) {
        binding.textTitle.setText(context.getResources().getString(title));
        binding.textDescription.setText(context.getResources().getString(description));
        binding.leftClick.setText(context.getResources().getString(textLeftButton));
        binding.rightClick.setText(context.getResources().getString(textRightButton));
        if (alertDialog != null && !alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

    public ConfirmDialog setCancelOnToughOutSide(boolean isCancelable) {
        alertDialog.setCancelable(isCancelable);
        alertDialog.setCanceledOnTouchOutside(isCancelable);
        alertDialog.setOnKeyListener((dialog, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                // we cannot close dialog when we press back button
            }
            return false;
        });

        return this;
    }

    public ConfirmDialog setupLeftClick(@Nullable ConfirmDialog.CallBack callBack) {
        binding.leftClick.setVisibility(View.VISIBLE);
        binding.leftClick.setOnClickListener(v -> {
            if (alertDialog != null) {
                alertDialog.dismiss();
                if (callBack != null) {
                    callBack.onButtonLeftClick();
                }
            }
        });

        return this;
    }

    public ConfirmDialog setupRightClick(@Nullable ConfirmDialog.CallBack callBack) {
        binding.rightClick.setVisibility(View.VISIBLE);
        binding.rightClick.setOnClickListener(v -> {
            if (alertDialog != null) {
                alertDialog.dismiss();
                if (callBack != null) {
                    callBack.onButtonRightClick();
                }
            }
        });

        return this;
    }

    public void onDestroy() {
        instance = null;
    }

    public interface CallBack {

        void onButtonLeftClick();

        void onButtonRightClick();

    }
}
