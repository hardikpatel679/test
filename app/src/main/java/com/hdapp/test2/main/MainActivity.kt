package com.hdapp.test2.ui.main


import android.os.Bundle
import com.hdapp.frameworkkotlin.base.activity.BaseActivity
import com.hdapp.test2.R

import com.hdapp.test2.events.TitleListener
import com.hdapp.test2.events.abc


class MainActivity : BaseActivity(), TitleListener {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "";
    }

    /**
     * this function set the title name fatch from remote repository.
     */
    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }






}