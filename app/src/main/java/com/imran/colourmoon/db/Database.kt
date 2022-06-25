package com.imran.colourmoon.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.imran.colourmoon.model.DepartmentModel


@androidx.room.Database(entities = arrayOf(DepartmentModel::class), version = 1, exportSchema = false)
abstract class Database : RoomDatabase(){

    abstract fun getDepartmentDao():DepartmentDao

    companion object{
        private var INSTANCE:Database?=null

        fun getDatabase(context: Context):Database{
            return INSTANCE?: synchronized(this){
                var instance = Room.databaseBuilder(context,Database::class.java,"department_database")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}