package com.designs.kotlin.custom.toolbar

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.designs.kotlin.R
import com.designs.kotlin.databinding.ToolBar2Binding

class Toolbar2 : LinearLayout {
    private lateinit var binding: ToolBar2Binding

    constructor(context: Context) : super(context) {
        initialize(context = context, attributeSet = null)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initialize(context = context, attributeSet = attributeSet)
    }

    private fun initialize(context: Context, attributeSet: AttributeSet?) {
        val inflater = LayoutInflater.from(context)
        this.binding = ToolBar2Binding.inflate(inflater, this, true)
        if (attributeSet != null) {
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(
                attributeSet, R.styleable.ToolbarCustom, 0, 0
            )
        }
    }
}