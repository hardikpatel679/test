package com.hdapp.test2.events

import java.io.Serializable

interface TitleListener : Serializable {
    fun setTitle(title:String)
}