package com.example.iteonlineshop.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iteonlineshop.api.model.Museum;
import com.example.iteonlineshop.api.model.Temple;
import com.example.iteonlineshop.databinding.ViewHolderMuseumBinding;
import com.example.iteonlineshop.databinding.ViewHolderTempleBinding;
import com.example.iteonlineshop.ui.activity.DetailActivity;
import com.example.iteonlineshop.ui.activity.EditActivity;
import com.example.iteonlineshop.ui.activity.TempleActivity;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class TempleAdapter extends ListAdapter<Temple, TempleAdapter.PostsViewHolder> {
    public TempleAdapter() {
        super(new DiffUtil.ItemCallback<Temple>() {
            @Override
            public boolean areItemsTheSame(@NonNull Temple oldItem, @NonNull Temple newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Temple oldItem, @NonNull Temple newItem) {
                return Objects.equals(oldItem.getId(), newItem.getId());
            }
        });
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderTempleBinding binding = ViewHolderTempleBinding.inflate(layoutInflater,parent,false);
        return new PostsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        Temple item = getItem(position);
        holder.bind(item);
    }

    protected static class PostsViewHolder extends RecyclerView.ViewHolder{
        private final ViewHolderTempleBinding itemBinding;
        public PostsViewHolder(ViewHolderTempleBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Temple temple){
            Picasso.get().load(temple.getImage()).into(itemBinding.imgProduct);
            itemBinding.txtName.setText(temple.getName());
        }
    }

}