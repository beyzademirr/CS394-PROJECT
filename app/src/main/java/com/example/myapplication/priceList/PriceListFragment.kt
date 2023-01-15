package com.example.myapplication.priceList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.PriceListBinding
import com.example.myapplication.network.GasPricesRepo
import com.example.myapplication.network.entities.GasPriceEntity
import com.example.myapplication.enums.storeEnums
import com.google.gson.Gson

class PriceListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: PriceListBinding = DataBindingUtil.inflate(
            inflater, R.layout.price_list, container, false
        )

        GasPricesRepo().getPrices((activity as MainActivity).prices)

        val press: (GasPriceEntity) -> (GasPriceEntity) = { pos: GasPriceEntity ->
            (activity as MainActivity).store.put(
                storeEnums.selectedPrice.tag, Gson().toJson(pos.price)
            )
            requireView().findNavController().navigate(R.id.add2)
            pos
        }

        val listener1 = PriceClickListener(press)
        val adapter = PriceListAdapter(listener1)
        binding.priceList.adapter = adapter
        adapter.submitList((activity as MainActivity).prices.value?.toGasPriceList())

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        GasPricesRepo().getPrices((activity as MainActivity).prices)
    }

    fun MutableMap<String, Float>.toGasPriceList(): MutableList<GasPriceEntity>{
        val list: MutableList<GasPriceEntity> = mutableListOf<GasPriceEntity>()
        for(item in this)
            list.add(GasPriceEntity(item.key, item.value))
        return list;
    }
}
