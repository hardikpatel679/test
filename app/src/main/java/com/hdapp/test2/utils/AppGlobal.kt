package com.hdapp.frameworkkotlin.utils

import android.util.Log
import java.util.logging.Logger

class AppGlobal {

    companion object {
        val TAG: String = AppGlobal::class.java.simpleName
        @JvmStatic
        fun print(TAG: String, message: String?) {
            message?.let {
                Log.d(TAG, message)
            }

        }
    }
}