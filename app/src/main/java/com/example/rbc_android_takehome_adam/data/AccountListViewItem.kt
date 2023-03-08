package com.example.rbc_android_takehome_adam.models

import com.example.rbc_android_takehome_adam.data.AccountData
import com.example.rbc_android_takehome_adam.data.AccountDataType

sealed class AccountListViewItem(val type: AccountListType) {
    data class AccountListData(val accountData: AccountData) : AccountListViewItem(AccountListType.AccountData)
    data class AccountHeader(val accountDataType: AccountDataType) : AccountListViewItem(AccountListType.AccountHeader)
}

enum class AccountListType {
    AccountData,
    AccountHeader
}
