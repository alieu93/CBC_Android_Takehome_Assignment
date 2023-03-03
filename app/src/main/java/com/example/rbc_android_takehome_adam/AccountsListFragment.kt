package com.example.rbc_android_takehome_adam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rbc_android_takehome_adam.databinding.FragmentAccountsListBinding
import com.example.rbc_android_takehome_adam.models.AccountsViewModel
import com.example.rbc_android_takehome_adam.views.AccountsListAdapter

class AccountsListFragment : Fragment() {

    private var _binding: FragmentAccountsListBinding? = null
    private val accountsViewModel: AccountsViewModel by activityViewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        accountsViewModel.getAccounts()
        accountsViewModel.accountsLiveDataList.observe(viewLifecycleOwner) { accountList ->
            binding.accountsListRecyclerView.adapter = AccountsListAdapter(accountList)
            binding.accountsListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.accountsListRecyclerView.setHasFixedSize(true)
            binding.accountsListRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}