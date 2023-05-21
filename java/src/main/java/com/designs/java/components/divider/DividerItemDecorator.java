package com.designs.java.components.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.designs.java.R;

/**
 * Author: William Nguyen
 */
public class DividerItemDecorator extends RecyclerView.ItemDecoration {
    private final @LinearLayoutCompat.OrientationMode
    int orientation;
    private final int mainAxisMargin;

    public DividerItemDecorator(@LinearLayoutCompat.OrientationMode int orientation, int mainAxisMargin) {
        this.orientation = orientation;
        this.mainAxisMargin = mainAxisMargin;
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        Drawable drawable = ContextCompat.getDrawable(parent.getContext(), R.drawable.divider);

        if (orientation == LinearLayoutCompat.VERTICAL) {
            int dividerLeft = (int) convertDpToPx(parent.getContext(), parent.getPaddingLeft() + mainAxisMargin);
            int dividerRight = parent.getWidth() - (int) convertDpToPx(parent.getContext(), parent.getPaddingRight() + mainAxisMargin);

            int childCount = parent.getChildCount();
            for (int i = 0; i <= childCount - 2; i++) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int dividerTop = child.getBottom() + params.bottomMargin;
                int dividerBottom = dividerTop + drawable.getIntrinsicHeight();

                drawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                drawable.draw(canvas);
            }
        } else {    // HORIZONTAL
            int dividerTop = (int) convertDpToPx(parent.getContext(), parent.getPaddingTop() + mainAxisMargin);
            int dividerBottom = parent.getHeight() - (int) convertDpToPx(parent.getContext(), parent.getPaddingBottom() + mainAxisMargin);

            int childCount = parent.getChildCount();
            for (int i = 0; i <= childCount - 2; i++) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int dividerLeft = child.getRight() + params.leftMargin;
                int dividerRight = dividerLeft + drawable.getIntrinsicWidth();

                drawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                drawable.draw(canvas);
            }
        }
    }

    public static float convertDpToPx(@NonNull Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}