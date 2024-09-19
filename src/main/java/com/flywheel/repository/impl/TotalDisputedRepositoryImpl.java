//
//package com.flywheel.repository.impl;
//
//import com.flywheel.constant.DisputeQueryConstants;
//import com.flywheel.repository.TotalDisputedRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.Date;
//
//@Repository
//public class TotalDisputedRepositoryImpl implements TotalDisputedRepository {
//
//    @Autowired
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    @Override
//    public Double getTotalDisputedAmount(
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
//        // Start with the base query
//        StringBuilder query = new StringBuilder(DisputeQueryConstants.BASE_QUERY);
//
//        // Add filters conditionally
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("businessUnitId", businessUnitId);
//        parameters.addValue("startDate", new java.sql.Date(startDate.getTime()));
//        parameters.addValue("endDate", new java.sql.Date(endDate.getTime()));
//
//        if (agreementId != null) {
//            query.append(DisputeQueryConstants.AGREEMENT_ID_FILTER);
//            parameters.addValue("agreementId", agreementId);
//        }
//        if (agreementTitle != null && !agreementTitle.isEmpty()) {
//            query.append(DisputeQueryConstants.AGREEMENT_TITLE_FILTER);
//            parameters.addValue("agreementTitle", agreementTitle);
//        }
//        if (disputeReason != null && !disputeReason.isEmpty()) {
//            query.append(DisputeQueryConstants.DISPUTE_REASON_FILTER);
//            parameters.addValue("disputeReason", disputeReason);
//        }
//        if (vendorCode != null && !vendorCode.isEmpty()) {
//            query.append(DisputeQueryConstants.VENDOR_CODE_FILTER);
//            parameters.addValue("vendorCode", vendorCode);
//        }
//        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
//            query.append(DisputeQueryConstants.INVOICE_NUMBER_FILTER);
//            parameters.addValue("invoiceNumber", invoiceNumber);
//        }
//        if (po != null && !po.isEmpty()) {
//            query.append(DisputeQueryConstants.PO_FILTER);
//            parameters.addValue("po", po);
//        }
//        if (asin != null && !asin.isEmpty()) {
//            query.append(DisputeQueryConstants.ASIN_FILTER);
//            parameters.addValue("asin", asin);
//        }
//
//        // Execute the query
//        return namedParameterJdbcTemplate.queryForObject(query.toString(), parameters, Double.class);
//    }
//}

package com.flywheel.repository.impl;

import com.flywheel.constant.DisputeQueryConstants;
import com.flywheel.repository.TotalDisputedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

@Repository
public class TotalDisputedRepositoryImpl implements TotalDisputedRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TotalDisputedRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

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
    	
    	 /*  The method starts with a base query (DisputeQueryConstants.BASE_QUERY) 
         * and adds dynamic conditions based on the optional filters like agreementId, 
         * agreementTitle, disputeReason, etc.*/

        StringBuilder query = new StringBuilder(DisputeQueryConstants.BASE_QUERY);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        
       // These parameters are part of the mandatory filters
        
        parameters.addValue("businessUnitId", businessUnitId);
        parameters.addValue("startDate", new java.sql.Date(startDate.getTime()));
        parameters.addValue("endDate", new java.sql.Date(endDate.getTime()));
        
        
        /* Adds parameters: For each condition, it uses the helper method 
         * addFilterCondition to append the query and map the filter values 
         * to query parameters.*/
        

        addFilterCondition(query, parameters, agreementId,
                () -> DisputeQueryConstants.AGREEMENT_ID_FILTER, "agreementId");

        addFilterCondition(query, parameters, agreementTitle,
                () -> DisputeQueryConstants.AGREEMENT_TITLE_FILTER, "agreementTitle");

        addFilterCondition(query, parameters, disputeReason,
                () -> DisputeQueryConstants.DISPUTE_REASON_FILTER, "disputeReason");

        addFilterCondition(query, parameters, vendorCode,
                () -> DisputeQueryConstants.VENDOR_CODE_FILTER, "vendorCode");

        addFilterCondition(query, parameters, invoiceNumber,
                () -> DisputeQueryConstants.INVOICE_NUMBER_FILTER, "invoiceNumber");

        addFilterCondition(query, parameters, po,
                () -> DisputeQueryConstants.PO_FILTER, "po");

        addFilterCondition(query, parameters, asin,
                () -> DisputeQueryConstants.ASIN_FILTER, "asin");

        
        /* Executes the query: After building the query string,
         *  it calls namedParameterJdbcTemplate.queryForObject to 
         *  execute the query and return the result as a Double 
         *  (the total disputed amount).*/
        
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


/* The repository implementation uses SQL queries to 
 * interact with the database directly. The NamedParameterJdbcTemplate 
 * is used to execute queries with named parameters.
In your TotalDisputedRepositoryImpl class, you dynamically 
build the SQL query with optional filters and execute it to 
fetch the total disputed amount.*/
