package com.material.design.custom.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.material.design.R
import com.material.design.databinding.ComponentTextFieldViewBinding

class TextFieldCustom : LinearLayout {
    private lateinit var binding: ComponentTextFieldViewBinding

    constructor(context: Context) : super(context) {
        initialize(context = context, attributeSet = null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initialize(context, attributeSet)
    }

    private fun initialize(context: Context, attributeSet: AttributeSet?) {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        this.binding = ComponentTextFieldViewBinding.inflate(inflater, this, true)

        if (attributeSet != null) {
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(
                attributeSet, R.styleable.TextFieldCustom, 0, 0
            )

            val textHint: String? = typedArray.getString(R.styleable.TextFieldCustom_textHint)
            val hintRes: Int = typedArray.getInt(R.styleable.TextFieldCustom_hintRes, 0)
            if (textHint != null) {
                this.binding.inputLayout.hint = textHint
            } else if (hintRes != 0) {
                this.binding.inputLayout.setHint(hintRes)
            }

            val startIconDraw: Drawable? =
                typedArray.getDrawable(R.styleable.TextFieldCustom_startIcon)
            if (startIconDraw != null) {
                this.binding.inputLayout.startIconDrawable = startIconDraw
            }

            val text: String? = typedArray.getString(R.styleable.TextFieldCustom_text)
            val textRes: Int = typedArray.getInt(R.styleable.TextFieldCustom_textRes, 0)
            if (text != null) {
                this.binding.inputEditText.setText(text)
            } else if (textRes != 0) {
                this.binding.inputEditText.setText(textRes)
            }

            when (typedArray.getString(R.styleable.TextFieldCustom_imeOption)) {
                ACTION_NEXT -> this.binding.inputEditText.imeOptions = EditorInfo.IME_ACTION_NEXT
                ACTION_DONE -> this.binding.inputEditText.imeOptions = EditorInfo.IME_ACTION_DONE
                ACTION_GO -> this.binding.inputEditText.imeOptions = EditorInfo.IME_ACTION_GO
            }

            when (typedArray.getString(R.styleable.TextFieldCustom_inputTypes)) {
                TYPE_TEXT -> {
                    this.binding.inputEditText.inputType = InputType.TYPE_CLASS_TEXT
                }
                TYPE_TEXT_PASSWORD -> {
                    this.binding.inputEditText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                TYPE_NUMBER_PASSWORD -> {
                    this.binding.inputEditText.inputType = InputType.TYPE_NUMBER_VARIATION_PASSWORD
                }
                TYPE_EMAIL -> {
                    this.binding.inputEditText.inputType =
                        InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }
                TYPE_NUMBER_PHONE -> {
                    this.binding.inputEditText.inputType = InputType.TYPE_CLASS_PHONE
                }
                TYPE_NUMBER -> {
                    this.binding.inputEditText.inputType = InputType.TYPE_CLASS_NUMBER
                }
            }

            val endDrawable: Drawable? =
                typedArray.getDrawable(R.styleable.TextFieldCustom_drawableEnd)
            if (endDrawable != null) {
                this.binding.clearText.visibility = VISIBLE
                this.binding.clearText.setImageDrawable(endDrawable)
            } else {
                this.binding.clearText.visibility = GONE
            }
        }

        this.binding.inputEditText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                if (editable.toString().isNotEmpty()) {
                    binding.clearText.visibility = VISIBLE
                } else {
                    binding.clearText.visibility = GONE
                }
            }
        })

        this.binding.clearText.setOnClickListener {
            this.binding.inputEditText.setText("")
        }
    }

    fun getText(): String {
        return this.binding.inputEditText.text.toString().trim()
    }

    companion object {
        const val ACTION_NEXT = "next"
        const val ACTION_DONE = "done"
        const val ACTION_GO = "go"

        const val TYPE_TEXT = "text"
        const val TYPE_TEXT_PASSWORD = "textPassword"
        const val TYPE_NUMBER_PASSWORD = "numberPassword"
        const val TYPE_EMAIL = "textEmailAddress"
        const val TYPE_NUMBER_PHONE = "phone"
        const val TYPE_NUMBER = "number"
    }
}