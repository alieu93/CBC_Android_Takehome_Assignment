package com.example.rbc_android_takehome_adam.models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rbc_android_takehome_adam.data.AccountData
import com.example.rbc_android_takehome_adam.data.AccountDataType
import com.rbc.rbcaccountlibrary.Account
import com.rbc.rbcaccountlibrary.AccountProvider
import com.rbc.rbcaccountlibrary.AccountType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountsViewModel(private val accountProvider: AccountProvider) : ViewModel() {
    private var _accountsList: List<Account> = emptyList()
    val accountsLiveDataList = MutableLiveData<List<AccountListViewItem>>()

    private val exceptionHandler = CoroutineExceptionHandler { _, t ->
        t.printStackTrace()
        onError("Exception: ${t.localizedMessage}")
    }

    fun getAccounts() {
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            _accountsList = accountProvider.getAccountsList()

            val sortedList = _accountsList.sortedBy { account ->
                account.type
            }

            val list = getAccountListViewItemList(sortedList)
            accountsLiveDataList.postValue(list)
        }
    }

    private fun getAccountListViewItemList(sortedAccountList: List<Account>): List<AccountListViewItem> {
        val accountListViewItems = mutableListOf<AccountListViewItem>()
        var currentType: AccountType? = null

        for (account in sortedAccountList) {
            if (currentType != account.type) {
                currentType = account.type
                accountListViewItems.add(AccountListViewItem.AccountHeader(AccountDataType.valueOf(account.type.toString())))
            }
            accountListViewItems.add(AccountListViewItem.AccountListData(AccountData(account.balance, account.name, account.number, AccountDataType.valueOf(account.type.toString()))))
        }

        return accountListViewItems
    }

    private fun onError(message: String) {
        Log.e("Error", message)
    }
}