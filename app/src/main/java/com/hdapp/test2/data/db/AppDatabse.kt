package com.hdapp.frameworkkotlin.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hdapp.test2.R
import com.hdapp.test2.data.db.Row


@Database(entities = arrayOf(Row::class),version = 1)
abstract class AppDatabse : RoomDatabase() {

    abstract fun getDataDao():RowDao

    companion object{
        @Volatile
        private var instance:AppDatabse?= null
        private var LOCK = Any()

        operator fun invoke(context: Context)= instance?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance=it

            }
        }

        private fun buildDatabase(context: Context)
                = Room.databaseBuilder(context.applicationContext,AppDatabse::class.java,
            context.getString(R.string.app_name)+".db").build()
    }
}