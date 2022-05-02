package com.ncasatti.meliproductssearcher.ui.view

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ncasatti.meliproductssearcher.R
import com.ncasatti.meliproductssearcher.core.mainLogTag
import com.ncasatti.meliproductssearcher.databinding.ActivityMainBinding
import com.ncasatti.meliproductssearcher.ui.viewmodel.SearchViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val logTag = "$mainLogTag MainActivity | "

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("$logTag onCreate()", "Aplicacion iniciada")

        setSupportActionBar(binding.toolbar)


        binding.toolbar.title = ""
        searchViewModel.actionBarTittle.observe(this, Observer { binding.toolbar.title = it })

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("$logTag onDestroy()", "Cerrando aplicación")
    }
}