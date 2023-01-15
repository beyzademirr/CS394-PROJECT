package com.example.myapplication.priceList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.PriceItemBinding
import com.example.myapplication.network.entities.GasPriceEntity

class PriceListAdapter(var clickFunction: PriceClickListener) :
    ListAdapter<GasPriceEntity, PriceListAdapter.ItemViewHolder>(PlantDiffCallback()) {

    class ItemViewHolder(val binding: PriceItemBinding) : RecyclerView.ViewHolder(binding.root) {
        //private var purchaseid: Int? = 0

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val binding =
                    PriceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ItemViewHolder(binding)
            }
        }

        fun bind(price: GasPriceEntity, clickFunction: PriceClickListener) {
            //purchaseid = purchase.id
            binding.price = price
            binding.clickListener = clickFunction
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): PriceListAdapter.ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PriceListAdapter.ItemViewHolder, position: Int) {
        val price = getItem(position)
        holder.bind(price, clickFunction)
    }
}

class PlantDiffCallback : DiffUtil.ItemCallback<GasPriceEntity>() {
    override fun areItemsTheSame(oldItem: GasPriceEntity, newItem: GasPriceEntity): Boolean {
        return oldItem.price == newItem.price
    }

    override fun areContentsTheSame(oldItem: GasPriceEntity, newItem: GasPriceEntity): Boolean {
        return oldItem == newItem
    }
}

class PriceClickListener(var clickFunction: (GasPriceEntity) -> GasPriceEntity) {
    fun press(id: GasPriceEntity) = clickFunction(id)
}

