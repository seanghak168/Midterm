package com.example.iteonlineshop.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.DiffUtil;
        import androidx.recyclerview.widget.ListAdapter;
        import androidx.recyclerview.widget.RecyclerView;

import com.example.iteonlineshop.api.model.PhnomPenh;
import com.example.iteonlineshop.databinding.ViewHolderPostsBinding;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class HomePostsAdapter extends ListAdapter<PhnomPenh, HomePostsAdapter.PostsViewHolder> {
    public HomePostsAdapter() {
        super(new DiffUtil.ItemCallback<PhnomPenh>() {
            @Override
            public boolean areItemsTheSame(@NonNull PhnomPenh oldItem, @NonNull PhnomPenh newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull PhnomPenh oldItem, @NonNull PhnomPenh newItem) {
                return Objects.equals(oldItem.getId(), newItem.getId());
            }
        });
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderPostsBinding binding = ViewHolderPostsBinding.inflate(layoutInflater,parent,false);
        return new PostsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        PhnomPenh item = getItem(position);
        holder.bind(item);
    }

    protected static class PostsViewHolder extends RecyclerView.ViewHolder{
        private final ViewHolderPostsBinding itemBinding;
        public PostsViewHolder(ViewHolderPostsBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(PhnomPenh phnomPenh){
            Picasso.get().load(phnomPenh.getImage()).into(itemBinding.imgProduct);
            itemBinding.txtName.setText(phnomPenh.getName());
            itemBinding.txtDescription.setText(phnomPenh.getDescription());
            itemBinding.txtInt.setText(phnomPenh.getUpdatedAt());
        }

    }
}