package com.imran.colourmoon.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.imran.colourmoon.model.DepartmentModel

@Dao
interface DepartmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(departmentModel: DepartmentModel)

    @Update
    suspend fun update(departmentModel: DepartmentModel)



    @Query("select * from departments where type='account'")
    fun getAllList(): LiveData<List<DepartmentModel>>

    @Query("select * from departments where type='hr'")
    fun getHrList(): LiveData<List<DepartmentModel>>

    @Query("select * from departments where type='main'")
    fun getMainteList(): LiveData<List<DepartmentModel>>
}