
package com.imran.colourmoon.view

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.imran.colourmoon.R
import com.imran.colourmoon.model.DepartmentModel
import com.imran.colourmoon.viewmodel.DepartmentViewModel
import kotlinx.android.synthetic.main.activity_add_employee.*
import java.io.ByteArrayOutputStream

class AddEmployeeActivity : AppCompatActivity() {
    lateinit var departmentViewModel: DepartmentViewModel
    private val REQUEST_PERMISSION = 100
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PICK_IMAGE = 2
    private lateinit var name:String
    private lateinit var branch:String
    private lateinit var lat:String
    private lateinit var long:String
    private lateinit var uriImg:Uri
    private lateinit var img:String
    var acconttId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)
        var type:String = intent.getStringExtra("type").toString()
        departmentViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(DepartmentViewModel::class.java)
        profilePic.setOnClickListener {
            openCamera()
        }


        submit.setOnClickListener {
          if (valid())
          {
              name=userName.text.toString()
              branch=branchName.text.toString()
              lat=latText.text.toString()
              long=lngText.text.toString()

              val departmentModel = DepartmentModel(type,name,branch,lat,long,
                  uriImg.toString()
              )
              departmentViewModel.departmentInsert(departmentModel)
              myFun("Data Saved Successfully")
              finish()
          }
        }


    }
    private fun valid():Boolean{
        if (userName.text!!.isEmpty()){
            myFun("Enter name")
            return false
        }
        if (branchName.text!!.isEmpty()){
            myFun("Enter Branch")
            return false
        }
        if (latText.text!!.isEmpty()){
            myFun("Enter lat")
            return false
        }
        if (lngText.text!!.isEmpty()){
            myFun("Enter Long")
            return false
        }
        if (!this::uriImg.isInitialized){
            myFun("Please Capture Image")
            return false
        }
        return true
    }
    fun myFun( msg:String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    override fun onResume() {
        super.onResume()
        checkCameraPermission()
    }

    private fun openCamera() {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
//            intent.resolveActivity(packageManager)?.also {
//                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
//            }
//        }
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        uriImg = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImg)
        startActivityForResult(cameraIntent,REQUEST_IMAGE_CAPTURE )
    }

    private fun checkCameraPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//            != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                arrayOf(Manifest.permission.CAMERA),
//                REQUEST_PERMISSION)
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
                val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission, REQUEST_PERMISSION)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK) {
//            if (requestCode == REQUEST_IMAGE_CAPTURE) {
//                val bitmap = data?.extras?.get("data") as Bitmap
//                patientProfilePhoto.setImageBitmap(bitmap)
//                uriImg=getImageUriFromBitmap(this,bitmap)
//                Log.e("imggg", "onActivityResult: "+uriImg )
//            }
//            else if (requestCode == REQUEST_PICK_IMAGE) {
//                val uri = data?.getData()
//                patientProfilePhoto.setImageURI(uri)
//            }
//        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            patientProfilePhoto?.setImageURI(uriImg)

            Log.e("imggg", "onActivityResult: "+uriImg )

        }

    }
//    fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri{
//        val bytes = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
//        return Uri.parse(path.toString())
//    }

}