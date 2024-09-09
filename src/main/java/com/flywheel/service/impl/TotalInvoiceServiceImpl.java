package com.flywheel.service.impl;


import com.flywheel.repository.TotalInvoiceRepository;
import com.flywheel.service.TotalInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TotalInvoiceServiceImpl implements TotalInvoiceService {

    @Autowired
    private TotalInvoiceRepository totalInvoiceRepository;

    @Override
    public Double getTotalInvoiceAmount(
        Long agreementId,
        String agreementTitle,
        String disputeReason,
        String vendorCode,
        String invoiceNumber,
        String po,
        String asin,
        String businessUnitId,
        Date startDate,
        Date endDate) {
        return totalInvoiceRepository.getTotalInvoiceAmount(
            agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);
    }
}

