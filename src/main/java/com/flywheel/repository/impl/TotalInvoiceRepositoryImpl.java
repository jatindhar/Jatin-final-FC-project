package com.flywheel.repository.impl;

import com.flywheel.repository.TotalInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TotalInvoiceRepositoryImpl implements TotalInvoiceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @SuppressWarnings("deprecation")
	@Override
    public Double getTotalInvoiceAmount(
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
        StringBuilder sql = new StringBuilder(
            "SELECT SUM(c.NET_RECEIPTS) AS total_invoice_amount " +
            "FROM FLYWHEEL_DEV.SUPPLY_CHAIN.CO_OP_GOLD c " +
            "JOIN FLYWHEEL_DEV.SUPPLY_CHAIN.VW_ACCOUNT_MAPPING vwm ON c.AGREEMENT_ID = vwm.VC_ACCOUNT_ID " +
            "LEFT JOIN FLYWHEEL_DEV.SUPPLY_CHAIN.DISPUTE_MANAGEMENT dm ON c.INVOICE_NUMBER = dm.INVOICE_NUMBER " +
            "WHERE vwm.BUSINESS_UNIT_ID = ? " +
            "AND dm.DISPUTE_TYPE = 'co-op' " +
            "AND dm.DISPUTE_DATE BETWEEN ? AND ? "
        );

        // List of query parameters
        List<Object> params = new ArrayList<>();
        params.add(businessUnitId);
        params.add(startDate);
        params.add(endDate);

        // Append filters to the query based on input parameters
        if (agreementId != null) {
            sql.append("AND c.AGREEMENT_ID = ? ");
            params.add(agreementId);
        }
        if (agreementTitle != null && !agreementTitle.isEmpty()) {
            sql.append("AND c.AGREEMENT_TITLE = ? ");
            params.add(agreementTitle);
        }
        if (disputeReason != null && !disputeReason.isEmpty()) {
            sql.append("AND dm.DISPUTE_REASON = ? ");
            params.add(disputeReason);
        }
        if (vendorCode != null && !vendorCode.isEmpty()) {
            sql.append("AND c.VENDOR_CODE = ? ");
            params.add(vendorCode);
        }
        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
            sql.append("AND c.INVOICE_NUMBER = ? ");
            params.add(invoiceNumber);
        }
        if (po != null && !po.isEmpty()) {
            sql.append("AND c.PURCHASE_ORDER = ? ");
            params.add(po);
        }
        if (asin != null && !asin.isEmpty()) {
            sql.append("AND c.ASIN = ? ");
            params.add(asin);
        }

        // Execute the query and return the total invoice amount
        return jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Double.class);
    }
}

