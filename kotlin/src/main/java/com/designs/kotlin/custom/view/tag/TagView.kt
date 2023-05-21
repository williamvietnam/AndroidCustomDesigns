package com.designs.kotlin.custom.view.tag

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.designs.kotlin.R
import com.designs.kotlin.databinding.ItemTagBinding

class TagView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val tags = arrayListOf<TextView>()
    private var lineHeight = 0
    private val horizontalSpacing: Int
    private val verticalSpacing: Int

    init {
        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TagView)
        horizontalSpacing =
            styledAttrs.getDimension(R.styleable.TagView_horizontal_spacing, 1f).toInt()
        verticalSpacing = styledAttrs.getDimension(R.styleable.TagView_vertical_spacing, 1f).toInt()
        styledAttrs.recycle()
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return TagLayoutParams(horizontalSpacing, verticalSpacing)
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams): LayoutParams {
        return TagLayoutParams(horizontalSpacing, verticalSpacing)
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams) = p is TagLayoutParams

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val width = r - l
        var xPos = paddingLeft
        var yPos = paddingTop

        tags.forEach { child ->
            if (child.visibility != View.GONE) {
                val childWidth = child.measuredWidth
                val childHeight = child.measuredHeight

                val lp = child.layoutParams as TagLayoutParams
                if (xPos + childWidth > width) {
                    xPos = paddingLeft
                    yPos += lineHeight
                }
                child.layout(xPos, yPos, xPos + childWidth, yPos + childHeight)
                xPos += childWidth + lp.horizontalSpacing
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec) - paddingLeft - paddingRight
        var height = View.MeasureSpec.getSize(heightMeasureSpec) - paddingTop - paddingBottom
        var lineHeight = 0

        var xPos = paddingLeft
        var yPos = paddingTop

        val childHeightMeasureSpec: Int
        if (View.MeasureSpec.getMode(heightMeasureSpec) == View.MeasureSpec.AT_MOST) {
            childHeightMeasureSpec =
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST)
        } else {
            childHeightMeasureSpec =
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        }

        tags.forEach { child ->
            if (child.visibility != View.GONE) {
                val lp = child.layoutParams as TagLayoutParams
                child.measure(
                    View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST),
                    childHeightMeasureSpec
                )
                val childWidth = child.measuredWidth
                lineHeight = Math.max(lineHeight, child.measuredHeight + lp.verticalSpacing)

                if (xPos + childWidth > width) {
                    xPos = paddingLeft
                    yPos += lineHeight
                }
                xPos += childWidth + lp.horizontalSpacing
            }
        }

        this.lineHeight = lineHeight
        if (View.MeasureSpec.getMode(heightMeasureSpec) == View.MeasureSpec.UNSPECIFIED) {
            height = yPos + lineHeight
        } else if (View.MeasureSpec.getMode(heightMeasureSpec) == View.MeasureSpec.AT_MOST) {
            if (yPos + lineHeight < height) {
                height = yPos + lineHeight
            }
        }
        setMeasuredDimension(width, height)
    }

    fun setTags(items: MutableList<String>) {
        removeAllViews()
        items.forEach { setTag(it) }
        requestLayout()
    }

    private fun setTag(item: String) {
        val binding: ItemTagBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_tag,
            this,
            false
        )
        binding.contentTv.text = item
        tags.add(binding.contentTv)
        addView(binding.contentTv)
    }

    fun clear() {
        tags.clear()
        removeAllViews()
    }
}

class TagLayoutParams(
    val horizontalSpacing: Int,
    val verticalSpacing: Int
) : LinearLayout.LayoutParams(
    ViewGroup.LayoutParams.WRAP_CONTENT,
    ViewGroup.LayoutParams.WRAP_CONTENT
)