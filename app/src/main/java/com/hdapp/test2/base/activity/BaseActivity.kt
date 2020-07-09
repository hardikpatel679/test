package com.hdapp.frameworkkotlin.base.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hdapp.frameworkkotlin.data.api.ApiService
import com.hdapp.frameworkkotlin.data.db.AppDatabse
import com.hdapp.frameworkkotlin.utils.toast

import com.hdapp.test2.data.api.ServiceGenerator


open class BaseActivity : AppCompatActivity() {
    lateinit var api: ApiService
    lateinit var db: AppDatabse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        api = ServiceGenerator.createService(ApiService::class.java)
        db = AppDatabse.invoke(this)
    }

    fun handleHTTPCode(code: Int?) {
        when (code) {
            404 -> {
                toast("No Service Available")
            }
            401 -> {
                toast("Unauthorized Request")
            }
        }
    }

    fun isNetworkConnected(): Boolean {

        var result = false
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

}