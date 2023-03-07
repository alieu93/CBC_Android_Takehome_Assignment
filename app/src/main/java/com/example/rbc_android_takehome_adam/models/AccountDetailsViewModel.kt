package com.example.rbc_android_takehome_adam.models

import androidx.lifecycle.ViewModel
import com.rbc.rbcaccountlibrary.AccountProvider

class AccountDetailsViewModel(private val accountProvider: AccountProvider) : ViewModel() {
    private lateinit var _currentAccountData: AccountData
    var currentAccountData: AccountData
        get() = _currentAccountData
        set(value) {
            _currentAccountData = value
        }


}