package com.designs.java.components.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.designs.java.databinding.ComponentDialogFullScreenBinding;

/**
 * Author: William Nguyen
 */
public class FullScreenDialog {

    private ComponentDialogFullScreenBinding binding;
    private static FullScreenDialog instance;
    private AlertDialog alertDialog = null;
    private final Context context;

    public static FullScreenDialog getInstance(Context context) {
        if (instance == null) {
            instance = new FullScreenDialog(context);
        }
        return instance;
    }

    public FullScreenDialog(Context context) {
        this.context = context;
        if (context == null || (alertDialog != null && alertDialog.isShowing())) {
            return;
        }
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = ComponentDialogFullScreenBinding.inflate(inflater);

        View view = binding.getRoot();
        dialogBuilder.setView(view);
        dialogBuilder.setCancelable(true);
        alertDialog = dialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding.cancelClick.setOnClickListener(v -> {
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        });
    }

    public void show(String headline) {
        binding.textHeadline.setText(headline);
        if (alertDialog != null && !alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

    public void show(@StringRes int headline) {
        binding.textHeadline.setText(context.getResources().getString(headline));
        if (alertDialog != null && !alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

    public FullScreenDialog setupCancelClick(@Nullable FullScreenDialog.CallBack callBack) {
        binding.cancelClick.setVisibility(View.VISIBLE);
        binding.cancelClick.setOnClickListener(v -> {
            if (alertDialog != null) {
                alertDialog.dismiss();
                if (callBack != null) {
                    callBack.onButtonLeftClick();
                }
            }
        });

        return this;
    }

    public FullScreenDialog setupSaveClick(@Nullable FullScreenDialog.CallBack callBack) {
        binding.saveClick.setVisibility(View.VISIBLE);
        binding.saveClick.setOnClickListener(v -> {
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
