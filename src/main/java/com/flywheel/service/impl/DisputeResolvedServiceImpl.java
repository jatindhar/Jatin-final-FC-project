package com.flywheel.service.impl;

import com.flywheel.repository.DisputeResolvedRepository;
import com.flywheel.service.DisputeResolvedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DisputeResolvedServiceImpl implements DisputeResolvedService {

    private final DisputeResolvedRepository disputeResolvedRepositoryService;

    @Autowired
    public DisputeResolvedServiceImpl(DisputeResolvedRepository disputeResolvedRepositoryService) {
        this.disputeResolvedRepositoryService = disputeResolvedRepositoryService;
    }

    @Override
    public Double getTotalResolvedDisputes(
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

        // Call the repository method to calculate total resolved disputes
        return disputeResolvedRepositoryService.getTotalResolvedDisputes(
                agreementId,
                agreementTitle,
                disputeReason,
                vendorCode,
                invoiceNumber,
                po,
                asin,
                businessUnitId,
                startDate,
                endDate);
    }
}

