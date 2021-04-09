
package com.vna.change.retrofit.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Image {

    @SerializedName("name")
    private String mName;
    @SerializedName("point")
    private String mPoint;
    @SerializedName("source")
    private List<Source> mSource;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPoint() {
        return mPoint;
    }

    public void setPoint(String point) {
        mPoint = point;
    }

    public List<Source> getSource() {
        return mSource;
    }

    public void setSource(List<Source> source) {
        mSource = source;
    }

}
