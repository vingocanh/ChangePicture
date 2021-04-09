
package com.vna.change.retrofit.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class DataReponcesHinhAnh {

    @SerializedName("error")
    private String mError;
    @SerializedName("images")
    private List<Image> mImages;
    @SerializedName("status")
    private Long mStatus;

    public String getError() {
        return mError;
    }

    public void setError(String error) {
        mError = error;
    }

    public List<Image> getImages() {
        return mImages;
    }

    public void setImages(List<Image> images) {
        mImages = images;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
