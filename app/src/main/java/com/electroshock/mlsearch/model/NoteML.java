
package com.electroshock.mlsearch.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoteML //implements Serializable
{

    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("paging")
    @Expose
    private Paging paging;
    @SerializedName("results")
    @Expose
    private List<Producto> results = null;
    @SerializedName("secondary_results")
    @Expose
    private List<Object> secondaryResults = null;
    @SerializedName("related_results")
    @Expose
    private List<Object> relatedResults = null;
    @SerializedName("sort")
    @Expose
    private Sort sort;
    @SerializedName("available_sorts")
    @Expose
    private List<AvailableSort> availableSorts = null;
    @SerializedName("filters")
    @Expose
    private List<Filter> filters = null;
    @SerializedName("available_filters")
    @Expose
    private List<AvailableFilter> availableFilters = null;
    private final static long serialVersionUID = 7512314781526102504L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NoteML() {
    }

    /**
     * 
     * @param sort
     * @param results
     * @param siteId
     * @param query
     * @param availableFilters
     * @param availableSorts
     * @param secondaryResults
     * @param relatedResults
     * @param filters
     * @param paging
     */
    public NoteML(String siteId, String query, Paging paging, List<Producto> results, List<Object> secondaryResults, List<Object> relatedResults, Sort sort, List<AvailableSort> availableSorts, List<Filter> filters, List<AvailableFilter> availableFilters) {
        super();
        this.siteId = siteId;
        this.query = query;
        this.paging = paging;
        this.results = results;
        this.secondaryResults = secondaryResults;
        this.relatedResults = relatedResults;
        this.sort = sort;
        this.availableSorts = availableSorts;
        this.filters = filters;
        this.availableFilters = availableFilters;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<Producto> getResults() {
        return results;
    }

    public void setResults(List<Producto> results) {
        this.results = results;
    }

    public List<Object> getSecondaryResults() {
        return secondaryResults;
    }

    public void setSecondaryResults(List<Object> secondaryResults) {
        this.secondaryResults = secondaryResults;
    }

    public List<Object> getRelatedResults() {
        return relatedResults;
    }

    public void setRelatedResults(List<Object> relatedResults) {
        this.relatedResults = relatedResults;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public List<AvailableSort> getAvailableSorts() {
        return availableSorts;
    }

    public void setAvailableSorts(List<AvailableSort> availableSorts) {
        this.availableSorts = availableSorts;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<AvailableFilter> getAvailableFilters() {
        return availableFilters;
    }

    public void setAvailableFilters(List<AvailableFilter> availableFilters) {
        this.availableFilters = availableFilters;
    }

}
