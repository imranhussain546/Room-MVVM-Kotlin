package com.imran.colourmoon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imran.colourmoon.R
import com.imran.colourmoon.model.DepartmentModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.viewholder.view.*
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat
import java.util.*
import kotlin.collections.ArrayList


class DepartmentAdapter (val context: Context):RecyclerView.Adapter<DepartmentAdapter.ViewHolder>(){

    val departmentList = ArrayList<DepartmentModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(departmentList.get(position).name)
        holder.branch.setText(departmentList.get(position).branch)
        Glide
            .with(context)
            .load(departmentList.get(position).profileImg)
            .centerCrop()

            .into(holder.imageView);

        holder.locationBtn.setOnClickListener {

            var lat:Double=departmentList.get(position).lat.toDouble()
            var lng:Double=departmentList.get(position).lng.toDouble()
            Log.e("latlong", "onBindViewHolder: "+lat+" "+lng )
            val uri: String = java.lang.String.format(
                Locale.ENGLISH,
                "http://maps.google.com/maps?q=loc:%f,%f",
                lat,
                lng
            )
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return departmentList.size
    }
    fun updateList(myList: List<DepartmentModel>)
    {
        departmentList.clear()
        departmentList.addAll(myList)
        notifyDataSetChanged()
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: CircleImageView = itemView.profilePic
        val name: TextView = itemView.name
        val branch: TextView = itemView.branch
        val locationBtn:AppCompatTextView=itemView.location
    }
}