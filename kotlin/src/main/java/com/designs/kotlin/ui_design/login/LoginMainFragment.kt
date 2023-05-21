package com.designs.kotlin.ui_design.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.designs.kotlin.base.BaseFragment
import com.designs.kotlin.data.models.LoginModel
import com.designs.kotlin.databinding.FragmentLoginMainBinding

class LoginMainFragment : BaseFragment<FragmentLoginMainBinding>(), LoginContainerAdapter.CallBack {
    private lateinit var loginContainerAdapter: LoginContainerAdapter

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentLoginMainBinding = FragmentLoginMainBinding.inflate(inflater, container, false)

    override fun initialize() {
        this.loginContainerAdapter = LoginContainerAdapter(createDataList(), this)
        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        this.binding.recyclerView.addItemDecoration(divider)
        this.binding.recyclerView.adapter = loginContainerAdapter
    }

    override fun onItemClicked(item: LoginModel?) {
        when (item?.itemId) {
            SAMPLE_ONE -> {
                showToast(item.itemContent)
            }

            SAMPLE_TWO -> {
                showToast(item.itemContent)
            }
        }
    }

    private fun createDataList(): MutableList<LoginModel> {
        val loginModelList: MutableList<LoginModel> = mutableListOf()
        loginModelList.add(LoginModel(1, "SAMPLE VERSION 1"))
        loginModelList.add(LoginModel(2, "SAMPLE VERSION 2"))
        return loginModelList
    }

    companion object {

        private const val SAMPLE_ONE = 1

        private const val SAMPLE_TWO = 2
    }
}