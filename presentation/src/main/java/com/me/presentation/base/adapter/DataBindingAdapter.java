package com.me.presentation.base.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public abstract class DataBindingAdapter<T> extends
        ListAdapter<T, DataBindingAdapter.DataBindingViewHolder<T>> {

    protected DataBindingAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    public interface ItemClickListener<T> {
        void onItemClick(T item);
    }

    private ItemClickListener itemClick = null;


    public DataBindingViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType,
                parent, false);

        return new DataBindingViewHolder(binding);
    }

    public void onBindViewHolder(DataBindingViewHolder<T> holder, int position) {
        holder.bind(getItem(position), itemClick);
    }


    static class DataBindingViewHolder<T> extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public DataBindingViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(T item, ItemClickListener aItemClick) {
            binding.setVariable(BR.item, item);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (aItemClick != null) {
                        aItemClick.onItemClick(item);
                    }
                }
            });
            binding.executePendingBindings();
        }

    }
}
