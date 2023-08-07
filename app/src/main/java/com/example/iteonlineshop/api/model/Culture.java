package com.example.iteonlineshop.api.model;

import com.google.gson.annotations.SerializedName;

public class Culture {
    @SerializedName("category_id")
    private Long mCategoryId;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("id")
    private Long mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("meta_description")
    private String mMetaDescription;
    @SerializedName("meta_keywords")
    private String mMetaKeywords;
    @SerializedName("meta_title")
    private String mMetaTitle;
    @SerializedName("name")
    private String mName;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("status")
    private Long mStatus;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public Long getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(Long categoryId) {
        mCategoryId = categoryId;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(Long createdBy) {
        mCreatedBy = createdBy;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getMetaDescription() {
        return mMetaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        mMetaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return mMetaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        mMetaKeywords = metaKeywords;
    }

    public String getMetaTitle() {
        return mMetaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        mMetaTitle = metaTitle;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }


}
