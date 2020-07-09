package com.hdapp.frameworkkotlin.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.hdapp.test2.MyApplication


fun Context.print(message:String) {
    Log.d(this.javaClass.simpleName,message)
}

fun Fragment.toast(message: String){
    Toast.makeText(MyApplication.applicationContext(), message , Toast.LENGTH_SHORT).show();
}
fun Context.toast(message: String){
    Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
}

fun ProgressBar.show(){
    visibility = View.VISIBLE
}
fun ProgressBar.hide(){
    visibility = View.GONE
}
fun View.snackbar(message:String){
    Snackbar.make(this,message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("OK"){
            snackbar.dismiss()
        }
    }.show()
}

