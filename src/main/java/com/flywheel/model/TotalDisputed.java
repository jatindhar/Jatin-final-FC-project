package com.flywheel.model;

import java.util.Date;

public class TotalDisputed {

    private Long id;
    private String vcAccountId;
    private Date disputeDate;
    private String disputeId;
    private String disputeStatus;
    private String disputeType;
    private String invoiceNumber;
    private String originalInvoiceNumber;
    private Double totalDisputedAmount;
    private Double approvedAmount;
    private Date landedDate;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVcAccountId() { return vcAccountId; }
    public void setVcAccountId(String vcAccountId) { this.vcAccountId = vcAccountId; }

    public Date getDisputeDate() { return disputeDate; }
    public void setDisputeDate(Date disputeDate) { this.disputeDate = disputeDate; }

    public String getDisputeId() { return disputeId; }
    public void setDisputeId(String disputeId) { this.disputeId = disputeId; }

    public String getDisputeStatus() { return disputeStatus; }
    public void setDisputeStatus(String disputeStatus) { this.disputeStatus = disputeStatus; }

    public String getDisputeType() { return disputeType; }
    public void setDisputeType(String disputeType) { this.disputeType = disputeType; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public String getOriginalInvoiceNumber() { return originalInvoiceNumber; }
    public void setOriginalInvoiceNumber(String originalInvoiceNumber) { this.originalInvoiceNumber = originalInvoiceNumber; }

    public Double getTotalDisputedAmount() { return totalDisputedAmount; }
    public void setTotalDisputedAmount(Double totalDisputedAmount) { this.totalDisputedAmount = totalDisputedAmount; }

    public Double getApprovedAmount() { return approvedAmount; }
    public void setApprovedAmount(Double approvedAmount) { this.approvedAmount = approvedAmount; }

    public Date getLandedDate() { return landedDate; }
    public void setLandedDate(Date landedDate) { this.landedDate = landedDate; }
}
