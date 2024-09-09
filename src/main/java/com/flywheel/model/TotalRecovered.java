package com.flywheel.model;

import java.util.Date;

public class TotalRecovered {

    private Long id;
    private Long vcAccountId;
    private String invoiceNumber;
    private Double amountPaid;
    private Double termsDiscount;
    private Long paymentNumber;
    private Date paymentDate;
    private String description;
    private Date landedDate;

    // Getters and Setters
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public Long getVcAccountId() { 
        return vcAccountId; 
    }
    public void setVcAccountId(Long vcAccountId) { 
        this.vcAccountId = vcAccountId; 
    }

    public String getInvoiceNumber() { 
        return invoiceNumber; 
    }
    public void setInvoiceNumber(String invoiceNumber) { 
        this.invoiceNumber = invoiceNumber; 
    }

    public Double getAmountPaid() { 
        return amountPaid; 
    }
    public void setAmountPaid(Double amountPaid) { 
        this.amountPaid = amountPaid; 
    }

    public Double getTermsDiscount() { 
        return termsDiscount; 
    }
    public void setTermsDiscount(Double termsDiscount) { 
        this.termsDiscount = termsDiscount; 
    }

    public Long getPaymentNumber() { 
        return paymentNumber; 
    }
    public void setPaymentNumber(Long paymentNumber) { 
        this.paymentNumber = paymentNumber; 
    }

    public Date getPaymentDate() { 
        return paymentDate; 
    }
    public void setPaymentDate(Date paymentDate) { 
        this.paymentDate = paymentDate; 
    }

    public String getDescription() { 
        return description; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }

    public Date getLandedDate() { 
        return landedDate; 
    }
    public void setLandedDate(Date landedDate) { 
        this.landedDate = landedDate; 
    }
}
