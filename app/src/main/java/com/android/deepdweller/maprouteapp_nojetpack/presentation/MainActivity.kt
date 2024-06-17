package com.android.deepdweller.maprouteapp_nojetpack.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.deepdweller.maprouteapp_nojetpack.R
import com.android.deepdweller.maprouteapp_nojetpack.databinding.ActivityMainBinding
import com.android.deepdweller.maprouteapp_nojetpack.presentation.map.ApiRegistrationFragment
import com.android.deepdweller.maprouteapp_nojetpack.presentation.map.MapViewModel
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Init
    private val viewModel: MapViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var api: String? = null

    //LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        //MapInfoInit

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        hostFragment()
    }



    private fun hostFragment(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val mapFragment = ApiRegistrationFragment()

        fragmentTransaction.replace(R.id.root_host, mapFragment)
        fragmentTransaction.commit()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()

    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
    }
}