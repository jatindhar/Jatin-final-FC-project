package com.flywheel.controller;


import com.flywheel.DTO.DataResponse;
import com.flywheel.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
public class DataController {

    private static final String DEFAULT_BUSINESS_UNIT_ID = "grjgkjgo45734985798";
    
    @Autowired
    private DataService dataService;

    // Utility method to get the default date range (last 3 months)
    private Date getDefaultStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3); // Subtract 3 months
        return calendar.getTime();
    }

    // Utility method to get the current date
    private Date getDefaultEndDate() {
        return new Date();
    }

    @GetMapping("/api/data/all-data")
    public DataResponse getAllData(
            @RequestParam(required = false) Long agreementId,
            @RequestParam(required = false) String agreementTitle,
            @RequestParam(required = false) String disputeReason,
            @RequestParam(required = false) String vendorCode,
            @RequestParam(required = false) String invoiceNumber,
            @RequestParam(required = false) String po,
            @RequestParam(required = false) String asin,
            @RequestParam(required = false, defaultValue = DEFAULT_BUSINESS_UNIT_ID) String businessUnitId, // Default businessUnitId
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,        // Date range filter (default last 3 months)
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        // If the user hasn't provided dates, use default range (last 3 months)
        if (startDate == null) {
            startDate = getDefaultStartDate();
        }
        if (endDate == null) {
            endDate = getDefaultEndDate();
        }

        // Call service method to get data based on the filters
        return dataService.getAllData(
                agreementId,
                agreementTitle,
                disputeReason,
                vendorCode,
                invoiceNumber,
                po,
                asin,
                businessUnitId,
                startDate,
                endDate
        );
    }
}
