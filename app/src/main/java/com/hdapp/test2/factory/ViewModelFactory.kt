package com.hdapp.frameworkkotlin.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hdapp.frameworkkotlin.data.api.ApiService
import com.hdapp.frameworkkotlin.data.db.AppDatabse
import com.hdapp.test2.main.fragment.MainViewModel

class ViewModelFactory(var api : ApiService,var db: AppDatabse) :ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(api,db) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}