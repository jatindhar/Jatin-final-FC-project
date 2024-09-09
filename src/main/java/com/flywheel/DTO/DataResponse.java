package com.flywheel.DTO;


public class DataResponse {

    private Double totalInvoiceAmount;
    private Double totalDisputedAmount;
    private Double totalRecoveredAmount;

    public DataResponse(Double totalInvoiceAmount, Double totalDisputedAmount, Double totalRecoveredAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
        this.totalDisputedAmount = totalDisputedAmount;
        this.totalRecoveredAmount = totalRecoveredAmount;
    }

    // Getters and Setters
    public Double getTotalInvoiceAmount() 
    { 
    	return totalInvoiceAmount; 
    }
    
    public void setTotalInvoiceAmount(Double totalInvoiceAmount) { 
    	this.totalInvoiceAmount = totalInvoiceAmount; 
    	}

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
}
