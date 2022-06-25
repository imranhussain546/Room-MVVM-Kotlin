package com.imran.colourmoon.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "departments")
 class DepartmentModel(

    @ColumnInfo(name = "type")
    val type:String,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "branch")
    val branch:String,
    @ColumnInfo(name = "lat")
    val lat:String,
    @ColumnInfo(name = "long")
    val lng:String,
    @ColumnInfo(name = "img")
    val profileImg: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}