package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.findNavController
import com.example.myapplication.database.PurchaseDatabase
import com.example.myapplication.database.entities.Purchase
import com.example.myapplication.enums.storeEnums
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddPurchaseFragment : Fragment() {
    private lateinit var descText: EditText
    private lateinit var amountText: EditText
    private lateinit var unitText: EditText
    private lateinit var totalText: EditText
    private lateinit var db: PurchaseDatabase
    private var purchaseId: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = PurchaseDatabase.getInstance(requireContext());

        descText = view.findViewById(R.id.descTextEdit)
        amountText = view.findViewById(R.id.amountTextEdit)
        unitText = view.findViewById(R.id.unitTextEdit)
        totalText = view.findViewById(R.id.totalTextEdit)

        amountText.doAfterTextChanged {
            calculateFromUnit()
        }
        unitText.doAfterTextChanged {
            calculateFromUnit()
        }

        val dbindex =
            if ((activity as MainActivity).store.contains(storeEnums.selectedPurchase.tag)) (activity as MainActivity).store.get(
                storeEnums.selectedPurchase.tag
            )?.toInt() else -1
        loadPurchase(dbindex!!)
        val loadprice =
            if ((activity as MainActivity).store.contains(storeEnums.selectedPrice.tag)) (activity as MainActivity).store.get(
                storeEnums.selectedPrice.tag
            )?.toFloat() else -1f
        if (loadprice != -1f) unitText.text = loadprice.toString().toEditable()

        val saveButton = view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            savePurchase()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireView().findNavController().popBackStack(R.id.history2, true)
                }
            })
    }

    private fun savePurchase() {
        if (unitText.text.toString() == "" || unitText.text.toFloat() == 0f || amountText.text.toString() == "" || amountText.text.toFloat() == 0f || totalText.text.toString() == "" || totalText.text.toFloat() == 0f || descText.text.toString() == "") {
            Toast.makeText(
                requireContext(), "Please fill all the areas correctly!", Toast.LENGTH_LONG
            ).show()
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                val savedPurchase = Purchase(
                    descText.text.toString(),
                    unitText.text.toFloat(),
                    amountText.text.toFloat(),
                    System.currentTimeMillis(),
                    totalText.text.toFloat()
                )
                if (purchaseId != -1) savedPurchase.id = purchaseId
                db.dao().savePurchase(savedPurchase)
            }
            clearTextBoxes()
            requireView().findNavController().popBackStack(R.id.history2, false)
        }
    }

    private fun clearTextBoxes() {
        descText.text = "Gas Purchase".toEditable()
        unitText.text = "".toEditable()
        amountText.text = "".toEditable()
        totalText.text = "".toEditable()
        (activity as MainActivity).store.put(storeEnums.selectedPurchase.tag, (-1).toString())
        (activity as MainActivity).store.put(storeEnums.selectedPrice.tag, (-1f).toString())
    }

    private fun loadPurchase(index: Int) {
        if (index == -1) {
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val purchase: Purchase = db.dao().getPurchase(index)
                purchaseId = purchase.id!!
                descText.text = purchase.description.toEditable()
                unitText.text = purchase.unitPrice.toString().toEditable()
                amountText.text = purchase.amount.toString().toEditable()
                totalText.text = purchase.totalPrice.toString().toEditable()
            } catch (e: Exception) {
                Log.i("loadPurchase: ", "load failed, index: " + (index))
            }
        }
    }

    fun calculateFromUnit() {
        if (amountText.text.toString().isNotBlank()) {
            if (unitText.text.toString() != "") {
                if (totalText.text.toString() != "") {
                    if (totalText.text.toFloat() != amountText.text.toFloat() * unitText.text.toFloat()) {
                        totalText.text =
                            (amountText.text.toFloat() * unitText.text.toFloat()).toString()
                                .toEditable()
                    }
                } else {
                    totalText.text =
                        (amountText.text.toFloat() * unitText.text.toFloat()).toString()
                            .toEditable()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearTextBoxes()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    private fun Editable.toFloat(): Float = this.toString().toFloat()
    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}