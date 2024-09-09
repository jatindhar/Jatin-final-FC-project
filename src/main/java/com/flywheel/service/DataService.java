package com.flywheel.service;




import com.flywheel.DTO.DataResponse;
import com.flywheel.repository.TotalDisputedRepository;
import com.flywheel.repository.TotalInvoiceRepository;
import com.flywheel.repository.TotalRecoveredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DataService {

    @Autowired
    private TotalInvoiceRepository totalInvoiceRepository;

    @Autowired
    private TotalDisputedRepository totalDisputedRepository;

    @Autowired
    private TotalRecoveredRepository totalRecoveredRepository;

    public DataResponse getAllData(
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

        Double totalInvoiceAmount = totalInvoiceRepository.getTotalInvoiceAmount(
            agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);

        Double totalDisputedAmount = totalDisputedRepository.getTotalDisputedAmount(
            agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);

        Double totalRecoveredAmount = totalRecoveredRepository.getTotalRecoveredAmount(
            agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);

        return new DataResponse(totalInvoiceAmount, totalDisputedAmount, totalRecoveredAmount);
    }
}
