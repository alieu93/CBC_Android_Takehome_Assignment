package com.example.rbc_android_takehome_adam.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rbc_android_takehome_adam.R;
import com.example.rbc_android_takehome_adam.data.TransactionRow;

import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.BaseViewHolder>{
    private final List<TransactionRow> transactionRowList;

    public TransactionListAdapter(List<TransactionRow> transactionRowList) {
        this.transactionRowList = transactionRowList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(viewType, parent, false);
        if (viewType == R.layout.view_transaction) {
            return new TransactionViewHolder(view);
        } else {
            return new HeaderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.setData(transactionRowList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (transactionRowList.get(position) instanceof TransactionRow.TransactionData) {
            return R.layout.view_transaction;
        } else {
            return R.layout.view_list_header;
        }
    }

    @Override
    public int getItemCount() {
        return transactionRowList.size();
    }

    public static class TransactionViewHolder extends BaseViewHolder {
        private final TextView transactionDescriptionTextView;
        private final TextView transactionAmountTextView;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionDescriptionTextView = (TextView) itemView.findViewById(R.id.transactionDescription);
            transactionAmountTextView = (TextView) itemView.findViewById(R.id.transactionAmount);
        }

        @Override
        void setData(TransactionRow transactionRow) {
            TransactionRow.TransactionData data = (TransactionRow.TransactionData) transactionRow;
            transactionDescriptionTextView.setText(data.getDescription());
            transactionAmountTextView.setText(data.getAmount());
        }
    }

    public static class HeaderViewHolder extends BaseViewHolder {
        private final TextView headerTitleTextView;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTitleTextView = (TextView) itemView.findViewById(R.id.headerTitle);
        }

        @Override
        void setData(TransactionRow transactionRow) {
            TransactionRow.TransactionHeader header = (TransactionRow.TransactionHeader) transactionRow;
            headerTitleTextView.setText(header.getDate());
        }
    }

    public abstract static class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void setData(TransactionRow transactionRow);
    }
}
