package com.hdapp.test2.main.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hdapp.frameworkkotlin.base.activity.BaseViewModel
import com.hdapp.frameworkkotlin.data.api.ApiService
import com.hdapp.frameworkkotlin.data.api.ResponseResult
import com.hdapp.frameworkkotlin.data.api.ResponseWrapper
import com.hdapp.frameworkkotlin.data.db.AppDatabse
import com.hdapp.frameworkkotlin.data.repository.DataRepository
import com.hdapp.frameworkkotlin.utils.Coroutines
import com.hdapp.test2.data.db.Row

import com.hdapp.test2.data.model.DataResponse

class MainViewModel(var api: ApiService, var db: AppDatabse) : BaseViewModel() {
   
    var dataResponse = MutableLiveData<ResponseResult<ResponseWrapper<DataResponse>>>()
    var issWipetoRefresh = MutableLiveData(false)

    fun isswipeToRefresh(isRefresh: Boolean) {
        issWipetoRefresh.postValue(isRefresh)
    }

    fun getRows(): LiveData<List<Row>> {
        return db.getDataDao().getDataList()
    }

    fun getData() {

        dataResponse.value = ResponseResult.Loading
        Coroutines.io {
            val tempResponse = DataRepository(api).getData()
            dataResponse.postValue(tempResponse)

            isswipeToRefresh(false)
            if (tempResponse is ResponseResult.Success) {
                tempResponse.result?.data?.let {
                    val filterList = it.rows.filter { it.description!= null }
                    db.getDataDao().insertData(filterList)
                }
            }
        }

    }
}