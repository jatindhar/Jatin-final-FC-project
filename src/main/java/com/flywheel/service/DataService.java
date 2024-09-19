//package com.flywheel.service;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//import com.flywheel.DTO.DataResponse;
//import com.flywheel.repository.TotalDisputedRepository;
//import com.flywheel.repository.TotalRecoveredRepository;
//
//@Service
//public class DataService {
//
//    @Autowired
//    private TotalDisputedRepository totalDisputedRepository;
//
//    @Autowired
//    private TotalRecoveredRepository totalRecoveredRepository;
//
//    // For local
//    @Autowired
//    private JdbcTemplate jdbcTemplate; 
//
//    public DataResponse getAllData(
//        Long agreementId,
//        String agreementTitle,
//        String disputeReason,
//        String vendorCode,
//        String invoiceNumber,
//        String po,
//        String asin,
//        String businessUnitId,
//        Date startDate,
//        Date endDate) {
//
//        Double totalDisputedAmount = totalDisputedRepository.getTotalDisputedAmount(
//            agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);
//
//        Double totalRecoveredAmount = totalRecoveredRepository.getTotalRecoveredAmount(
//            agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);
//
//        return new DataResponse(totalDisputedAmount, totalRecoveredAmount);
//    }
//
//    // Method to check if database is connected
//    public boolean isDatabaseConnected() {
//        try {
//            jdbcTemplate.execute("SELECT 1"); // Simple query to check the connection
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}


package com.flywheel.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.flywheel.DTO.DataResponse;
import com.flywheel.repository.DisputeResolvedRepository;
import com.flywheel.repository.TotalDisputedRepository;
import com.flywheel.repository.TotalRecoveredRepository;


@Service
public class DataService {

    @Autowired
    private TotalDisputedRepository totalDisputedRepository;

    @Autowired
    private TotalRecoveredRepository totalRecoveredRepository;
    
    @Autowired
    private DisputeResolvedRepository disputeResolvedRepository;

    // For local
    @Autowired
    private JdbcTemplate jdbcTemplate; 

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

        Double totalDisputedAmount = totalDisputedRepository.getTotalDisputedAmount(
            agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);

        Double totalRecoveredAmount = totalRecoveredRepository.getTotalRecoveredAmount(
            agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);
        
        // Get total resolved disputes amount
        Double totalResolvedDisputes = disputeResolvedRepository.getTotalResolvedDisputes(
            agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);

        // Create and return the DataResponse object
        return new DataResponse(totalDisputedAmount, totalRecoveredAmount, totalResolvedDisputes);
    }

    // Method to check if database is connected
    public boolean isDatabaseConnected() {
        try {
            jdbcTemplate.execute("SELECT 1"); // Simple query to check the connection
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
