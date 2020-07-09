package com.hdapp.frameworkkotlin.data.api

sealed class ResponseResult<out T> {

    data class Success<T>(val result: T?): ResponseResult<T>()
    data class Error<T>(val error: T): ResponseResult<T>()
    object Loading : ResponseResult<Nothing>()


}

data class ResponseWrapper<out T>(val data: T?, val errorMsg: String?,val errorCode: Int?)