package com.imran.colourmoon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imran.colourmoon.db.Database
import com.imran.colourmoon.model.DepartmentModel
import com.imran.colourmoon.repositery.DepartmentRepositery
import kotlinx.coroutines.launch

class DepartmentViewModel(application: Application) : AndroidViewModel(application){

    val getList : LiveData<List<DepartmentModel>>
    val getHrList : LiveData<List<DepartmentModel>>
    val getMainteList : LiveData<List<DepartmentModel>>
    val departmentRepositery:DepartmentRepositery

    init {
        val dao = Database.getDatabase(application).getDepartmentDao()
        departmentRepositery = DepartmentRepositery(dao)
        getList = departmentRepositery.getAllList
        getHrList = departmentRepositery.getHrList
        getMainteList = departmentRepositery.getMainteList
    }
    fun departmentInsert(departmentModel: DepartmentModel) = viewModelScope.launch {
        departmentRepositery.insert(departmentModel)
    }

    fun departmentUpdate(departmentModel: DepartmentModel) = viewModelScope.launch {
        departmentRepositery.update(departmentModel)
    }
}