package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.network.entities.GasPriceEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    var store: MutableMap<String, String> = mutableMapOf<String, String>()
    var prices = MutableLiveData<MutableMap<String, Float>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationBar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_holder) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.history -> {
                    navController.popBackStack(R.id.history2, false)
                }
                R.id.add -> {
                    if (navController.currentBackStackEntry?.getFragmentName() != "add") navController.navigate(
                        R.id.add2
                    )
                }
                R.id.prices -> {
                    Log.i("", "onCreate: " + navController.currentBackStackEntry?.getFragmentName())
                    if (navController.currentBackStackEntry?.getFragmentName() != "priceListFragment") navController.navigate(
                        R.id.priceListFragment
                    )
                }
            }
            true
        }

    }

    private fun NavBackStackEntry.getFragmentName(): String =
        this.destination.label.toString().replace("fragment_", "")
}