package com.example.rbc_android_takehome_adam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.rbc_android_takehome_adam.databinding.FragmentAccountDetailsBinding
import com.example.rbc_android_takehome_adam.models.AccountData
import com.example.rbc_android_takehome_adam.models.AccountDetailsViewModel

class AccountDetailsFragment : Fragment() {
    private var _binding: FragmentAccountDetailsBinding? = null
    private val accountsDetailViewModel: AccountDetailsViewModel by activityViewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAccountDetails(accountsDetailViewModel.currentAccountData)
        accountsDetailViewModel.getTransactionsForAccount()
    }

    private fun setAccountDetails(accountData: AccountData) {
        binding.accountName.text = accountData.name
        binding.accountNumber.text = accountData.number
        binding.accountBalance.text = accountData.balance
    }
}