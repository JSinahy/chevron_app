package com.trend.feature_common.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface

object AlertDialogHelper {
    fun basic(context: Activity,
                   title: String,
                   msg: String,
                   okTitle: String,
                   negativeTitle: String?,
                   positive: (DialogInterface, Int) -> Unit,
                   negative: (DialogInterface, Int) -> Unit,
                   isFinish: Boolean = false) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(okTitle, DialogInterface.OnClickListener(positive))
        if(!negativeTitle.isNullOrEmpty()){
            builder.setNegativeButton(negativeTitle, DialogInterface.OnClickListener(negative))
        }
        if(isFinish) {
            context.finish()
        }

        builder.show()
    }
}