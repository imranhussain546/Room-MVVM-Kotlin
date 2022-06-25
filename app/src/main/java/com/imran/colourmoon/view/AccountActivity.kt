package com.imran.colourmoon.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.imran.colourmoon.R
import com.imran.colourmoon.adapter.DepartmentAdapter
import com.imran.colourmoon.viewmodel.DepartmentViewModel
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.activity_add_employee.*

class AccountActivity : AppCompatActivity() {
    lateinit var departmentViewModel: DepartmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val departmentAdapter = DepartmentAdapter(this)
        recyclerView.adapter = departmentAdapter
        departmentViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(DepartmentViewModel::class.java)
        departmentViewModel.getList.observe(this,{list->
            list?.let {
              total.text="Total Employee:-  "+it.size
                Log.e("datata", "onCreate: "+it.size)
                departmentAdapter.updateList(it)
            }})
        floatingActionButton.setOnClickListener {
           MainActivity.goToActivity(this,AddEmployeeActivity::class.java,"account")
        }
        backBtn.setOnClickListener {
            onBackPressed()
        }
    }






}