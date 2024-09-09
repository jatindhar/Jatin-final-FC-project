package com.flywheel.controller;


import com.flywheel.DTO.DataResponse;
import com.flywheel.service.DataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DataControllerTest {

    @InjectMocks
    private DataController dataController;

    @Mock
    private DataService dataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Date getDefaultStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3); // Subtract 3 months
        return calendar.getTime();
    }

    private Date getDefaultEndDate() {
        return new Date();
    }

    @Test
    public void testGetAllDataWithDefaultValues() {
        // Arrange
        DataResponse mockResponse = new DataResponse();
        when(dataService.getAllData(
                null, null, null, null, null, null, null, "grjgkjgo45734985798", getDefaultStartDate(), getDefaultEndDate()
        )).thenReturn(mockResponse);

        // Act
        DataResponse response = dataController.getAllData(null, null, null, null, null, null, null, null, null);

        // Assert
        assertEquals(mockResponse, response);
    }

    @Test
    public void testGetAllDataWithCustomFilters() throws Exception {
        // Arrange
        DataResponse mockResponse = new DataResponse();
        Long agreementId = 123L;
        String agreementTitle = "Test Agreement";
        String disputeReason = "Test Reason";
        String vendorCode = "Vendor123";
        String invoiceNumber = "INV123";
        String po = "PO123";
        String asin = "ASIN123";
        String businessUnitId = "CustomBusinessUnit";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-31");

        when(dataService.getAllData(
                agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate
        )).thenReturn(mockResponse);

        // Act
        DataResponse response = dataController.getAllData(
                agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate
        );

        // Assert
        assertEquals(mockResponse, response);
    }

    @Test
    public void testGetAllDataWithNullDates() {
        // Arrange
        DataResponse mockResponse = new DataResponse();
        when(dataService.getAllData(
                null, null, null, null, null, null, null, "grjgkjgo45734985798", getDefaultStartDate(), getDefaultEndDate()
        )).thenReturn(mockResponse);

        // Act
        DataResponse response = dataController.getAllData(
                null, null, null, null, null, null, null, null, null
        );

        // Assert
        assertEquals(mockResponse, response);
    }

    // Add more tests for various edge cases as needed
}
