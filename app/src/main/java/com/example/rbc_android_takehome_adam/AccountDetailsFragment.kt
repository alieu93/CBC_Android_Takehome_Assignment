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

        accountsDetailViewModel.allTransactionList.observe(viewLifecycleOwner) { transactions ->
            //TODO Implement RecyclerView with date headers and transactions themselves
            showLoading(false)
        }

        accountsDetailViewModel.showTransactionError.observe(viewLifecycleOwner) { showTransactionError ->
            //TODO Implement error UI and show it
        }

        accountsDetailViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingSpinner.visibility = View.VISIBLE
            binding.transactionListRecyclerView.visibility = View.GONE
        } else {
            binding.loadingSpinner.visibility = View.GONE
            binding.transactionListRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun setAccountDetails(accountData: AccountData) {
        binding.accountName.text = accountData.name
        binding.accountNumber.text = accountData.number
        binding.accountBalance.text = accountData.balance
    }
}