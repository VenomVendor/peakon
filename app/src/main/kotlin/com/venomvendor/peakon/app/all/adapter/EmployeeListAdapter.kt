/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.app.all.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.venomvendor.peakon.R
import com.venomvendor.peakon.app.common.factory.OnItemClickListener
import com.venomvendor.peakon.app.core.GlideApp
import com.venomvendor.peakon.app.util.DiffUtilHelper
import com.venomvendor.peakon.employee.model.Employee

/**
 * Recycler Adapter for displaying list of results.
 */
internal class EmployeeListAdapter(context: Context) :
    ListAdapter<Employee, EmployeeListAdapter.EmployeeViewHolder>(DiffUtilHelper.EMPLOYEE_DIFF) {

    private val glide by lazy { GlideApp.with(context) }

    // Event listener
    private var mItemClickListener: OnItemClickListener<Employee>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            // Update item view
            .inflate(R.layout.team_item, parent, false)

        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        // Get item
        val employee = getItem(position)

        // Update views
        holder.employeeName.text = employee.attributes.name

        holder.teamType.text = employee.attributes.jobLevel
        holder.teamId.text = employee.attributes.gender

        // Load image
        glide.load("https://api.adorable.io/avatars/${employee.id}")
            .centerCrop()
            // place holder until image loads
            .placeholder(R.drawable.circle_bg)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.media)
    }

    fun setOnItemClickLister(listener: OnItemClickListener<Employee>?) {
        mItemClickListener = listener
    }

    /**
     * View Holder pattern, used by recycler view.
     */
    internal inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        internal val employeeName = itemView.findViewById<TextView>(R.id.team_name)
        internal val teamType = itemView.findViewById<TextView>(R.id.team_type)
        internal val teamId = itemView.findViewById<TextView>(R.id.team_id)
        internal val media = itemView.findViewById<ImageView>(R.id.media)

        init {
            // Add event listener
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val selectedPos = adapterPosition
            if (selectedPos == RecyclerView.NO_POSITION) {
                return
            }

            // Pass on to root level event listener
            mItemClickListener?.onClick(getItem(selectedPos), view, selectedPos)
        }
    }
}
