package com.material.design.ui_design.login.samples

import android.view.LayoutInflater
import android.view.ViewGroup
import com.material.design.base.BaseFragment
import com.material.design.databinding.FragmentLoginSampleTwoBinding

class LoginSampleTwoFragment : BaseFragment<FragmentLoginSampleTwoBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginSampleTwoBinding =
        FragmentLoginSampleTwoBinding.inflate(inflater, container, false)

    override fun initialize() {

    }
}