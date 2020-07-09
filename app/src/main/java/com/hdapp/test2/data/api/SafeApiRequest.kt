package com.hdapp.frameworkkotlin.data.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

    protected suspend fun <T> apiRequest(call: suspend () -> Response<T>): ResponseResult<ResponseWrapper<T>> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (null != body) return ResponseResult.Success(ResponseWrapper(body, null,null))
            }
            return error(response.code(),"${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(code:Int?,msg: String): ResponseResult<ResponseWrapper<T>> {
        return ResponseResult.Error(ResponseWrapper(null, msg,code))
    }



}