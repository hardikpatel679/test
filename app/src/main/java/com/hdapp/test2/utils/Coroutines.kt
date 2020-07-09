package com.hdapp.frameworkkotlin.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines  {

    fun io(functionCall:suspend (() ->Unit)) =  CoroutineScope(Dispatchers.IO).launch {
        functionCall()
    }

    fun main(functionCall:suspend (() ->Unit)) =  CoroutineScope(Dispatchers.Main).launch {
        functionCall()
    }

    fun default(functionCall:suspend (() ->Unit)) =  CoroutineScope(Dispatchers.Default).launch {
        functionCall()
    }

    fun unconfined(functionCall:suspend (() ->Unit)) =  CoroutineScope(Dispatchers.Unconfined).launch {
        functionCall()
    }
}