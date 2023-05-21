package com.designs.kotlin.ui_design.login.samples

import android.view.LayoutInflater
import android.view.ViewGroup
import com.designs.kotlin.base.BaseFragment
import com.designs.kotlin.databinding.FragmentLoginSampleOneBinding

class LoginSampleOneFragment : BaseFragment<FragmentLoginSampleOneBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginSampleOneBinding =
        FragmentLoginSampleOneBinding.inflate(inflater, container, false)

    override fun initialize() {

    }
}