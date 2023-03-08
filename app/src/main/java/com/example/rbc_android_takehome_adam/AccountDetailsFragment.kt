package com.example.rbc_android_takehome_adam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rbc_android_takehome_adam.databinding.FragmentAccountDetailsBinding
import com.example.rbc_android_takehome_adam.data.AccountData
import com.example.rbc_android_takehome_adam.models.AccountDetailsViewModel
import com.example.rbc_android_takehome_adam.views.TransactionListAdapter

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
        binding.transactionError.retryButton.setOnClickListener {
            showError(false)
            showLoading(true)
            accountsDetailViewModel.getTransactionsForAccount()
        }

        accountsDetailViewModel.allTransactionList.observe(viewLifecycleOwner) { transactions ->
            showLoading(false)
            if (transactions.isEmpty()) {
                binding.transactionsNotFoundText.visibility = View.VISIBLE
            } else {
                binding.transactionListRecyclerView.adapter = TransactionListAdapter(transactions)
                binding.transactionListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.transactionListRecyclerView.setHasFixedSize(true)
                binding.transactionListRecyclerView.visibility = View.VISIBLE
            }
        }

        accountsDetailViewModel.showTransactionError.observe(viewLifecycleOwner) { showTransactionError ->
            //TODO Implement error UI and show it
            showError(showTransactionError)
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

    private fun showError(hasError: Boolean) {
        if (hasError) {
            showLoading(false)
            binding.transactionError.transactionErrorLayout.visibility = View.VISIBLE
        } else {
            binding.transactionError.transactionErrorLayout.visibility = View.GONE
        }
    }

    private fun setAccountDetails(accountData: AccountData) {
        binding.accountName.text = accountData.name
        binding.accountNumber.text = accountData.number
        binding.accountBalance.text = accountData.balance
    }
}