
package com.electroshock.mlsearch.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attribute implements Serializable
{

    @SerializedName("source")
    @Expose
    private Long source;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value_id")
    @Expose
    private Object valueId;
    @SerializedName("value_name")
    @Expose
    private String valueName;
    @SerializedName("value_struct")
    @Expose
    private Object valueStruct;
    @SerializedName("attribute_group_id")
    @Expose
    private String attributeGroupId;
    @SerializedName("attribute_group_name")
    @Expose
    private String attributeGroupName;
    private final static long serialVersionUID = 8248063447981353452L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Attribute() {
    }

    /**
     * 
     * @param valueName
     * @param id
     * @param valueStruct
     * @param source
     * @param name
     * @param valueId
     * @param attributeGroupName
     * @param attributeGroupId
     */
    public Attribute(Long source, String id, String name, Object valueId, String valueName, Object valueStruct, String attributeGroupId, String attributeGroupName) {
        super();
        this.source = source;
        this.id = id;
        this.name = name;
        this.valueId = valueId;
        this.valueName = valueName;
        this.valueStruct = valueStruct;
        this.attributeGroupId = attributeGroupId;
        this.attributeGroupName = attributeGroupName;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
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

    public Object getValueId() {
        return valueId;
    }

    public void setValueId(Object valueId) {
        this.valueId = valueId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public Object getValueStruct() {
        return valueStruct;
    }

    public void setValueStruct(Object valueStruct) {
        this.valueStruct = valueStruct;
    }

    public String getAttributeGroupId() {
        return attributeGroupId;
    }

    public void setAttributeGroupId(String attributeGroupId) {
        this.attributeGroupId = attributeGroupId;
    }

    public String getAttributeGroupName() {
        return attributeGroupName;
    }

    public void setAttributeGroupName(String attributeGroupName) {
        this.attributeGroupName = attributeGroupName;
    }

}
