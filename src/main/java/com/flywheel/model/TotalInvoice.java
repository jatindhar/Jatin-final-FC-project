//package com.flywheel.model;
//
//import java.util.Date;
//
//public class TotalInvoice {
//
//    private Long id;
//    private Date dateLanded;
//    private String client;
//    private String fileName;
//    private String agreementTitle;
//    private Date invoiceDate;
//    private String invoiceNumber;
//    private Long agreementId;
//    private String transactionType;
//    private Long quantity;
//    private Double netReceipts;
//    private String netReceiptsCurrency;
//    private Double listPrice;
//    private String listPriceCurrency;
//    private Double rebateInAgreement;
//    private String agreementCurrency;
//    private Double rebateInPurchaseOrder;
//    private String purchaseOrderCurrency;
//    private String purchaseOrder;
//    private String asin;
//    private Long upc;
//    private Long ean;
//    private String manufacturer;
//    private String distributor;
//    private Long productGroup;
//    private String category;
//    private String subcategory;
//    private String title;
//    private String productDescription;
//    private String binding;
//    private String costCurrency;
//    private Double oldCost;
//    private Double newCost;
//    private String priceProtectionAgreement;
//    private String priceProtectionDay;
//    private String costVariance;
//    private String invoice;
//    private Double agreementTitlePercentage;
//
//    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public Date getDateLanded() { return dateLanded; }
//    public void setDateLanded(Date dateLanded) { this.dateLanded = dateLanded; }
//
//    public String getClient() { return client; }
//    public void setClient(String client) { this.client = client; }
//
//    public String getFileName() { return fileName; }
//    public void setFileName(String fileName) { this.fileName = fileName; }
//
//    public String getAgreementTitle() { return agreementTitle; }
//    public void setAgreementTitle(String agreementTitle) { this.agreementTitle = agreementTitle; }
//
//    public Date getInvoiceDate() { return invoiceDate; }
//    public void setInvoiceDate(Date invoiceDate) { this.invoiceDate = invoiceDate; }
//
//    public String getInvoiceNumber() { return invoiceNumber; }
//    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
//
//    public Long getAgreementId() { return agreementId; }
//    public void setAgreementId(Long agreementId) { this.agreementId = agreementId; }
//
//    public String getTransactionType() { return transactionType; }
//    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
//
//    public Long getQuantity() { return quantity; }
//    public void setQuantity(Long quantity) { this.quantity = quantity; }
//
//    public Double getNetReceipts() { return netReceipts; }
//    public void setNetReceipts(Double netReceipts) { this.netReceipts = netReceipts; }
//
//    public String getNetReceiptsCurrency() { return netReceiptsCurrency; }
//    public void setNetReceiptsCurrency(String netReceiptsCurrency) { this.netReceiptsCurrency = netReceiptsCurrency; }
//
//    public Double getListPrice() { return listPrice; }
//    public void setListPrice(Double listPrice) { this.listPrice = listPrice; }
//
//    public String getListPriceCurrency() { return listPriceCurrency; }
//    public void setListPriceCurrency(String listPriceCurrency) { this.listPriceCurrency = listPriceCurrency; }
//
//    public Double getRebateInAgreement() { return rebateInAgreement; }
//    public void setRebateInAgreement(Double rebateInAgreement) { this.rebateInAgreement = rebateInAgreement; }
//
//    public String getAgreementCurrency() { return agreementCurrency; }
//    public void setAgreementCurrency(String agreementCurrency) { this.agreementCurrency = agreementCurrency; }
//
//    public Double getRebateInPurchaseOrder() { return rebateInPurchaseOrder; }
//    public void setRebateInPurchaseOrder(Double rebateInPurchaseOrder) { this.rebateInPurchaseOrder = rebateInPurchaseOrder; }
//
//    public String getPurchaseOrderCurrency() { return purchaseOrderCurrency; }
//    public void setPurchaseOrderCurrency(String purchaseOrderCurrency) { this.purchaseOrderCurrency = purchaseOrderCurrency; }
//
//    public String getPurchaseOrder() { return purchaseOrder; }
//    public void setPurchaseOrder(String purchaseOrder) { this.purchaseOrder = purchaseOrder; }
//
//    public String getAsin() { return asin; }
//    public void setAsin(String asin) { this.asin = asin; }
//
//    public Long getUpc() { return upc; }
//    public void setUpc(Long upc) { this.upc = upc; }
//
//    public Long getEan() { return ean; }
//    public void setEan(Long ean) { this.ean = ean; }
//
//    public String getManufacturer() { return manufacturer; }
//    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
//
//    public String getDistributor() { return distributor; }
//    public void setDistributor(String distributor) { this.distributor = distributor; }
//
//    public Long getProductGroup() { return productGroup; }
//    public void setProductGroup(Long productGroup) { this.productGroup = productGroup; }
//
//    public String getCategory() { return category; }
//    public void setCategory(String category) { this.category = category; }
//
//    public String getSubcategory() { return subcategory; }
//    public void setSubcategory(String subcategory) { this.subcategory = subcategory; }
//
//    public String getTitle() { return title; }
//    public void setTitle(String title) { this.title = title; }
//
//    public String getProductDescription() { return productDescription; }
//    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }
//
//    public String getBinding() { return binding; }
//    public void setBinding(String binding) { this.binding = binding; }
//
//    public String getCostCurrency() { return costCurrency; }
//    public void setCostCurrency(String costCurrency) { this.costCurrency = costCurrency; }
//
//    public Double getOldCost() { return oldCost; }
//    public void setOldCost(Double oldCost) { this.oldCost = oldCost; }
//
//    public Double getNewCost() { return newCost; }
//    public void setNewCost(Double newCost) { this.newCost = newCost; }
//
//    public String getPriceProtectionAgreement() { return priceProtectionAgreement; }
//    public void setPriceProtectionAgreement(String priceProtectionAgreement) { this.priceProtectionAgreement = priceProtectionAgreement; }
//
//    public String getPriceProtectionDay() { return priceProtectionDay; }
//    public void setPriceProtectionDay(String priceProtectionDay) { this.priceProtectionDay = priceProtectionDay; }
//
//    public String getCostVariance() { return costVariance; }
//    public void setCostVariance(String costVariance) { this.costVariance = costVariance; }
//
//    public String getInvoice() { return invoice; }
//    public void setInvoice(String invoice) { this.invoice = invoice; }
//
//    public Double getAgreementTitlePercentage() { return agreementTitlePercentage; }
//    public void setAgreementTitlePercentage(Double agreementTitlePercentage) { this.agreementTitlePercentage = agreementTitlePercentage; }
//}
