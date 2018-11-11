
package com.electroshock.mlsearch.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Installments implements Serializable
{

    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("currency_id")
    @Expose
    private String currencyId;
    private final static long serialVersionUID = -425346130732255713L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Installments() {
    }

    /**
     * 
     * @param amount
     * @param rate
     * @param currencyId
     * @param quantity
     */
    public Installments(Integer quantity, Double amount, Double rate, String currencyId) {
        super();
        this.quantity = quantity;
        this.amount = amount;
        this.rate = rate;
        this.currencyId = currencyId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

}
