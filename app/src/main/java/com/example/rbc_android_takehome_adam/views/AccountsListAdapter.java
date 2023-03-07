package com.example.rbc_android_takehome_adam.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rbc_android_takehome_adam.AccountDetailsActivity;
import com.example.rbc_android_takehome_adam.R;
import com.example.rbc_android_takehome_adam.models.AccountData;
import com.example.rbc_android_takehome_adam.models.AccountDataType;
import com.example.rbc_android_takehome_adam.models.AccountListViewItem;
import com.rbc.rbcaccountlibrary.Account;

import java.util.List;

public class AccountsListAdapter extends RecyclerView.Adapter<AccountsListAdapter.BaseViewHolder>{
    private final List<AccountListViewItem> accountList;

    public AccountsListAdapter(List<AccountListViewItem> accountList) {
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());//.inflate(R.layout.view_account, parent, false);
        View view = layoutInflater.inflate(viewType, parent, false);
        if (viewType == R.layout.view_account) {
            return new AccountViewHolder(view);
        } else {
            return new HeaderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.setListData(accountList.get(position), position == 0, position == accountList.size() - 1);
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (accountList.get(position).component1() != null) {
            return R.layout.view_account_list_header;
        } else {
            return R.layout.view_account;
        }
    }

    public static class HeaderViewHolder extends BaseViewHolder {
        private final TextView headerTitleTextView;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTitleTextView = (TextView) itemView.findViewById(R.id.headerTitle);
        }

        @Override
        void setListData(AccountListViewItem accountListViewItem, boolean isFirst, boolean isLast) {
            String headerString = accountListViewItem.getHeader();
            assert accountListViewItem.component1() != null;
            headerTitleTextView.setText(headerString);
        }
    }

    public static class AccountViewHolder extends BaseViewHolder {
        private final TextView accountDisplayTextView;
        private final TextView accountNumberTextView;
        private final TextView accountBalanceTextView;
        private final View topDividerView;
        private final View bottomDividerView;
        private final View accountConstraintView;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            accountDisplayTextView = (TextView) itemView.findViewById(R.id.accountDisplayName);
            accountNumberTextView = (TextView) itemView.findViewById(R.id.accountNumber);
            accountBalanceTextView = (TextView) itemView.findViewById(R.id.accountBalance);
            topDividerView = itemView.findViewById(R.id.topDivider);
            bottomDividerView = itemView.findViewById(R.id.bottomDivider);
            accountConstraintView = itemView.findViewById(R.id.accountConstraintView);
        }

        @Override
        void setListData(AccountListViewItem accountListViewItem, boolean isFirst, boolean isLast) {
            Account account = accountListViewItem.getAccount();
            assert account != null;
            accountDisplayTextView.setText(account.getName());
            accountNumberTextView.setText(account.getNumber());
            accountBalanceTextView.setText(account.getBalance());
            topDividerView.setVisibility(isFirst ? View.GONE : View.VISIBLE);
            bottomDividerView.setVisibility(isLast ? View.VISIBLE : View.GONE);

            accountConstraintView.setOnClickListener(view -> {
                Context context = itemView.getContext();
                context.startActivity(AccountDetailsActivity.Companion.getIntent(context, buildAccountData(account)));
            });
        }

        private AccountData buildAccountData(Account account) {
            return new AccountData(account.getBalance(), account.getName(), account.getNumber(), AccountDataType.valueOf(account.getType().toString()));
        }
    }

    public abstract static class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void setListData(AccountListViewItem accountListViewItem, boolean isFirst, boolean isLast);
    }
}
