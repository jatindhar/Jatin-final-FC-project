//package com.flywheel.repository.impl;
//
//import com.flywheel.constant.RecoveryQueryConstants;
//import com.flywheel.repository.TotalRecoveredRepository;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Repository
//public class TotalRecoveredRepositoryImpl implements TotalRecoveredRepository {
//
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    public TotalRecoveredRepositoryImpl(@Qualifier("${spring.datasource.snowflake.namedJdbcTemplate}") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    @Override
//    public Double getTotalRecoveredAmount(
//            Long agreementId,
//            String agreementTitle,
//            String disputeReason,
//            String vendorCode,
//            String invoiceNumber,
//            String po,
//            String asin,
//            String businessUnitId,
//            Date startDate,
//            Date endDate
//    ) {
//        // Validate mandatory parameters
//        if (businessUnitId == null || startDate == null || endDate == null) {
//            throw new IllegalArgumentException("Mandatory parameters businessUnitId, startDate, and endDate must not be null");
//        }
//
//        // Base SQL query with mandatory filters
//        StringBuilder sql = new StringBuilder(RecoveryQueryConstants.TOTAL_RECOVERED_BASE_QUERY);
//
//        // Map to hold query parameters
//        Map<String, Object> params = new HashMap<>();
//        params.put("businessUnitId", businessUnitId);
//        params.put("startDate", new java.sql.Date(startDate.getTime()));
//        params.put("endDate", new java.sql.Date(endDate.getTime()));
//
//        // Append optional filters to the SQL query and parameters map
//        if (agreementId != null) {
//            sql.append(RecoveryQueryConstants.AGREEMENT_ID_FILTER);
//            params.put("agreementId", agreementId);
//        }
//        if (agreementTitle != null && !agreementTitle.isEmpty()) {
//            sql.append(RecoveryQueryConstants.AGREEMENT_TITLE_FILTER);
//            params.put("agreementTitle", agreementTitle);
//        }
//        if (disputeReason != null && !disputeReason.isEmpty()) {
//            sql.append(RecoveryQueryConstants.DISPUTE_REASON_FILTER);
//            params.put("disputeReason", disputeReason);
//        }
//        if (vendorCode != null && !vendorCode.isEmpty()) {
//            sql.append(RecoveryQueryConstants.VENDOR_CODE_FILTER);
//            params.put("vendorCode", vendorCode);
//        }
//        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
//            sql.append(RecoveryQueryConstants.INVOICE_NUMBER_FILTER);
//            params.put("invoiceNumber", invoiceNumber);
//        }
//        if (po != null && !po.isEmpty()) {
//            sql.append(RecoveryQueryConstants.PO_FILTER);
//            params.put("po", po);
//        }
//        if (asin != null && !asin.isEmpty()) {
//            sql.append(RecoveryQueryConstants.ASIN_FILTER);
//            params.put("asin", asin);
//        }
//
//        // Execute the query and retrieve the result
//        return namedParameterJdbcTemplate.queryForObject(sql.toString(), params, Double.class);
//    }
//}

//package com.flywheel.repository.impl;
//
//import com.flywheel.constant.RecoveryQueryConstants;
//import com.flywheel.repository.TotalRecoveredRepository;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Repository
//public class TotalRecoveredRepositoryImpl implements TotalRecoveredRepository {
//
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    public TotalRecoveredRepositoryImpl(
//            @Qualifier("defaultJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    @Override
//    public Double getTotalRecoveredAmount(
//            Long agreementId,
//            String agreementTitle,
//            String disputeReason,
//            String vendorCode,
//            String invoiceNumber,
//            String po,
//            String asin,
//            String businessUnitId,
//            Date startDate,
//            Date endDate
//    ) {
//        // Validate mandatory parameters
//        if (businessUnitId == null || startDate == null || endDate == null) {
//            throw new IllegalArgumentException("Mandatory parameters businessUnitId, startDate, and endDate must not be null");
//        }
//
//        // Base SQL query with mandatory filters
//        StringBuilder sql = new StringBuilder(RecoveryQueryConstants.TOTAL_RECOVERED_BASE_QUERY);
//
//        // Map to hold query parameters
//        Map<String, Object> params = new HashMap<>();
//        params.put("businessUnitId", businessUnitId);
//        params.put("startDate", new java.sql.Date(startDate.getTime()));
//        params.put("endDate", new java.sql.Date(endDate.getTime()));
//
//        // Append optional filters to the SQL query and parameters map
//        if (agreementId != null) {
//            sql.append(RecoveryQueryConstants.AGREEMENT_ID_FILTER);
//            params.put("agreementId", agreementId);
//        }
//        if (agreementTitle != null && !agreementTitle.isEmpty()) {
//            sql.append(RecoveryQueryConstants.AGREEMENT_TITLE_FILTER);
//            params.put("agreementTitle", agreementTitle);
//        }
//        if (disputeReason != null && !disputeReason.isEmpty()) {
//            sql.append(RecoveryQueryConstants.DISPUTE_REASON_FILTER);
//            params.put("disputeReason", disputeReason);
//        }
//        if (vendorCode != null && !vendorCode.isEmpty()) {
//            sql.append(RecoveryQueryConstants.VENDOR_CODE_FILTER);
//            params.put("vendorCode", vendorCode);
//        }
//        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
//            sql.append(RecoveryQueryConstants.INVOICE_NUMBER_FILTER);
//            params.put("invoiceNumber", invoiceNumber);
//        }
//        if (po != null && !po.isEmpty()) {
//            sql.append(RecoveryQueryConstants.PO_FILTER);
//            params.put("po", po);
//        }
//        if (asin != null && !asin.isEmpty()) {
//            sql.append(RecoveryQueryConstants.ASIN_FILTER);
//            params.put("asin", asin);
//        }
//
//        // Execute the query and retrieve the result
//        return namedParameterJdbcTemplate.queryForObject(sql.toString(), params, Double.class);
//    }
//}

package com.flywheel.repository.impl;

import com.flywheel.constant.RecoveryQueryConstants;
import com.flywheel.repository.TotalRecoveredRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

@Repository
public class TotalRecoveredRepositoryImpl implements TotalRecoveredRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TotalRecoveredRepositoryImpl(
            @Qualifier("defaultJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Double getTotalRecoveredAmount(
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
        // Validate mandatory parameters
        if (businessUnitId == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Mandatory parameters businessUnitId, startDate, and endDate must not be null");
        }

        // Start with the base query
        StringBuilder query = new StringBuilder(RecoveryQueryConstants.TOTAL_RECOVERED_BASE_QUERY);

        // Create parameters map
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("businessUnitId", businessUnitId);
        parameters.addValue("startDate", new java.sql.Date(startDate.getTime()));
        parameters.addValue("endDate", new java.sql.Date(endDate.getTime()));

        // Add filters conditionally
        addFilterCondition(query, parameters, agreementId,
                () -> RecoveryQueryConstants.AGREEMENT_ID_FILTER, "agreementId");

        addFilterCondition(query, parameters, agreementTitle,
                () -> RecoveryQueryConstants.AGREEMENT_TITLE_FILTER, "agreementTitle");

        addFilterCondition(query, parameters, disputeReason,
                () -> RecoveryQueryConstants.DISPUTE_REASON_FILTER, "disputeReason");

        addFilterCondition(query, parameters, vendorCode,
                () -> RecoveryQueryConstants.VENDOR_CODE_FILTER, "vendorCode");

        addFilterCondition(query, parameters, invoiceNumber,
                () -> RecoveryQueryConstants.INVOICE_NUMBER_FILTER, "invoiceNumber");

        addFilterCondition(query, parameters, po,
                () -> RecoveryQueryConstants.PO_FILTER, "po");

        addFilterCondition(query, parameters, asin,
                () -> RecoveryQueryConstants.ASIN_FILTER, "asin");

        // Execute the query
        return namedParameterJdbcTemplate.queryForObject(query.toString(), parameters, Double.class);
    }

    private void addFilterCondition(StringBuilder query, MapSqlParameterSource parameters, Object filterValue,
                                    Supplier<String> queryBuilderFunction, String param) {
        Optional.ofNullable(filterValue).filter(value -> value instanceof String ? !((String) value).isEmpty() : true)
                .ifPresent(value -> {
                    query.append(queryBuilderFunction.get());
                    parameters.addValue(param, value);
                });
    }
}


