
package com.electroshock.mlsearch.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ratings {

    @SerializedName("negative")
    @Expose
    private Double negative;
    @SerializedName("neutral")
    @Expose
    private Integer neutral;
    @SerializedName("positive")
    @Expose
    private Double positive;

    public Double getNegative() {
        return negative;
    }

    public void setNegative(Double negative) {
        this.negative = negative;
    }

    public Integer getNeutral() {
        return neutral;
    }

    public void setNeutral(Integer neutral) {
        this.neutral = neutral;
    }

    public Double getPositive() {
        return positive;
    }

    public void setPositive(Double positive) {
        this.positive = positive;
    }

}
