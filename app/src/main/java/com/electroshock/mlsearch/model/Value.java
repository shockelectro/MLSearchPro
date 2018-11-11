
package com.electroshock.mlsearch.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("path_from_root")
    @Expose
    private List<PathFromRoot> pathFromRoot = null;
    private final static long serialVersionUID = -2181901979756944517L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Value() {
    }

    /**
     * 
     * @param id
     * @param name
     * @param pathFromRoot
     */
    public Value(String id, String name, List<PathFromRoot> pathFromRoot) {
        super();
        this.id = id;
        this.name = name;
        this.pathFromRoot = pathFromRoot;
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

    public List<PathFromRoot> getPathFromRoot() {
        return pathFromRoot;
    }

    public void setPathFromRoot(List<PathFromRoot> pathFromRoot) {
        this.pathFromRoot = pathFromRoot;
    }

}
