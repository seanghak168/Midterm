package com.example.iteonlineshop.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iteonlineshop.api.model.Profiles;
import com.example.iteonlineshop.databinding.ViewHolderProfileBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private final List<Profiles> profileData = new ArrayList<>();    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderProfileBinding binding = ViewHolderProfileBinding.inflate(layoutInflater, parent, false);
        return new ProfileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Profiles profiles = profileData.get(position);
        holder.bind(profiles);
    }

    @Override
    public int getItemCount() {
        return profileData.size();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setProfile(Profiles profiles){
        profileData.clear();
        profileData.add(profiles);
        notifyDataSetChanged();
    }

    protected static class ProfileViewHolder extends RecyclerView.ViewHolder {
        private final ViewHolderProfileBinding itemBinding;
        public ProfileViewHolder(@NonNull ViewHolderProfileBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
        public void bind (Profiles profiles){
            Picasso.get().load(profiles.getImageUrl()).into(itemBinding.imgPf);
            itemBinding.txtFirstName.setText(profiles.getFirst_name());
            itemBinding.txtLastName.setText(profiles.getLast_name());
            itemBinding.Email.setText(profiles.getEmail());
            itemBinding.PhoneNumber.setText(profiles.getPhoneNumber());
            itemBinding.Gender.setText(profiles.getGender());
            itemBinding.HBD.setText(profiles.getBirthday());
            itemBinding.Address.setText(profiles.getAddress());
        }
    }

}
