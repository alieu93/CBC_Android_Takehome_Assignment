package com.example.rbc_android_takehome_adam.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rbc.rbcaccountlibrary.AccountProvider

class AccountsViewModelFactory(private val accountProvider: AccountProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountsViewModel::class.java)) {
            return AccountsViewModel(accountProvider) as T
        }
        throw IllegalArgumentException("ViewModel not found.")
    }
}