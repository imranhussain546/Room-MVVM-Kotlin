package com.imran.colourmoon.repositery

import androidx.lifecycle.LiveData
import com.imran.colourmoon.db.DepartmentDao
import com.imran.colourmoon.model.DepartmentModel

class DepartmentRepositery(private val departmentDao: DepartmentDao) {

    val getAllList: LiveData<List<DepartmentModel>> = departmentDao.getAllList()
    val getHrList: LiveData<List<DepartmentModel>> = departmentDao.getHrList()
    val getMainteList: LiveData<List<DepartmentModel>> = departmentDao.getMainteList()

    suspend fun insert(departmentModel: DepartmentModel){
        departmentDao.insert(departmentModel)
    }

    suspend fun update(departmentModel: DepartmentModel){
        departmentDao.update(departmentModel)
    }
}