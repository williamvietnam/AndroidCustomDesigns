package com.material.design.ui_design.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.material.design.data.models.LoginModel
import com.material.design.databinding.ItemLoginSampleBinding

class LoginContainerAdapter(
    private val loginModelList: MutableList<LoginModel>?,
    private val callBack: CallBack?
) : RecyclerView.Adapter<LoginContainerAdapter.LoginContainerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginContainerHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLoginSampleBinding.inflate(inflater, parent, false)
        return LoginContainerHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: LoginContainerHolder, position: Int) {
        holder.onBindData(position)
    }

    override fun getItemCount(): Int {
        if (loginModelList != null) {
            return loginModelList.size
        }
        return 0
    }

    inner class LoginContainerHolder(
        private val binding: ItemLoginSampleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBindData(position: Int) {
            val item: LoginModel? = loginModelList?.get(position)
            if (item != null) {
                this.binding.text.text = item.itemContent
            }

            this.binding.root.setOnClickListener {
                callBack?.onItemClicked(item)
            }
        }
    }

    interface CallBack {
        fun onItemClicked(item: LoginModel?)
    }
}