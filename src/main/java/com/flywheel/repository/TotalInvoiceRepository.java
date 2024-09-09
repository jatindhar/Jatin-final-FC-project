package com.flywheel.repository;

import java.util.Date;

public interface TotalInvoiceRepository {
    Double getTotalInvoiceAmount(
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
