package com.imran.colourmoon.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.imran.colourmoon.R
import com.imran.colourmoon.adapter.DepartmentAdapter
import com.imran.colourmoon.viewmodel.DepartmentViewModel
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.activity_account.backBtn
import kotlinx.android.synthetic.main.activity_account.floatingActionButton
import kotlinx.android.synthetic.main.activity_account.recyclerView
import kotlinx.android.synthetic.main.activity_account.total
import kotlinx.android.synthetic.main.activity_hr.*

class HrActivity : AppCompatActivity() {
    lateinit var departmentViewModel: DepartmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hr)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val departmentAdapter = DepartmentAdapter(this)
        recyclerView.adapter = departmentAdapter
        departmentViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(DepartmentViewModel::class.java)
        departmentViewModel.getHrList.observe(this,{list->
            list?.let {
                total.text="Total Employee:-  "+it.size
                Log.e("datata", "onCreate: "+it.size)
                departmentAdapter.updateList(it)
            }})
        floatingActionButton.setOnClickListener {
            MainActivity.goToActivity(this,AddEmployeeActivity::class.java,"hr")
        }

        backBtn.setOnClickListener {
            onBackPressed()
        }
    }
}