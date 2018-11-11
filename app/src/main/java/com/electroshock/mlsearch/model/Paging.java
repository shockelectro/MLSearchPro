
package com.electroshock.mlsearch.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paging implements Serializable
{

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("primary_results")
    @Expose
    private Integer primaryResults;
    private final static long serialVersionUID = 5840207453060630167L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Paging() {
    }

    /**
     * 
     * @param limit
     * @param total
     * @param offset
     * @param primaryResults
     */
    public Paging(Integer total, Integer offset, Integer limit, Integer primaryResults) {
        super();
        this.total = total;
        this.offset = offset;
        this.limit = limit;
        this.primaryResults = primaryResults;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPrimaryResults() {
        return primaryResults;
    }

    public void setPrimaryResults(Integer primaryResults) {
        this.primaryResults = primaryResults;
    }

}
