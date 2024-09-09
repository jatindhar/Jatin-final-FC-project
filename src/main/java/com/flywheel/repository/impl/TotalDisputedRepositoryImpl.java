
package com.flywheel.repository.impl;

import com.flywheel.repository.TotalDisputedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class TotalDisputedRepositoryImpl implements TotalDisputedRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @SuppressWarnings("deprecation")
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

        // Base SQL query
        String sql = "SELECT SUM(dm.TOTAL_DISPUTED_AMOUNT) AS totalDisputedAmount " +
                     "FROM DISPUTE_MANAGEMENT dm " +
                     "JOIN VW_ACCOUNT_MAPPING v ON dm.VC_ACCOUNT_ID = v.VC_ACCOUNT_ID " +
                     "WHERE v.BUSINESS_UNIT_ID = ? " +
                     "AND dm.DISPUTE_DATE BETWEEN ? AND ? " +
                     "AND dm.DISPUTE_TYPE = 'co-op' ";

        // StringBuilder to append dynamic filters
        StringBuilder filterQuery = new StringBuilder();

        if (agreementId != null) {
            filterQuery.append("AND dm.AGREEMENT_ID = ? ");
        }
        if (agreementTitle != null) {
            filterQuery.append("AND dm.AGREEMENT_TITLE LIKE ? ");
        }
        if (disputeReason != null) {
            filterQuery.append("AND dm.DISPUTE_REASON LIKE ? ");
        }
        if (vendorCode != null) {
            filterQuery.append("AND dm.VENDOR_CODE LIKE ? ");
        }
        if (invoiceNumber != null) {
            filterQuery.append("AND dm.INVOICE_NUMBER = ? ");
        }
        if (po != null) {
            filterQuery.append("AND dm.PURCHASE_ORDER = ? ");
        }
        if (asin != null) {
            filterQuery.append("AND dm.ASIN = ? ");
        }

        // Combine the base SQL query with dynamic filters
        sql += filterQuery.toString();

        // Dynamically build the parameters array
        Object[] params = buildQueryParameters(agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate);

        // Execute the query and retrieve the result
        return jdbcTemplate.queryForObject(sql, params, Double.class);
    }

    // Helper method to build query parameters array
    private Object[] buildQueryParameters(
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

        // Initialize an array for the parameters
        Object[] baseParams = new Object[]{
            businessUnitId,
            new java.sql.Date(startDate.getTime()),
            new java.sql.Date(endDate.getTime())
        };

        // Initialize a list to hold all the parameters
        List<Object> paramsList = new ArrayList<>(Arrays.asList(baseParams));

        // Add optional filters to the parameters list
        if (agreementId != null) {
            paramsList.add(agreementId);
        }
        if (agreementTitle != null) {
            paramsList.add("%" + agreementTitle + "%");
        }
        if (disputeReason != null) {
            paramsList.add("%" + disputeReason + "%");
        }
        if (vendorCode != null) {
            paramsList.add("%" + vendorCode + "%");
        }
        if (invoiceNumber != null) {
            paramsList.add(invoiceNumber);
        }
        if (po != null) {
            paramsList.add(po);
        }
        if (asin != null) {
            paramsList.add(asin);
        }

        // Convert the list back to an array
        return paramsList.toArray();
    }
}

