package com.designs.kotlin.custom.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.designs.kotlin.R
import java.lang.ref.WeakReference

class LoadingDialog private constructor(private val context: Context?) {
    private var isShow = false
    private lateinit var dialog: AlertDialog
    fun show() {
        if (context != null && !(context as Activity?)!!.isFinishing) {
            if (!isShow) {
                isShow = true
                dialog.show()
            }
        }
    }

    fun hidden() {
        if (isShow && dialog.isShowing) {
            isShow = false
            dialog.dismiss()
        }
    }

    fun destroyLoadingDialog() {
        if (instance != null) {
            instance!!.dialog.dismiss()
        }
        instance = null
    }

    companion object {
        private var instance: LoadingDialog? = null

        fun getInstance(context: Context): LoadingDialog? {
            return if (instance != null) {
                instance
            } else {
                instance = LoadingDialog(
                    WeakReference(context).get()
                )
                instance
            }
        }
    }

    init {
        if (context != null && !isShow) {
            val dialogBuilder = AlertDialog.Builder(context)
            val li = LayoutInflater.from(context)
            val dialogView = li.inflate(R.layout.layout_loading, null)
            dialogBuilder.setView(dialogView)
            dialogBuilder.setCancelable(false)
            dialog = dialogBuilder.create()
            if (dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
        }
    }
}
