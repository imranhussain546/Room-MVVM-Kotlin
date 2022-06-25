package com.imran.colourmoon.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.imran.colourmoon.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        account.setOnClickListener {

            goToActivity(this,AccountActivity::class.java,"")
           
        }
        hr.setOnClickListener {
            goToActivity(this,HrActivity::class.java,"")
        }
        maintain.setOnClickListener {
            goToActivity(this,MaintenActivity::class.java,"")
        }
    }

   companion object{
       fun goToActivity(activity: Activity, classs: Class<*>?,type:String) {
           activity?.let {
               val intent = Intent(it, classs)
               intent.putExtra("type",type)
               it.startActivity(intent)
           }
       }
   }

//    private fun loadFragment(fragment: Fragment){
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.main, fragment)
//        transaction.disallowAddToBackStack()
//        transaction.commit()
//    }
}