package com.designs.kotlin.ui_design.login

import android.util.Log
import com.designs.kotlin.base.BaseActivity
import com.designs.kotlin.databinding.ActivityLoginContainerBinding

class LoginContainerActivity : BaseActivity<ActivityLoginContainerBinding>() {

    override fun createViewBinding(): ActivityLoginContainerBinding =
        ActivityLoginContainerBinding.inflate(layoutInflater)

    override fun initialize() {
        Log.d(TAG, "debug: onViewCreated()... in $TAG")
    }

    companion object {
        private val TAG = LoginContainerAdapter::class.java.simpleName
    }
}