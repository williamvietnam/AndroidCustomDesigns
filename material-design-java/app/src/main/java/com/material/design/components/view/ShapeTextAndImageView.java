package com.material.design.components.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.material.design.R;

public class ShapeTextAndImageView extends FrameLayout {

    private int backgroundColor;
    private int textColor;
    private String text;
    private float textSize;
    private int avatar = -1;

    private GradientDrawable gradientDrawable;
    private TextView tv;
    private ImageView im;

    public ShapeTextAndImageView(Context context) {
        super(context);
        addChildren(context);
    }

    public ShapeTextAndImageView(Context context, int avatarResourceId) {
        super(context);
        avatar = avatarResourceId;
        addChildren(context);
    }

    public ShapeTextAndImageView(Context context, int backgroundColor, float textSize, int textColor, String text) {
        super(context);
        this.backgroundColor = backgroundColor;
        this.textSize = textSize;
        this.textColor = textColor;
        this.text = changeTextToInitials(text);
        addChildren(context);
    }

    public ShapeTextAndImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyAttrs(context, attrs);
        addChildren(context);
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        gradientDrawable.setColor(this.backgroundColor);
        makeTextViewVisible();
    }

    public void setText(String text) {
        this.text = changeTextToInitials(text);
        tv.setText(this.text);
        makeTextViewVisible();
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        tv.setTextSize(this.textSize);
        makeTextViewVisible();
    }

    public void setTextColor(int color) {
        this.textColor = color;
        tv.setTextColor(this.textColor);
        makeTextViewVisible();
    }

    public ImageView getAvatar() {
        makeAvatarVisible();
        return im;
    }

    public int getTextColor() {
        return textColor;
    }

    public String getText() {
        return text;
    }

    public float getTextSize() {
        return textSize;
    }

    private void makeTextViewVisible() {
        setBackgroundResource(0);
        setBackground(gradientDrawable);
        tv.setVisibility(View.VISIBLE);
        im.setVisibility(View.GONE);
    }

    private void makeAvatarVisible() {
        tv.setVisibility(View.GONE);
        im.setVisibility(View.VISIBLE);
    }

    private void applyAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.shapeInitialsView_attrs, 0, 0);
        try {
            backgroundColor = a.getColor(R.styleable.shapeInitialsView_attrs_shapeInitialsView_backgroundColor, getResources().getColor(R.color.color_FBD4C2));
            textColor = a.getColor(R.styleable.shapeInitialsView_attrs_shapeInitialsView_textColor, Color.BLACK);
            avatar = a.getResourceId(R.styleable.shapeInitialsView_attrs_shapeInitialsView_avatar, -1);
            text = a.getString(R.styleable.shapeInitialsView_attrs_shapeInitialsView_text);
            String textSizeString = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "textSize");
            if (textSizeString != null) {
                String split1 = textSizeString.split("sp")[0];
                this.textSize = Float.parseFloat(split1);
            } else {
                this.textSize = 15;
            }
            text = changeTextToInitials(text);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            a.recycle();
        }
    }

    private String changeTextToInitials(String text) {
        if (text != null && text.trim().length() > 0) {
            if (text.trim().length() <= 2) {
                return text.trim().toUpperCase();
            }
            String[] split = text.trim().split(" ");
            String result = String.valueOf(split[0].charAt(0));
            for (int i = 2; i < 15; i++) {
                if (split.length == i) {
                    result += String.valueOf(split[i - 1].charAt(0));
                }
            }
            return result.toUpperCase();
        } else {
            return null;
        }
    }

    private void addChildren(Context context) {
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(backgroundColor);
        setBackground(gradientDrawable);
        tv = new TextView(context);
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tv.setTextColor(textColor);
        tv.setGravity(Gravity.CENTER);
        im = new ImageView(context);
        addView(tv);
        addView(im);
        FrameLayout.LayoutParams params = (LayoutParams) tv.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        tv.setLayoutParams(params);
        im.setLayoutParams(params);

        if (avatar == -1) {
            makeTextViewVisible();
        } else {
            makeAvatarVisible();
        }
    }
}

