package com.flywheel.service.impl;


import com.flywheel.repository.TotalDisputedRepository;
import com.flywheel.service.TotalDisputedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TotalDisputedServiceImpl implements TotalDisputedService {

    @Autowired
    private TotalDisputedRepository totalDisputedRepository;

    @Override
    public Double getTotalDisputedAmount(
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

        return totalDisputedRepository.getTotalDisputedAmount(
            agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);
    }
}

