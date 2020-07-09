package com.hdapp.frameworkkotlin.data.model

import com.hdapp.frameworkkotlin.utils.Status
import java.lang.Exception


data class Resource<out T>(val httpStatus: Int,val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(0,Status.SUCCESS, data, null)
        }

        fun <T> error(httpStatus: Int, msg: String, data: T?): Resource<T> {
            return Resource(httpStatus,Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(0,Status.LOADING, data, null)
        }

    }

}