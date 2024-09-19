package com.flywheel.constant;

public class DisputeQueryConstants {

//
//    public static final String TOTAL_DISPUTED_BASE_QUERY =
//        "SELECT SUM(dm.TOTAL_DISPUTED_AMOUNT) AS totalDisputedAmount " +
//        "FROM DISPUTE_MANAGEMENT dm " +
//        "JOIN VW_ACCOUNT_MAPPING v ON dm.VC_ACCOUNT_ID = v.VC_ACCOUNT_ID " +
//        "WHERE v.BUSINESS_UNIT_ID = :businessUnitId " +
//        "AND dm.DISPUTE_DATE BETWEEN :startDate AND :endDate " +
//        "AND dm.DISPUTE_TYPE = 'co-op' ";
//
//    public static final String AGREEMENT_ID_FILTER = "AND dm.AGREEMENT_ID = :agreementId ";
//    public static final String AGREEMENT_TITLE_FILTER = "AND dm.AGREEMENT_TITLE LIKE :agreementTitle ";
//    public static final String DISPUTE_REASON_FILTER = "AND dm.DISPUTE_REASON LIKE :disputeReason ";
//    public static final String VENDOR_CODE_FILTER = "AND dm.VENDOR_CODE LIKE :vendorCode ";
//    public static final String INVOICE_NUMBER_FILTER = "AND dm.INVOICE_NUMBER = :invoiceNumber ";
//    public static final String PO_FILTER = "AND dm.PURCHASE_ORDER = :po ";
//    public static final String ASIN_FILTER = "AND dm.ASIN = :asin ";
//}
	

	    public static final String BASE_QUERY = 
	        "WITH DistinctPending AS (" +
	        "   SELECT DISTINCT INVOICE_NUMBER, TOTAL_DISPUTED_AMOUNT " +
	        "   FROM DISPUTE_MANAGEMENT " +
	        "   WHERE VC_ACCOUNT_ID IN (" +
	        "       SELECT VC_ACCOUNT_ID " +
	        "       FROM VW_ACCOUNT_MAPPING " +
	        "       WHERE BUSINESS_UNIT_ID = :businessUnitId " +
	        "   ) " +
	        "   AND DISPUTE_STATUS = 'pending amazon action' " +
	        "   AND DISPUTE_TYPE = 'co-op' " +
	        "   AND DISPUTE_DATE BETWEEN :startDate AND :endDate " +
	        ") " +
	        "SELECT SUM(TOTAL_DISPUTED_AMOUNT) AS dispute_in_progress " +
	        "FROM DistinctPending ";

	    // Filter constants
	    public static final String AGREEMENT_ID_FILTER = " AND dm.AGREEMENT_ID = :agreementId ";
	    public static final String AGREEMENT_TITLE_FILTER = " AND dm.AGREEMENT_TITLE LIKE CONCAT('%', :agreementTitle, '%') ";
	    public static final String DISPUTE_REASON_FILTER = " AND dm.DISPUTE_REASON = :disputeReason ";
	    public static final String VENDOR_CODE_FILTER = " AND dm.VENDOR_CODE = :vendorCode ";
	    public static final String INVOICE_NUMBER_FILTER = " AND dm.INVOICE_NUMBER = :invoiceNumber ";
	    public static final String PO_FILTER = " AND dm.PURCHASE_ORDER = :po ";
	    public static final String ASIN_FILTER = " AND dm.ASIN = :asin ";

	}
