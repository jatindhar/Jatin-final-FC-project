//package com.flywheel.controller;
//
//
//import com.flywheel.DTO.DataResponse;
//import com.flywheel.service.DataService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//public class DataControllerTest {
//
//    @InjectMocks
//    private DataController dataController;
//
//    @Mock
//    private DataService dataService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    private Date getDefaultStartDate() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, -3); // Subtract 3 months
//        return calendar.getTime();
//    }
//
//    private Date getDefaultEndDate() {
//        return new Date();
//    }
//
//    @Test
//    public void testGetAllDataWithDefaultValues() {
//        // Arrange
//        DataResponse mockResponse = new DataResponse();
//        when(dataService.getAllData(
//                null, null, null, null, null, null, null, "grjgkjgo45734985798", getDefaultStartDate(), getDefaultEndDate()
//        )).thenReturn(mockResponse);
//
//        // Act
//        DataResponse response = dataController.getAllData(null, null, null, null, null, null, null, (Date) null, (Date)null);
//
//        // Assert
//        assertEquals(mockResponse, response);
//    }
//
//    @Test
//    public void testGetAllDataWithCustomFilters() throws Exception {
//        // Arrange
//        DataResponse mockResponse = new DataResponse();
//        Long agreementId = 123L;
//        String agreementTitle = "Test Agreement";
//        String disputeReason = "Test Reason";
//        String vendorCode = "Vendor123";
//        String invoiceNumber = "INV123";
//        String po = "PO123";
//        String asin = "ASIN123";
//        String businessUnitId = "CustomBusinessUnit";
//        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01");
//        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-31");
//
//        when(dataService.getAllData(
//                agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate
//        )).thenReturn(mockResponse);
//
//        // Act
//        DataResponse response = dataController.getAllData(
//                agreementId, agreementTitle, disputeReason, vendorCode, invoiceNumber, po, asin, businessUnitId, startDate, endDate
//        );
//
//        // Assert
//        assertEquals(mockResponse, response);
//    }
//
//    @Test
//    public void testGetAllDataWithNullDates() {
//        // Arrange
//        DataResponse mockResponse = new DataResponse();
//        when(dataService.getAllData(
//                null, null, null, null, null, null, null, "grjgkjgo45734985798", getDefaultStartDate(), getDefaultEndDate()
//        )).thenReturn(mockResponse);
//
//        // Act
//        DataResponse response = dataController.getAllData(
//                null, null, null, null, null, null, null, null, null
//        );
//
//        // Assert
//        assertEquals(mockResponse, response);
//    }
//
//    // Add more tests for various edge cases as needed
//}

package com.flywheel.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.flywheel.DTO.DataResponse;
import com.flywheel.service.DataService;

import java.text.SimpleDateFormat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DataControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DataService dataService;

    @InjectMocks
    private DataController dataController;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dataController).build();
    }

    @Test
    public void testGetAllData_withValidParameters() throws Exception {
        // Mock the DataResponse object to be returned by the service
        DataResponse mockResponse = new DataResponse(1000.0, 100.0, 500.0);
        
        // Mock the service call
        when(dataService.getAllData(
                1L, "Agreement Title", "Dispute Reason", "VendorCode",
                "Invoice123", "PO123", "ASIN123", "businessUnit123",
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-03-31")))
                .thenReturn(mockResponse);

        // Perform the request and validate the response
        mockMvc.perform(get("/api/data/all-data")
                        .param("agreementId", "1")
                        .param("agreementTitle", "Agreement Title")
                        .param("disputeReason", "Dispute Reason")
                        .param("vendorCode", "VendorCode")
                        .param("invoiceNumber", "Invoice123")
                        .param("po", "PO123")
                        .param("asin", "ASIN123")
                        .param("businessUnitId", "businessUnit123")
                        .param("startDate", "2023-01-01")
                        .param("endDate", "2023-03-31"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"totalInvoiceAmount\":1000.0,\"disputeDeniedCount\":100.0,\"totalRecoveredAmount\":500.0}"));
    }

    @Test
    public void testGetAllData_withDefaultDateRange() throws Exception {
        // Mock the DataResponse object to be returned by the service
        DataResponse mockResponse = new DataResponse(2000.0, 50.0, 1000.0);

        // Mock the service call without dates (use defaults)
        when(dataService.getAllData(
                null, null, null, null, null, null, null, "grjgkjgo45734985798",
                dataController.getDefaultStartDate(), dataController.getDefaultEndDate()))
                .thenReturn(mockResponse);

        // Perform the request with no dates and check for default values
        mockMvc.perform(get("/api/data/all-data"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"totalInvoiceAmount\":2000.0,\"disputeDeniedCount\":50.0,\"totalRecoveredAmount\":1000.0}"));
    }

    @Test
    public void testCheckDatabaseConnection_connected() throws Exception {
        // Mock the service call
        when(dataService.isDatabaseConnected()).thenReturn(true);

        // Perform the request and validate the response
        mockMvc.perform(get("/api/data/check-connection"))
                .andExpect(status().isOk())
                .andExpect(content().string("Database is connected"));
    }

    @Test
    public void testCheckDatabaseConnection_notConnected() throws Exception {
        // Mock the service call
        when(dataService.isDatabaseConnected()).thenReturn(false);

        // Perform the request and validate the response
        mockMvc.perform(get("/api/data/check-connection"))
                .andExpect(status().isOk())
                .andExpect(content().string("Database connection failed"));
    }
}

