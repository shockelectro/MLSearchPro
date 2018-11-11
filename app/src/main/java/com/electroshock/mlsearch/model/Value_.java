
package com.electroshock.mlsearch.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value_ implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("results")
    @Expose
    private Integer results;
    private final static long serialVersionUID = 2873167135265590353L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Value_() {
    }

    /**
     * 
     * @param id
     * @param results
     * @param name
     */
    public Value_(String id, String name, Integer results) {
        super();
        this.id = id;
        this.name = name;
        this.results = results;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

}
