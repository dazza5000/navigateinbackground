package com.example.navigateinbackgroundfragmentviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.navigateinbackgroundfragmentviewmodel.ui.main.MainFragment
import com.example.navigateinbackgroundfragmentviewmodel.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setViewModelDeepLinkValue(intent)
        setupDestinationChangedListener()

    }

    private fun setViewModelDeepLinkValue(intent: Intent?) {
        intent?.data?.let { uri ->
            viewModel.updateDeeplink(uri)
        }
        // This line stops automatic redirection to deep links
        intent?.data = null
    }

    private fun setupDestinationChangedListener() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController
            .addOnDestinationChangedListener { _, destination,
                                                                                 _ ->
            Log.d("darran","Destination: $destination")
        }
    }
}
