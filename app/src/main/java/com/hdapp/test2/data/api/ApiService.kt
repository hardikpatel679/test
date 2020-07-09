package com.hdapp.frameworkkotlin.data.api


import com.hdapp.test2.data.model.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getData(): Response<DataResponse>



}