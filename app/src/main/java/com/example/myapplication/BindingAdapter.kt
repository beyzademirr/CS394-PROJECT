package com.example.myapplication

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.entities.Purchase
import com.example.myapplication.purchaseList.PurchaseListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Purchase>?) {
    val adapter = recyclerView.adapter as PurchaseListAdapter
    adapter.submitList(data)
}