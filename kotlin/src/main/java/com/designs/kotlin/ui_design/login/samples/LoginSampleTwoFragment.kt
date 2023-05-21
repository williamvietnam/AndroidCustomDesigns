package com.designs.kotlin.ui_design.login.samples

import android.view.LayoutInflater
import android.view.ViewGroup
import com.designs.kotlin.base.BaseFragment
import com.designs.kotlin.databinding.FragmentLoginSampleTwoBinding

class LoginSampleTwoFragment : BaseFragment<FragmentLoginSampleTwoBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginSampleTwoBinding.inflate(inflater, container, false)

    override fun initialize() {

    }
}