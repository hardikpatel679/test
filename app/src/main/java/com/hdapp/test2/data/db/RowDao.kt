package com.hdapp.frameworkkotlin.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hdapp.test2.data.db.Row


@Dao
interface RowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(lstRow: List<Row>)


    @Query("SELECT * FROM Row")
    fun getDataList():LiveData<List<Row>>
}