package com.example.myapplication.purchaseList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListItemBinding
import com.example.myapplication.database.entities.Purchase

class PurchaseListAdapter(
    var clickFunction: PurchaseClickListener, var longClickFunction: PurchaseLongClickListener
) : ListAdapter<Purchase, PurchaseListAdapter.ItemViewHolder>(
    purchaseListCallback()
) {

    class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private var purchaseid: Int? = 0

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val binding =
                    ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ItemViewHolder(binding)
            }
        }

        fun bind(
            purchase: Purchase,
            clickFunction: PurchaseClickListener,
            longClickFunction: PurchaseLongClickListener,
            adapter: PurchaseListAdapter
        ) {
            purchaseid = purchase.id
            binding.purchase = purchase
            binding.clickListener = clickFunction
            binding.longClickListener = longClickFunction
            binding.adapter = adapter
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val purchase = getItem(position)
        holder.bind(purchase, clickFunction, longClickFunction, this)
    }
}

class purchaseListCallback : DiffUtil.ItemCallback<Purchase>() {
    override fun areItemsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
        return oldItem == newItem
    }
}

class PurchaseClickListener(var clickFunction: (Int) -> (Int)) {
    fun press(id: Int) = clickFunction(id)
}

class PurchaseLongClickListener(var clickFunction: (Int, PurchaseListAdapter) -> Boolean) {
    fun press(id: Int, adapter: PurchaseListAdapter) = clickFunction(id, adapter)
}



