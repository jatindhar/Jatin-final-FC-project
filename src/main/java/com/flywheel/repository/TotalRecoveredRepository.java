package com.flywheel.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public interface TotalRecoveredRepository {
    Double getTotalRecoveredAmount(
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


