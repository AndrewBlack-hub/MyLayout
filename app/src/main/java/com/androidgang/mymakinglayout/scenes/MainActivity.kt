package com.androidgang.mymakinglayout.scenes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.androidgang.mymakinglayout.R
import com.androidgang.mymakinglayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var bottomNavigationView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        bottomNavigationView = binding.bottomNavigationMenu

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        setupNavigationController(navController)
    }

    private fun setupNavigationController(navController: NavController) {
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}