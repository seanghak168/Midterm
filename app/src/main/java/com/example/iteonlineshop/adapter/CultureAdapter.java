package com.example.iteonlineshop.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iteonlineshop.api.model.Culture;
import com.example.iteonlineshop.databinding.ViewHolderCultureBinding;
import com.example.iteonlineshop.databinding.ViewHolderPostsBinding;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class CultureAdapter extends ListAdapter<Culture, CultureAdapter.PostsViewHolder> {
    public CultureAdapter() {
        super(new DiffUtil.ItemCallback<Culture>() {
            @Override
            public boolean areItemsTheSame(@NonNull Culture oldItem, @NonNull Culture newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Culture oldItem, @NonNull Culture newItem) {
                return Objects.equals(oldItem.getId(), newItem.getId());
            }
        });
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderCultureBinding binding = ViewHolderCultureBinding.inflate(layoutInflater,parent,false);
        return new PostsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        Culture item = getItem(position);
        holder.bind(item);
    }

    protected static class PostsViewHolder extends RecyclerView.ViewHolder{
        private final ViewHolderCultureBinding itemBinding;
        public PostsViewHolder(ViewHolderCultureBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Culture culture){
            Picasso.get().load(culture.getImage()).into(itemBinding.imgProduct);
            itemBinding.txtName.setText(culture.getName());
            itemBinding.txtInt.setText(culture.getCreatedAt());

        }

    }
}