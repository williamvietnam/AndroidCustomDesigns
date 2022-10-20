package com.material.design.components.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.core.widget.TextViewCompat;

import com.material.design.R;
import com.material.design.databinding.DialogAppAttentionBinding;
import com.material.design.utilities.AppLogger;

public class AttentionDialog {

    public static final String TAG = AttentionDialog.class.getCanonicalName();

    private DialogAppAttentionBinding binding;

    private AlertDialog alertDialog = null;

    private static AttentionDialog instance;

    private Context context;

    public static AttentionDialog getInstance(Context context) {
        instance = new AttentionDialog(context);
        return instance;
    }

    public static AttentionDialog getInstance(Context context, boolean isCancel) {
        instance = new AttentionDialog(context, isCancel);
        return instance;
    }

    public AttentionDialog(Context context) {
        this.context = context;
        if (context == null || (alertDialog != null && alertDialog.isShowing())) {
            return;
        }
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DialogAppAttentionBinding.inflate(inflater);
        View view = binding.getRoot();
        dialogBuilder.setView(view);
        dialogBuilder.setCancelable(true);
        alertDialog = dialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    public AttentionDialog(@NonNull Context context, boolean isCancel) {
        this.context = context;
        if (alertDialog != null && alertDialog.isShowing()) {
            return;
        }
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DialogAppAttentionBinding.inflate(inflater);
        View view = binding.getRoot();
        dialogBuilder.setView(view);
        dialogBuilder.setCancelable(isCancel);
        alertDialog = dialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(isCancel);
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    public void show() {
        if (alertDialog != null) {
            alertDialog.show();
        } else {
            AppLogger.e(TAG + " trying to show a null alert dialog");
        }
    }

    public void dismiss() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    /**
     * set iconType (ex: R.id.ic_success)
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public AttentionDialog setIcon(@DrawableRes int iconId) {
        binding.icType.setVisibility(View.VISIBLE);
        binding.icType.setImageDrawable(context.getResources().getDrawable(iconId));
        return this;
    }

    /**
     * Dialog with no body, title is the message to be shown
     *
     * @param titleId - the string res of message to be shown
     */
    public AttentionDialog setTitle(@StringRes int titleId) {
        return setTitle(titleId, R.style.AppIconDialogText_Body_Info);
    }

    /**
     * Dialog with no body, title is the message to be shown
     *
     * @param title - the message to be shown
     */
    public AttentionDialog setTitle(String title) {
        return setTitle(title, R.style.AppIconDialogText_Body_Info);
    }

    /**
     * Dialog with no body, title is the message to be shown
     *
     * @param title - the message to be shown
     */
    public AttentionDialog setTitle(Spanned title) {
        return setTitle(title, R.style.AppIconDialogText_Body_Info);
    }

    /**
     * Dialog with body
     *
     * @param titleRes - the string res of title
     * @param styleRes - the style res of title
     */
    public AttentionDialog setTitle(@StringRes int titleRes, @StyleRes int styleRes) {
        binding.tvTitle.setText(titleRes);
        TextViewCompat.setTextAppearance(binding.tvTitle, styleRes);
        binding.tvTitle.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * Dialog with body
     *
     * @param title    - the title
     * @param styleRes - the style res of title
     */
    public AttentionDialog setTitle(String title, @StyleRes int styleRes) {
        binding.tvTitle.setText(title);
        TextViewCompat.setTextAppearance(binding.tvTitle, styleRes);
        binding.tvTitle.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * Dialog with body
     *
     * @param title    - the title
     * @param styleRes - the style res of title
     */
    public AttentionDialog setTitle(Spanned title, @StyleRes int styleRes) {
        binding.tvTitle.setText(title);
        TextViewCompat.setTextAppearance(binding.tvTitle, styleRes);
        binding.tvTitle.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * @param subTitleId - string res of message
     */
    public AttentionDialog setSubTitle(@StringRes int subTitleId) {
        return setSubTitle(subTitleId, R.style.AppIconDialogText_Body_Warning);
    }

    /**
     * @param subTitle - message
     */
    public AttentionDialog setSubTitle(String subTitle) {
        return setSubTitle(subTitle, R.style.AppIconDialogText_Body_Warning);
    }

    /**
     * Set body for dialog with style
     *
     * @param subTitleRes - string res of message
     * @param styleRes    - style res of message style
     */
    public AttentionDialog setSubTitle(@StringRes int subTitleRes, @StyleRes int styleRes) {
        binding.tvSubTitle.setText(subTitleRes);
        TextViewCompat.setTextAppearance(binding.tvSubTitle, styleRes);
        binding.tvSubTitle.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * Set body for dialog with style
     *
     * @param subTitle - message
     * @param styleRes - style res of message style
     */
    public AttentionDialog setSubTitle(String subTitle, @StyleRes int styleRes) {
        binding.tvSubTitle.setText(subTitle);
        TextViewCompat.setTextAppearance(binding.tvSubTitle, styleRes);
        binding.tvSubTitle.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * Set body for dialog with style
     *
     * @param subTitle - message
     * @param styleRes - style res of message style
     */
    public AttentionDialog setSubTitle(Spanned subTitle, @StyleRes int styleRes) {
        binding.tvSubTitle.setText(subTitle);
        TextViewCompat.setTextAppearance(binding.tvSubTitle, styleRes);
        binding.tvSubTitle.setVisibility(View.VISIBLE);
        return this;
    }

    public AttentionDialog setCancelOnToughOutSide(boolean isCancelable) {
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

    /**
     * setup primary button (single button in center dialog)
     *
     * @param btnText               button text
     * @param iOnButtonPrimaryClick set null if you no need to listen onclick event
     */
    public AttentionDialog setupPrimaryButton(@StringRes int btnText, @Nullable IOnButtonPrimaryClick iOnButtonPrimaryClick) {
        binding.btnPrimary.setVisibility(View.VISIBLE);
        binding.llTwoButton.setVisibility(View.GONE);
        binding.btnPrimary.setText(context.getResources().getText(btnText));
        binding.btnPrimary.setOnClickListener(v -> {
            if (alertDialog != null) {
                alertDialog.dismiss();
                if (iOnButtonPrimaryClick != null) {
                    iOnButtonPrimaryClick.onButtonPrimaryClick();
                }
            }
        });
        return this;
    }

    /**
     * setup positive button (left button)
     *
     * @param btnText                button text
     * @param iOnButtonPositiveClick set null if you no need to listen onclick event
     */
    public AttentionDialog setupPositiveButton(@StringRes int btnText, @Nullable IOnButtonPositiveClick iOnButtonPositiveClick) {
        binding.btnPrimary.setVisibility(View.GONE);
        binding.llTwoButton.setVisibility(View.VISIBLE);
        binding.btnPositive.setText(context.getResources().getText(btnText));
        binding.btnPositive.setOnClickListener(v -> {
            if (alertDialog != null) {
                alertDialog.dismiss();
                if (iOnButtonPositiveClick != null) {
                    iOnButtonPositiveClick.onButtonPositiveClick();
                }
            }
        });
        return this;
    }

    /**
     * setup negative button (right button)
     *
     * @param btnText                button text
     * @param iOnButtonNegativeClick set null if you no need to listen onclick event
     */
    public AttentionDialog setupNegativeButton(@StringRes int btnText, @Nullable IOnButtonNegativeClick iOnButtonNegativeClick) {
        binding.btnPrimary.setVisibility(View.GONE);
        binding.llTwoButton.setVisibility(View.VISIBLE);
        binding.btnNegative.setText(context.getResources().getText(btnText));
        binding.btnNegative.setOnClickListener(v -> {
            if (alertDialog != null) {
                alertDialog.dismiss();
                if (iOnButtonNegativeClick != null) {
                    iOnButtonNegativeClick.onButtonNegativeClick();
                }
            }
        });
        return this;
    }

    /**
     * hide all view before we config dialog
     */
    private void resetState() {
        binding.icType.setVisibility(View.GONE);
        binding.tvTitle.setVisibility(View.GONE);
        binding.tvSubTitle.setVisibility(View.GONE);
        binding.btnPrimary.setVisibility(View.GONE);
        binding.llTwoButton.setVisibility(View.GONE);
    }

    public void onDestroy() {
        instance = null;
    }

    public interface IOnButtonPrimaryClick {
        void onButtonPrimaryClick();
    }

    public interface IOnButtonPositiveClick {
        void onButtonPositiveClick();
    }

    public interface IOnButtonNegativeClick {
        void onButtonNegativeClick();
    }
}