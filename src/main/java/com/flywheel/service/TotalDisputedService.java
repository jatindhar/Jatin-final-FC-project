package com.flywheel.service;


import java.util.Date;

public interface TotalDisputedService {
    Double getTotalDisputedAmount(
        Long agreementId,
        String agreementTitle,
        String disputeReason,
        String vendorCode,
        String invoiceNumber,
        String po,
        String asin,
        String businessUnitId,
        Date startDate,
        Date endDate
    );
}
