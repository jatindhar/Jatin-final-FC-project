package com.flywheel.constant;

public class DisputeResolvedQueryConstant {
	
	  // Base query for total denied amount
    public static final String TOTAL_DENIED_AMOUNT_BASE_QUERY = 
        "WITH DistinctDenied AS ( " +
        "   SELECT DISTINCT INVOICE_NUMBER, APPROVED_AMOUNT, TOTAL_DISPUTED_AMOUNT " +
        "   FROM DISPUTE_MANAGEMENT " +
        "   WHERE VC_ACCOUNT_ID IN ( " +
        "       SELECT VC_ACCOUNT_ID " +
        "       FROM VW_ACCOUNT_MAPPING " +
        "       WHERE BUSINESS_UNIT_ID = :businessUnitId " +
        "   ) " +
        "   AND DISPUTE_TYPE = 'co-op' " +
        "   AND DISPUTE_STATUS = 'resolved' " +
        "   AND DISPUTE_DATE BETWEEN :startDate AND :endDate " +
        ") " +
        "SELECT " +
        "   SUM( " +
        "       CASE " +
        "           WHEN APPROVED_AMOUNT = 0.0000 THEN TOTAL_DISPUTED_AMOUNT " +
        "           WHEN APPROVED_AMOUNT < TOTAL_DISPUTED_AMOUNT THEN TOTAL_DISPUTED_AMOUNT - APPROVED_AMOUNT " +
        "           ELSE 0 " +
        "       END " +
        "   ) AS total_denied_amount " +
        "FROM DistinctDenied;";

    // Base query for total approved amount
    public static final String TOTAL_APPROVED_AMOUNT_BASE_QUERY = 
        "SELECT SUM(dm.APPROVED_AMOUNT) AS total_approved_amount " +
        "FROM FLYWHEEL_DEV.SUPPLY_CHAIN.DISPUTE_MANAGEMENT dm " +
        "JOIN FLYWHEEL_DEV.SUPPLY_CHAIN.VW_ACCOUNT_MAPPING vam " +
        "ON dm.VC_ACCOUNT_ID = vam.vc_account_id " +
        "WHERE vam.BUSINESS_UNIT_ID = :businessUnitId " +
        "AND dm.DISPUTE_TYPE = 'co-op' " +
        "AND dm.DISPUTE_STATUS = 'resolved' " +
        "AND dm.DISPUTE_DATE BETWEEN :startDate AND :endDate ";

    // Filter constants
    public static final String AGREEMENT_ID_FILTER = " AND dm.AGREEMENT_ID = :agreementId ";
    public static final String AGREEMENT_TITLE_FILTER = " AND dm.AGREEMENT_TITLE LIKE CONCAT('%', :agreementTitle, '%') ";
    public static final String DISPUTE_REASON_FILTER = " AND dm.DISPUTE_REASON = :disputeReason ";
    public static final String VENDOR_CODE_FILTER = " AND dm.VENDOR_CODE = :vendorCode ";
    public static final String INVOICE_NUMBER_FILTER = " AND dm.INVOICE_NUMBER = :invoiceNumber ";
    public static final String PO_FILTER = " AND dm.PURCHASE_ORDER = :po ";
    public static final String ASIN_FILTER = " AND dm.ASIN = :asin ";

}
