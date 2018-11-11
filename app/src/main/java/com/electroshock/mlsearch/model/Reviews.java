
package com.electroshock.mlsearch.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reviews implements Serializable
{

    @SerializedName("rating_average")
    @Expose
    private Double ratingAverage;
    @SerializedName("total")
    @Expose
    private Integer total;
    private final static long serialVersionUID = 7924457762948186695L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Reviews() {
    }

    /**
     * 
     * @param total
     * @param ratingAverage
     */
    public Reviews(Double ratingAverage, Integer total) {
        super();
        this.ratingAverage = ratingAverage;
        this.total = total;
    }

    public Double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(Double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
