////package com.flywheel.repository.impl;
////
////import com.flywheel.constant.InvoiceQueryConstants;
////import com.flywheel.repository.TotalInvoiceRepository;
////import org.springframework.beans.factory.annotation.Qualifier;
////import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
////import org.springframework.stereotype.Repository;
////
////import java.util.Date;
////import java.util.HashMap;
////import java.util.Map;
////
////@Repository
////public class TotalInvoiceRepositoryImpl implements TotalInvoiceRepository {
////
////    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
////    public TotalInvoiceRepositoryImpl(
////            @Qualifier("defaultJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate,
////            @Qualifier("snowflakeJdbcTemplate") NamedParameterJdbcTemplate snowflakeNamedJdbcTemplate) {
////        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
////    }
////
////    @Override
////    public Double getTotalInvoiceAmount(
////        Long agreementId,
////        String agreementTitle,
////        String disputeReason,
////        String vendorCode,
////        String invoiceNumber,
////        String po,
////        String asin,
////        String businessUnitId,
////        Date startDate,
////        Date endDate) {
////
////        // Base SQL query with mandatory filters
////        StringBuilder sql = new StringBuilder(InvoiceQueryConstants.TOTAL_INVOICE_BASE_QUERY);
////
////        // Map to hold query parameters
////        Map<String, Object> params = new HashMap<>();
////        params.put("businessUnitId", businessUnitId);
////        params.put("startDate", new java.sql.Date(startDate.getTime()));
////        params.put("endDate", new java.sql.Date(endDate.getTime()));
////
////        // Append optional filters to the SQL query and parameters map
////        if (agreementId != null) {
////            sql.append(InvoiceQueryConstants.AGREEMENT_ID_FILTER);
////            params.put("agreementId", agreementId);
////        }
////        if (agreementTitle != null && !agreementTitle.isEmpty()) {
////            sql.append(InvoiceQueryConstants.AGREEMENT_TITLE_FILTER);
////            params.put("agreementTitle", agreementTitle);
////        }
////        if (disputeReason != null && !disputeReason.isEmpty()) {
////            sql.append(InvoiceQueryConstants.DISPUTE_REASON_FILTER);
////            params.put("disputeReason", disputeReason);
////        }
////        if (vendorCode != null && !vendorCode.isEmpty()) {
////            sql.append(InvoiceQueryConstants.VENDOR_CODE_FILTER);
////            params.put("vendorCode", vendorCode);
////        }
////        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
////            sql.append(InvoiceQueryConstants.INVOICE_NUMBER_FILTER);
////            params.put("invoiceNumber", invoiceNumber);
////        }
////        if (po != null && !po.isEmpty()) {
////            sql.append(InvoiceQueryConstants.PO_FILTER);
////            params.put("po", po);
////        }
////        if (asin != null && !asin.isEmpty()) {
////            sql.append(InvoiceQueryConstants.ASIN_FILTER);
////            params.put("asin", asin);
////        }
////
////        // Execute the query and retrieve the result
////        return namedParameterJdbcTemplate.queryForObject(sql.toString(), params, Double.class);
////    }
////}
//
//package com.flywheel.repository.impl;
//
//import com.flywheel.constant.InvoiceQueryConstants;
//import com.flywheel.repository.TotalInvoiceRepository;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Repository
//public class TotalInvoiceRepositoryImpl implements TotalInvoiceRepository {
//
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    public TotalInvoiceRepositoryImpl(
//            @Qualifier("defaultJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    @Override
//    public Double getTotalInvoiceAmount(
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
//        // Base SQL query with mandatory filters
//        StringBuilder sql = new StringBuilder(InvoiceQueryConstants.TOTAL_INVOICE_BASE_QUERY);
//
//        // Map to hold query parameters
//        Map<String, Object> params = new HashMap<>();
//        params.put("businessUnitId", businessUnitId);
//        params.put("startDate", new java.sql.Date(startDate.getTime()));
//        params.put("endDate", new java.sql.Date(endDate.getTime()));
//
//        // Append optional filters to the SQL query and parameters map
//        if (agreementId != null) {
//            sql.append(InvoiceQueryConstants.AGREEMENT_ID_FILTER);
//            params.put("agreementId", agreementId);
//        }
//        if (agreementTitle != null && !agreementTitle.isEmpty()) {
//            sql.append(InvoiceQueryConstants.AGREEMENT_TITLE_FILTER);
//            params.put("agreementTitle", agreementTitle);
//        }
//        if (disputeReason != null && !disputeReason.isEmpty()) {
//            sql.append(InvoiceQueryConstants.DISPUTE_REASON_FILTER);
//            params.put("disputeReason", disputeReason);
//        }
//        if (vendorCode != null && !vendorCode.isEmpty()) {
//            sql.append(InvoiceQueryConstants.VENDOR_CODE_FILTER);
//            params.put("vendorCode", vendorCode);
//        }
//        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
//            sql.append(InvoiceQueryConstants.INVOICE_NUMBER_FILTER);
//            params.put("invoiceNumber", invoiceNumber);
//        }
//        if (po != null && !po.isEmpty()) {
//            sql.append(InvoiceQueryConstants.PO_FILTER);
//            params.put("po", po);
//        }
//        if (asin != null && !asin.isEmpty()) {
//            sql.append(InvoiceQueryConstants.ASIN_FILTER);
//            params.put("asin", asin);
//        }
//
//        // Execute the query and retrieve the result
//        return namedParameterJdbcTemplate.queryForObject(sql.toString(), params, Double.class);
//    }
//}
