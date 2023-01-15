package com.example.myapplication.purchaseList


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.database.PurchaseDatabase
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.network.GasPricesRepo
import com.example.myapplication.enums.storeEnums
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PurchaseListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list, container, false
        )


        val db = PurchaseDatabase.getInstance(requireContext())

        val application = requireNotNull(this.activity).application

        val press: (Int) -> (Int) = { pos: Int ->
            (activity as MainActivity).store.put(storeEnums.selectedPurchase.tag, pos.toString())
            requireView().findNavController().navigate(R.id.action_history_to_add)
            pos
        }

        val longPress: (Int, PurchaseListAdapter) -> (Boolean) =
            { pos: Int, adapter: PurchaseListAdapter ->

                CoroutineScope(Dispatchers.IO).launch {
                    db.dao().deletePurchase(pos)
                    adapter.submitList(db.dao().getAllPurchases())
                }



                true
            }

        var listener1 = PurchaseClickListener(press)

        var listener2 = PurchaseLongClickListener(longPress)


        val adapter = PurchaseListAdapter(listener1, listener2)

        binding.historyList.adapter = adapter

        var purchaseDAO = db.dao()
        val purchaseListViewModel: PurchaseListViewModel by viewModels() {
            PlantListViewModelFactory(
                purchaseDAO, application, db
            )
        }



        binding.vm = purchaseListViewModel
       // CoroutineScope(Dispatchers.IO).launch { adapter.submitList(db.dao().getAllPurchases()) }

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        GasPricesRepo().getPrices((activity as MainActivity).prices)
    }
}