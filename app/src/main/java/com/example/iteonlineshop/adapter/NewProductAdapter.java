package com.example.iteonlineshop.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.DiffUtil;
        import androidx.recyclerview.widget.ListAdapter;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.iteonlineshop.api.model.Product;
        import com.example.iteonlineshop.databinding.ViewHolderProductLabBinding;
import com.squareup.picasso.Picasso;

        import java.util.Objects;

public class NewProductAdapter extends ListAdapter<Product,NewProductAdapter.ProductViewHolder> {
    public NewProductAdapter() {
        super(new DiffUtil.ItemCallback<Product>() {
            @Override
            public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return Objects.equals(oldItem.getId(), newItem.getId());
            }
        });
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderProductLabBinding binding = ViewHolderProductLabBinding.inflate(layoutInflater,parent,false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product item = getItem(position);
        holder.bind(item);
    }

    protected static class ProductViewHolder extends RecyclerView.ViewHolder{
        private final ViewHolderProductLabBinding itemBinding;
        public ProductViewHolder(ViewHolderProductLabBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Product product){
            Picasso.get().load(product.getImageUrl()).into(itemBinding.imgProduct);
            itemBinding.txtName.setText(product.getName());
            itemBinding.txtPrice.setText(Long.toString(product.getPrice()));
            itemBinding.txtRating.setText(Double.toString(product.getRating()));
        }

    }
}