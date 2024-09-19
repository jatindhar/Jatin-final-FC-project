//package com.flywheel.constant;
//
//
//
//public class InvoiceQueryConstants {
//
//    public static final String TOTAL_INVOICE_BASE_QUERY =
//        "SELECT SUM(c.NET_RECEIPTS) AS total_invoice_amount " +
//        "FROM FLYWHEEL_DEV.SUPPLY_CHAIN.CO_OP_GOLD c " +
//        "JOIN FLYWHEEL_DEV.SUPPLY_CHAIN.VW_ACCOUNT_MAPPING vwm ON c.AGREEMENT_ID = vwm.VC_ACCOUNT_ID " +
//        "LEFT JOIN FLYWHEEL_DEV.SUPPLY_CHAIN.DISPUTE_MANAGEMENT dm ON c.INVOICE_NUMBER = dm.INVOICE_NUMBER " +
//        "WHERE vwm.BUSINESS_UNIT_ID = :businessUnitId " +
//        "AND dm.DISPUTE_TYPE = 'co-op' " +
//        "AND dm.DISPUTE_DATE BETWEEN :startDate AND :endDate ";
//
//    public static final String AGREEMENT_ID_FILTER = "AND c.AGREEMENT_ID = :agreementId ";
//    public static final String AGREEMENT_TITLE_FILTER = "AND c.AGREEMENT_TITLE = :agreementTitle ";
//    public static final String DISPUTE_REASON_FILTER = "AND dm.DISPUTE_REASON = :disputeReason ";
//    public static final String VENDOR_CODE_FILTER = "AND c.VENDOR_CODE = :vendorCode ";
//    public static final String INVOICE_NUMBER_FILTER = "AND c.INVOICE_NUMBER = :invoiceNumber ";
//    public static final String PO_FILTER = "AND c.PURCHASE_ORDER = :po ";
//    public static final String ASIN_FILTER = "AND c.ASIN = :asin ";
//}
