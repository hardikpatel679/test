package com.hdapp.test2.data.model

import com.hdapp.test2.data.db.Row

data class DataResponse(
    val rows: List<Row>,
    val title: String
)