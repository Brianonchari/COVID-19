package com.studycode.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.studycode.recyclerview.R
import com.studycode.recyclerview.data.service.responses.CountriesItem
import com.studycode.recyclerview.databinding.RecyclerviewItemsBinding

class CountriesAdapter(private val cases: List<CountriesItem>) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    override fun getItemCount() = cases.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_items,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recyclerView.viewmodel = cases[position]
    }

    inner class ViewHolder(val recyclerView: RecyclerviewItemsBinding) :
        RecyclerView.ViewHolder(recyclerView.root) {
    }

}