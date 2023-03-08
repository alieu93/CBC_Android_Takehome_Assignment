package com.example.rbc_android_takehome_adam.data

import java.util.*

sealed class TransactionRow(val type: TransactionRowType) {
    data class TransactionData(val amount: String, val date: Calendar, val description: String)
        : TransactionRow(TransactionRowType.TransactionData)

    data class TransactionHeader(val date: String) : TransactionRow(TransactionRowType.TransactionHeader)
}

enum class TransactionRowType {
    TransactionData,
    TransactionHeader
}