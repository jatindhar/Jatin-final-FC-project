//package com.flywheel.repository.impl;
//
//import com.flywheel.constant.DisputeQueryConstants;
//import com.flywheel.constant.DisputeResolvedQueryConstant;
//import com.flywheel.repository.DisputeResolvedRepositoryService;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.Date;
//import java.util.Optional;
//import java.util.function.Supplier;
//
//@Repository
//public class DisputeResolvedRepositoryImpl implements DisputeResolvedRepositoryService {
//
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    public DisputeResolvedRepositoryImpl(
//            @Qualifier("defaultJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    /* getTotalDisputeResolvedAmount: Uses the getAmount method to calculate 
//     * both denied and approved amounts by calling it with appropriate base 
//     * queries and parameters.
//        getAmount Method: A unified method to build and execute the query 
//        based on the provided base query, which is reusable for both denied 
//        and approved amounts.*/
//    
//    
//    @Override
//    public Double getTotalResolvedDisputes(
//            Long agreementId,
//            String agreementTitle,
//            String disputeReason,
//            String vendorCode,
//            String invoiceNumber,
//            String po,
//            String asin,
//            String businessUnitId,
//            Date startDate,
//            Date endDate) {
//
//        // Validate mandatory parameters
//        if (businessUnitId == null || startDate == null || endDate == null) {
//            throw new IllegalArgumentException("Mandatory parameters businessUnitId, startDate, and endDate must not be null");
//        }
//
//        // Calculate total denied amount
//        Double totalDeniedAmount = getAmount(DisputeResolvedQueryConstant.TOTAL_DENIED_AMOUNT_BASE_QUERY,
//                agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);
//
//        // Calculate total approved amount
//        Double totalApprovedAmount = getAmount(DisputeResolvedQueryConstant.TOTAL_APPROVED_AMOUNT_BASE_QUERY,
//                agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);
//
//        // Return the sum of both amounts
//        return (totalDeniedAmount != null ? totalDeniedAmount : 0) + (totalApprovedAmount != null ? totalApprovedAmount : 0);
//    }
//
//    private Double getAmount(String baseQuery,
//                             Long agreementId,
//                             String agreementTitle,
//                             String disputeReason,
//                             String vendorCode,
//                             String invoiceNumber,
//                             String po,
//                             String asin,
//                             String businessUnitId,
//                             Date startDate,
//                             Date endDate) {
//
//        // Build the query
//        StringBuilder query = new StringBuilder(baseQuery);
//
//        // Create parameters map
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("businessUnitId", businessUnitId);
//        parameters.addValue("startDate", new java.sql.Date(startDate.getTime()));
//        parameters.addValue("endDate", new java.sql.Date(endDate.getTime()));
//
//        // Add filters conditionally
//        addFilterCondition(query, parameters, agreementId, () -> DisputeQueryConstants.AGREEMENT_ID_FILTER, "agreementId");
//        addFilterCondition(query, parameters, agreementTitle, () -> DisputeQueryConstants.AGREEMENT_TITLE_FILTER, "agreementTitle");
//        addFilterCondition(query, parameters, disputeReason, () -> DisputeQueryConstants.DISPUTE_REASON_FILTER, "disputeReason");
//        addFilterCondition(query, parameters, vendorCode, () -> DisputeQueryConstants.VENDOR_CODE_FILTER, "vendorCode");
//        addFilterCondition(query, parameters, invoiceNumber, () -> DisputeQueryConstants.INVOICE_NUMBER_FILTER, "invoiceNumber");
//        addFilterCondition(query, parameters, po, () -> DisputeQueryConstants.PO_FILTER, "po");
//        addFilterCondition(query, parameters, asin, () -> DisputeQueryConstants.ASIN_FILTER, "asin");
//
//        // Execute the query
//        return namedParameterJdbcTemplate.queryForObject(query.toString(), parameters, Double.class);
//    }
//
//    private void addFilterCondition(StringBuilder query, MapSqlParameterSource parameters, Object filterValue,
//                                    Supplier<String> queryBuilderFunction, String param) {
//        Optional.ofNullable(filterValue)
//                .filter(value -> value instanceof String ? !((String) value).isEmpty() : true)
//                .ifPresent(value -> {
//                    query.append(queryBuilderFunction.get());
//                    parameters.addValue(param, value);
//                });
//    }
//}

package com.flywheel.repository.impl;

import com.flywheel.constant.DisputeResolvedQueryConstant;
import com.flywheel.repository.DisputeResolvedRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

@Repository
public class DisputeResolvedRepositoryImpl implements DisputeResolvedRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DisputeResolvedRepositoryImpl(
            @Qualifier("defaultJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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

        // Validate mandatory parameters
        if (businessUnitId == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Mandatory parameters businessUnitId, startDate, and endDate must not be null");
        }

        // Calculate total denied amount
        Double totalDeniedAmount = getAmount(DisputeResolvedQueryConstant.TOTAL_DENIED_AMOUNT_BASE_QUERY,
                agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);

        // Calculate total approved amount
        Double totalApprovedAmount = getAmount(DisputeResolvedQueryConstant.TOTAL_APPROVED_AMOUNT_BASE_QUERY,
                agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);

        // Return the sum of both amounts
        return (totalDeniedAmount != null ? totalDeniedAmount : 0) + (totalApprovedAmount != null ? totalApprovedAmount : 0);
    }

    
		/* getTotalDisputeResolvedAmount: Uses the getAmount method to calculate 
		  * both denied and approved amounts by calling it with appropriate base 
		   * queries and parameters.
		      getAmount Method: A unified method to build and execute the query 
		      based on the provided base query, which is reusable for both denied 
		       and approved amounts.*/
    
    private Double getAmount(String baseQuery,
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

        // Build the query
        StringBuilder query = new StringBuilder(baseQuery);

        // Create parameters map
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("businessUnitId", businessUnitId);
        parameters.addValue("startDate", new java.sql.Date(startDate.getTime()));
        parameters.addValue("endDate", new java.sql.Date(endDate.getTime()));

        // Add filters conditionally
        addFilterCondition(query, parameters, agreementId, () -> DisputeResolvedQueryConstant.AGREEMENT_ID_FILTER, "agreementId");
        addFilterCondition(query, parameters, agreementTitle, () -> DisputeResolvedQueryConstant.AGREEMENT_TITLE_FILTER, "agreementTitle");
        addFilterCondition(query, parameters, disputeReason, () -> DisputeResolvedQueryConstant.DISPUTE_REASON_FILTER, "disputeReason");
        addFilterCondition(query, parameters, vendorCode, () -> DisputeResolvedQueryConstant.VENDOR_CODE_FILTER, "vendorCode");
        addFilterCondition(query, parameters, invoiceNumber, () -> DisputeResolvedQueryConstant.INVOICE_NUMBER_FILTER, "invoiceNumber");
        addFilterCondition(query, parameters, po, () -> DisputeResolvedQueryConstant.PO_FILTER, "po");
        addFilterCondition(query, parameters, asin, () -> DisputeResolvedQueryConstant.ASIN_FILTER, "asin");

        // Execute the query
        return namedParameterJdbcTemplate.queryForObject(query.toString(), parameters, Double.class);
    }

    private void addFilterCondition(StringBuilder query, MapSqlParameterSource parameters, Object filterValue,
                                    Supplier<String> queryBuilderFunction, String param) {
        Optional.ofNullable(filterValue)
                .filter(value -> value instanceof String ? !((String) value).isEmpty() : true)
                .ifPresent(value -> {
                    query.append(queryBuilderFunction.get());
                    parameters.addValue(param, value);
                });
    }
}

