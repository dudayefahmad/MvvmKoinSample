package com.example.mvvmkoinsample.util

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.TextView
import com.example.mvvmkoinsample.R
import com.google.android.material.snackbar.Snackbar




@Suppress("DEPRECATION")
fun Context.showSnackbar(view: View, message: Int): Snackbar {
    return Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
        show()
    }
}

@Suppress("DEPRECATION")
fun Context.showSnackbar2(view: View, message: String): Snackbar {
    return Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
        show()
    }
}



@Suppress("DEPRECATION")
fun Context.showErrorSnackbar(view: View, message: Int): Snackbar {
    return Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getView().setBackgroundColor(resources.getColor(R.color.error_red, theme))
        } else {
            getView().setBackgroundColor(resources.getColor(R.color.error_red))
        }
        getView().findViewById<TextView>(R.id.snackbar_text).setTextColor(Color.WHITE)
        show()
    }
}
