package com.material.design.ui_design.login.samples

import android.view.LayoutInflater
import android.view.ViewGroup
import com.material.design.base.BaseFragment
import com.material.design.databinding.FragmentLoginSampleOneBinding

class LoginSampleOneFragment : BaseFragment<FragmentLoginSampleOneBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginSampleOneBinding =
        FragmentLoginSampleOneBinding.inflate(inflater, container, false)

    override fun initialize() {

    }
}