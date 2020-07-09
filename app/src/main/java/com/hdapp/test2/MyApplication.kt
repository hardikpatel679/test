package com.hdapp.test2

import android.app.Application

class MyApplication :Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: MyApplication
        fun applicationContext() : MyApplication {
            return instance.applicationContext as MyApplication
        }
    }
}