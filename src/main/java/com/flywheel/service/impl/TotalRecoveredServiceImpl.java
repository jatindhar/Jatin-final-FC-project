package com.flywheel.service.impl;



import com.flywheel.repository.TotalRecoveredRepository;
import com.flywheel.service.TotalRecoveredService;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TotalRecoveredServiceImpl implements TotalRecoveredService {

    private final TotalRecoveredRepository totalRecoveredRepository;

    public TotalRecoveredServiceImpl(TotalRecoveredRepository totalRecoveredRepository) {
        this.totalRecoveredRepository = totalRecoveredRepository;
    }

    @Override
    public Double getTotalRecoveredAmountWithFilters(
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
    ) {
        return totalRecoveredRepository.getTotalRecoveredAmount(
                agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate
        );
    }
}

