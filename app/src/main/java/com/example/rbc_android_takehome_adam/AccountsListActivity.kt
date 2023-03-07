package com.example.rbc_android_takehome_adam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.rbc_android_takehome_adam.databinding.ActivityMainBinding
import com.example.rbc_android_takehome_adam.models.AccountsViewModel
import com.example.rbc_android_takehome_adam.models.AccountsViewModelFactory
import com.rbc.rbcaccountlibrary.AccountProvider

class AccountsListActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AccountsViewModel
    private lateinit var viewModelFactory: AccountsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.white))

        viewModelFactory = AccountsViewModelFactory(AccountProvider)
        viewModel = ViewModelProvider(this, viewModelFactory)[AccountsViewModel::class.java]

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}