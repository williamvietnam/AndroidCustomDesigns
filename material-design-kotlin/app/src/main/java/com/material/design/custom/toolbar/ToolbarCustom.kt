package com.material.design.custom.toolbar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.material.design.R
import com.material.design.databinding.ComponentToolbarViewBinding

class ToolbarCustom : FrameLayout {

    private lateinit var binding: ComponentToolbarViewBinding
    private lateinit var toolbarStartCallBack: ToolbarStartCallBack
    private lateinit var toolbarEndCallBack: ToolbarEndCallBack

    fun setToolbarStartCallBack(toolbarStartCallBack: ToolbarStartCallBack) {
        this.toolbarStartCallBack = toolbarStartCallBack
    }

    fun setToolbarEndCallBack(toolbarEndCallBack: ToolbarEndCallBack) {
        this.toolbarEndCallBack = toolbarEndCallBack
    }

    constructor(context: Context) : super(context) {
        initialize(context = context, attributeSet = null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initialize(context = context, attributeSet = attributeSet)
    }

    private fun initialize(context: Context, attributeSet: AttributeSet?) {
        val inflater = LayoutInflater.from(context)
        this.binding = ComponentToolbarViewBinding.inflate(inflater, this, true)
        if (attributeSet != null) {
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(
                attributeSet, R.styleable.ToolbarCustom, 0, 0
            )

            val startIconDraws: Drawable? =
                typedArray.getDrawable(R.styleable.ToolbarCustom_startIconDraw)
            if (startIconDraws != null) {
                this.binding.startImageView.setImageDrawable(startIconDraws)
                this.binding.startImageView.visibility = VISIBLE
            } else {
                this.binding.startImageView.visibility = GONE
            }

            val toolbarName: String? = typedArray.getString(R.styleable.ToolbarCustom_toolbarTitle)
            if (toolbarName != null) {
                this.binding.centerTextView.text = toolbarName
                this.binding.centerTextView.visibility = VISIBLE
            } else {
                this.binding.centerTextView.visibility = GONE
            }

            val toolbarNameRes: Int = typedArray.getInt(
                R.styleable.ToolbarCustom_toolbarTitleRes, 0
            )
            if (toolbarNameRes != 0) {
                this.binding.centerTextView.setText(toolbarNameRes)
            }

            val endIconDraws: Drawable? =
                typedArray.getDrawable(R.styleable.ToolbarCustom_endIconDraw)
            if (endIconDraws != null) {
                this.binding.endImageView.setImageDrawable(endIconDraws)
                this.binding.endImageView.visibility = VISIBLE
            } else {
                this.binding.endImageView.visibility = GONE
            }
        }

        this.binding.startImageView.setOnClickListener {
            toolbarStartCallBack.onToolbarStartClicked()
        }

        this.binding.endImageView.setOnClickListener {
            toolbarEndCallBack.onToolbarEndClicked()
        }
    }

    interface ToolbarStartCallBack {
        fun onToolbarStartClicked()
    }

    interface ToolbarEndCallBack {
        fun onToolbarEndClicked()
    }
}