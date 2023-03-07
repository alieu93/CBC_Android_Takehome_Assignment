package com.example.rbc_android_takehome_adam.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rbc.rbcaccountlibrary.AccountProvider

class AccountDetailsViewModelFactory(private val accountProvider: AccountProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountDetailsViewModel::class.java)) {
            return AccountDetailsViewModel(accountProvider) as T
        }
        throw IllegalArgumentException("ViewModel not found.")
    }
}