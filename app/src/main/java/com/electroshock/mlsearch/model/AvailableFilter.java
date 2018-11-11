
package com.electroshock.mlsearch.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvailableFilter implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("values")
    @Expose
    private List<Value_> values = null;
    private final static long serialVersionUID = -5605634406510832378L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AvailableFilter() {
    }

    /**
     * 
     * @param id
     * @param values
     * @param name
     * @param type
     */
    public AvailableFilter(String id, String name, String type, List<Value_> values) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.values = values;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Value_> getValues() {
        return values;
    }

    public void setValues(List<Value_> values) {
        this.values = values;
    }

}
