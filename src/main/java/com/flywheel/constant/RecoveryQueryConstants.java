package com.flywheel.constant;


public class RecoveryQueryConstants {

    public static final String TOTAL_RECOVERED_BASE_QUERY =
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
        "           WHERE BUSINESS_UNIT_ID = :businessUnitId" +
        "       )" +
        "       AND dm.DISPUTE_TYPE = 'co-op'" +
        "       AND dm.DISPUTE_DATE BETWEEN :startDate AND :endDate";

    public static final String AGREEMENT_ID_FILTER = " AND dm.AGREEMENT_ID = :agreementId ";
    public static final String AGREEMENT_TITLE_FILTER = " AND dm.AGREEMENT_TITLE LIKE CONCAT('%', :agreementTitle, '%') ";
    public static final String DISPUTE_REASON_FILTER = " AND dm.DISPUTE_REASON = :disputeReason ";
    public static final String VENDOR_CODE_FILTER = " AND dm.VENDOR_CODE = :vendorCode ";
    public static final String INVOICE_NUMBER_FILTER = " AND dm.INVOICE_NUMBER = :invoiceNumber ";
    public static final String PO_FILTER = " AND dm.PURCHASE_ORDER = :po ";
    public static final String ASIN_FILTER = " AND dm.ASIN = :asin ";

    public static final String VARIATIONS_CTE =
        "   SELECT" +
        "       INVOICE_NUMBER," +
        "       APPROVED_AMOUNT," +
        "       INVOICE_NUMBER || 'R1' AS INVOICE_NUMBER_R1," +
        "       INVOICE_NUMBER || 'R2' AS INVOICE_NUMBER_R2," +
        "       INVOICE_NUMBER || 'R3' AS INVOICE_NUMBER_R3" +
        "   FROM" +
        "       ResolvedDisputes";

    public static final String MATCHING_REMITTANCES_CTE =
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
        "       r.INVOICE_NUMBER LIKE '%R3'";

    public static final String TOTAL_RECOVERED_QUERY =
        "   SELECT" +
        "       SUM(v.APPROVED_AMOUNT + COALESCE(r.TOTAL_PAID, 0)) AS TOTAL_RECOVERED_AMOUNT" +
        "   FROM" +
        "       TotalRecovered r" +
        "   JOIN" +
        "       Variations v" +
        "   ON" +
        "       r.INVOICE_NUMBER = v.INVOICE_NUMBER";
}

