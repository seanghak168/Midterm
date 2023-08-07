package com.example.iteonlineshop.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iteonlineshop.api.model.Popular_visit;
import com.example.iteonlineshop.databinding.ViewHolderPopularBinding;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class PopularAdapter extends ListAdapter<Popular_visit, PopularAdapter.PopularViewHolder> {
    public PopularAdapter() {
        super(new DiffUtil.ItemCallback<Popular_visit>() {
            @Override
            public boolean areItemsTheSame(@NonNull Popular_visit oldItem, @NonNull Popular_visit newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Popular_visit oldItem, @NonNull Popular_visit newItem) {
                return Objects.equals(oldItem.getId(), newItem.getId());
            }
        });
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderPopularBinding binding = ViewHolderPopularBinding.inflate(layoutInflater,parent,false);
        return new PopularViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {

        Popular_visit item = getItem(position);
        holder.bind(item);

    }

    protected static class PopularViewHolder extends RecyclerView.ViewHolder{
        private ViewHolderPopularBinding itemBinding;
        public PopularViewHolder(ViewHolderPopularBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
        @SuppressLint("SetTextI18n")
        public void bind(Popular_visit product){
            Picasso.get().load(product.getImage()).into(itemBinding.imgProduct);
            itemBinding.txtName.setText(product.getName());
//            itemBinding.txtPrice.setText(Long.toString(product.getPrice()));
//            itemBinding.txtRating.setText(Double.toString(product.getRating()));

        }

    }
}