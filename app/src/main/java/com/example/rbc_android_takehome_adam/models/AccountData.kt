package com.example.rbc_android_takehome_adam.models

import java.io.Serializable

data class AccountData(
    val balance: String,
    val name: String,
    val number: String,
    val type: AccountDataType
) : Serializable