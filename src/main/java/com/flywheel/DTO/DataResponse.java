package com.flywheel.DTO;

public class DataResponse {

    private Double totalDisputedAmount;
    private Double totalRecoveredAmount;
    private Double totalResolvedDisputes;  // New field for total resolved disputes

    // Updated constructor to include the new field
    public DataResponse(Double totalDisputedAmount, Double totalRecoveredAmount, Double totalResolvedDisputes) {
        this.totalDisputedAmount = totalDisputedAmount;
        this.totalRecoveredAmount = totalRecoveredAmount;
        this.totalResolvedDisputes = totalResolvedDisputes;
    }

    // Getters and Setters
    public Double getTotalDisputedAmount() {
        return totalDisputedAmount;
    }

    public void setTotalDisputedAmount(Double totalDisputedAmount) {
        this.totalDisputedAmount = totalDisputedAmount;
    }

    public Double getTotalRecoveredAmount() {
        return totalRecoveredAmount;
    }

    public void setTotalRecoveredAmount(Double totalRecoveredAmount) {
        this.totalRecoveredAmount = totalRecoveredAmount;
    }

    public Double getTotalResolvedDisputes() {
        return totalResolvedDisputes;
    }

    public void setTotalResolvedDisputes(Double totalResolvedDisputes) {
        this.totalResolvedDisputes = totalResolvedDisputes;
    }
}
