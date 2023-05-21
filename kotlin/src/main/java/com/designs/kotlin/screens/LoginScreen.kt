package com.designs.kotlin.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.designs.kotlin.R
import com.designs.kotlin.databinding.LoginViewBinding

class LoginScreen : LinearLayout {
    private lateinit var binding: LoginViewBinding
    private lateinit var callBack: CallBack

    fun setCallBack(callBack: CallBack) {
        this.callBack = callBack
    }

    constructor(context: Context) : super(context) {
        initialize(context = context, attributeSet = null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initialize(context = context, attributeSet = attributeSet)
    }

    @SuppressLint("SetTextI18n")
    private fun initialize(context: Context, attributeSet: AttributeSet?) {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        this.binding = LoginViewBinding.inflate(inflater, this, true)

        if (attributeSet != null) {
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(
                attributeSet, R.styleable.LoginScreen, 0, 0
            )

            val title = typedArray.getString(R.styleable.LoginScreen_title)
            if (title != null) {
                this.binding.titleLoginScreen.text = title
            } else {
                this.binding.titleLoginScreen.text = "Create new account"
            }

            val hintAccount = typedArray.getString(R.styleable.LoginScreen_hintAccount)
            val hintAccountRes = typedArray.getInt(R.styleable.LoginScreen_hintAccount, 0)
            if (hintAccount != null) {
                this.binding.inputAccount.hint = hintAccount
            } else if (hintAccountRes != 0) {
                this.binding.inputAccount.setHint(hintAccountRes)
            } else {
                this.binding.inputAccount.hint = "Account"
            }


        }
    }

    interface CallBack {
        fun onLoginButtonClicked()
    }
}