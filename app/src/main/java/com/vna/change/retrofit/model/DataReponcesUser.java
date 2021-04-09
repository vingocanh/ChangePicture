
package com.vna.change.retrofit.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DataReponcesUser {

    @SerializedName("error")
    private Long mError;
    @SerializedName("resulf")
    private User user;
    @SerializedName("status")
    private String mStatus;

    public Long getError() {
        return mError;
    }

    public void setError(Long error) {
        mError = error;
    }

    public User getResulf() {
        return user;
    }

    public void setResulf(User resulf) {
        user = resulf;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
