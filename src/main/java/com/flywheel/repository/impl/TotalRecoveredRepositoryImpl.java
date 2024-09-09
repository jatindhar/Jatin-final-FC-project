package com.flywheel.repository.impl;



import com.flywheel.repository.TotalRecoveredRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TotalRecoveredRepositoryImpl implements TotalRecoveredRepository {

    private final JdbcTemplate jdbcTemplate;

    public TotalRecoveredRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        StringBuilder queryBuilder = new StringBuilder(
        "WITH ResolvedDisputes AS (" +
        "   SELECT" +
        "       dm.INVOICE_NUMBER," +
        "       dm.APPROVED_AMOUNT" +
        "   FROM" +
        "       DISPUTE_MANAGEMENT dm" +
        "   WHERE" +
        "       DISPUTE_STATUS = 'resolved'" +
        "       AND dm.APPROVED_AMOUNT > 0" +
        "       AND dm.VC_ACCOUNT_ID IN (" +
        "           SELECT VC_ACCOUNT_ID" +
        "           FROM VW_ACCOUNT_MAPPING" +
        "           WHERE business_unit_id = ?" +
        "       )" +
        "       AND dm.DISPUTE_TYPE = 'co-op'" +
        "       AND dm.DISPUTE_DATE BETWEEN ? AND ?" +
        (agreementId != null ? " AND dm.AGREEMENT_ID = ?" : "") +
        (agreementTitle != null && !agreementTitle.isEmpty() ? " AND dm.AGREEMENT_TITLE LIKE CONCAT('%', ?, '%')" : "") +
        (disputeReason != null && !disputeReason.isEmpty() ? " AND dm.DISPUTE_REASON = ?" : "") +
        (vendorCode != null && !vendorCode.isEmpty() ? " AND dm.VENDOR_CODE = ?" : "") +
        (invoiceNumber != null && !invoiceNumber.isEmpty() ? " AND dm.INVOICE_NUMBER = ?" : "") +
        (po != null && !po.isEmpty() ? " AND dm.PURCHASE_ORDER = ?" : "") +
        (asin != null && !asin.isEmpty() ? " AND dm.ASIN = ?" : "") +
        (businessUnitId != null && !businessUnitId.isEmpty() ? " AND dm.BUSINESS_UNIT_ID = ?" : "") +
        "), Variations AS (" +
        "   SELECT" +
        "       INVOICE_NUMBER," +
        "       APPROVED_AMOUNT," +
        "       INVOICE_NUMBER || 'R1' AS INVOICE_NUMBER_R1," +
        "       INVOICE_NUMBER || 'R2' AS INVOICE_NUMBER_R2," +
        "       INVOICE_NUMBER || 'R3' AS INVOICE_NUMBER_R3" +
        "   FROM" +
        "       ResolvedDisputes" +
        "), MatchingRemittances AS (" +
        "   SELECT" +
        "       r.INVOICE_NUMBER," +
        "       r.AMOUNT_PAID" +
        "   FROM" +
        "       REMITTANCE r" +
        "   JOIN" +
        "       Variations v" +
        "   ON" +
        "       r.INVOICE_NUMBER IN (v.INVOICE_NUMBER_R1, v.INVOICE_NUMBER_R2, v.INVOICE_NUMBER_R3)" +
        "   WHERE" +
        "       r.INVOICE_NUMBER LIKE '%R1' OR" +
        "       r.INVOICE_NUMBER LIKE '%R2' OR" +
        "       r.INVOICE_NUMBER LIKE '%R3'" +
        "), TotalRecovered AS (" +
        "   SELECT" +
        "       v.INVOICE_NUMBER," +
        "       v.APPROVED_AMOUNT," +
        "       COALESCE(SUM(r.AMOUNT_PAID), 0) AS TOTAL_PAID" +
        "   FROM" +
        "       Variations v" +
        "   LEFT JOIN" +
        "       MatchingRemittances r" +
        "   ON" +
        "       r.INVOICE_NUMBER IN (v.INVOICE_NUMBER_R1, v.INVOICE_NUMBER_R2, v.INVOICE_NUMBER_R3)" +
        "   GROUP BY" +
        "       v.INVOICE_NUMBER, v.APPROVED_AMOUNT" +
        ")" +
        "SELECT" +
        "   SUM(v.APPROVED_AMOUNT + COALESCE(r.TOTAL_PAID, 0)) AS TOTAL_RECOVERED_AMOUNT" +
        "FROM" +
        "   TotalRecovered r" +
        "JOIN" +
        "   Variations v" +
        "ON" +
        "   r.INVOICE_NUMBER = v.INVOICE_NUMBER");

        List<Object> params = new ArrayList<>();
        params.add(businessUnitId);
        params.add(startDate);
        params.add(endDate);

        if (agreementId != null) {
            params.add(agreementId);
        }
        if (agreementTitle != null && !agreementTitle.isEmpty()) {
            params.add(agreementTitle);
        }
        if (disputeReason != null && !disputeReason.isEmpty()) {
            params.add(disputeReason);
        }
        if (vendorCode != null && !vendorCode.isEmpty()) {
            params.add(vendorCode);
        }
        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
            params.add(invoiceNumber);
        }
        if (po != null && !po.isEmpty()) {
            params.add(po);
        }
        if (asin != null && !asin.isEmpty()) {
            params.add(asin);
        }
        if (businessUnitId != null && !businessUnitId.isEmpty()) {
            params.add(businessUnitId);
        }

        @SuppressWarnings("deprecation")
		Double totalRecoveredAmount = jdbcTemplate.queryForObject(
            queryBuilder.toString(),
            params.toArray(),
            Double.class
        );

        return totalRecoveredAmount != null ? totalRecoveredAmount : 0.0;
    }
}
