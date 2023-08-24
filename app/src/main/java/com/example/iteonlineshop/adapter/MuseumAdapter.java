package com.example.iteonlineshop.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iteonlineshop.api.model.Museum;
import com.example.iteonlineshop.databinding.ViewHolderMuseumBinding;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MuseumAdapter extends ListAdapter<Museum, MuseumAdapter.PostsViewHolder> {
    public MuseumAdapter() {
        super(new DiffUtil.ItemCallback<Museum>() {
            @Override
            public boolean areItemsTheSame(@NonNull Museum oldItem, @NonNull Museum newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Museum oldItem, @NonNull Museum newItem) {
                return Objects.equals(oldItem.getId(), newItem.getId());
            }
        });
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderMuseumBinding binding = ViewHolderMuseumBinding.inflate(layoutInflater,parent,false);
        return new PostsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        Museum item = getItem(position);
        holder.bind(item);
    }

    protected static class PostsViewHolder extends RecyclerView.ViewHolder{
        private final ViewHolderMuseumBinding itemBinding;
        public PostsViewHolder(ViewHolderMuseumBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Museum museum){
            Picasso.get().load(museum.getImage()).into(itemBinding.imgProduct);
            itemBinding.txtName.setText(museum.getName());
        }
    }
}