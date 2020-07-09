package com.hdapp.frameworkkotlin.data.repository

import com.hdapp.frameworkkotlin.data.api.*
import com.hdapp.test2.data.model.DataResponse


class DataRepository(val api: ApiService) : SafeApiRequest() {
    
    suspend fun getData(): ResponseResult<ResponseWrapper<DataResponse>> {
        return apiRequest { api.getData() }
    }
}