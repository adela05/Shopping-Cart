package com.company.ShoppingCart.Dto;

import java.util.Objects;

public class Receipt {
    private Float grandTotal;
    private Float localRate;
    private Float importRate;
    private Float totalLocalRate;
    private Float totalImportRate;
    private Float subTotal;
    private Float taxTotal;


    public Float getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(Float taxTotal) {
        this.taxTotal = taxTotal;
    }

    public Float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Float itemTotal) {
        this.grandTotal = itemTotal;
    }

    public Float getLocalRate() {
        return localRate;
    }

    public void setLocalRate(Float localRate) {
        this.localRate = localRate;
    }

    public Float getImportRate() {
        return importRate;
    }

    public void setImportRate(Float importRate) {
        this.importRate = importRate;
    }

    public Float getTotalLocalRate() {
        return totalLocalRate;
    }

    public void setTotalLocalRate(Float totalLocalRate) {
        this.totalLocalRate = totalLocalRate;
    }

    public Float getTotalImportRate() {
        return totalImportRate;
    }

    public void setTotalImportRate(Float totalImportRate) {
        this.totalImportRate = totalImportRate;
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(grandTotal, receipt.grandTotal) &&
                Objects.equals(localRate, receipt.localRate) &&
                Objects.equals(importRate, receipt.importRate) &&
                Objects.equals(totalLocalRate, receipt.totalLocalRate) &&
                Objects.equals(totalImportRate, receipt.totalImportRate) &&
                Objects.equals(subTotal, receipt.subTotal) &&
                Objects.equals(taxTotal, receipt.taxTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grandTotal, localRate, importRate, totalLocalRate, totalImportRate, subTotal, taxTotal);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "grandTotal=" + grandTotal +
                ", localRate=" + localRate +
                ", importRate=" + importRate +
                ", totalLocalRate=" + totalLocalRate +
                ", totalImportRate=" + totalImportRate +
                ", subTotal=" + subTotal +
                ", taxTotal=" + taxTotal +
                '}';
    }
}
