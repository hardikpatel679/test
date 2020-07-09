package com.hdapp.test2.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Row (
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0,
    var description: String?,
    var imageHref: String?,
    var title: String?
)