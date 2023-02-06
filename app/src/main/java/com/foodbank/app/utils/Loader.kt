package com.foodbank.app.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.foodbank.app.R

class Loader private constructor() {
    private var loadingDialog: Dialog? = null

    companion object {
        private var instance: Loader? = null

        fun getInstance(): Loader {
            if (instance == null) {
                instance = Loader()
            }
            return instance!!
        }
    }

    fun showLoading(context: Context) {
        loadingDialog = Dialog(context)
        loadingDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingDialog!!.setCancelable(false)
        loadingDialog!!.setContentView(R.layout.loading_dialog)
        loadingDialog!!.show()
    }

    fun hideLoading() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
            loadingDialog = null
        }
    }
}
