package com.example.rbc_android_takehome_adam

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rbc_android_takehome_adam.data.AccountData
import com.example.rbc_android_takehome_adam.databinding.ActivityAccountDetailsBinding
import com.example.rbc_android_takehome_adam.models.*
import com.rbc.rbcaccountlibrary.AccountProvider

class AccountDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountDetailsBinding
    private lateinit var viewModel: AccountDetailsViewModel
    private lateinit var viewModelFactory: AccountDetailsViewModelFactory

    companion object {
        private const val INTENT_ACCOUNT_DATA = "intent_account_data"

        fun getIntent(context: Context, accountData: AccountData): Intent {
            val intent = Intent(context, AccountDetailsActivity::class.java)
            intent.putExtra(INTENT_ACCOUNT_DATA, accountData)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccountDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.title = getString(R.string.account_details_activity_label)
        window.statusBarColor = resources.getColor(R.color.blue)

        viewModelFactory = AccountDetailsViewModelFactory(AccountProvider)
        viewModel = ViewModelProvider(this, viewModelFactory)[AccountDetailsViewModel::class.java]
        viewModel.currentAccountData = intent.getSerializableExtra(INTENT_ACCOUNT_DATA) as AccountData
    }
}