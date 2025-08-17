package com.example.crm.web;

import static org.junit.jupiter.api.Assertions.*;



import com.example.crm.service.ReportingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportingController.class)
class ReportingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportingService reportingService;

    @BeforeEach
    void setUp() {
        when(reportingService.totalCustomers()).thenReturn(2L);
        when(reportingService.totalSales()).thenReturn(1L);
        when(reportingService.totalInteractions()).thenReturn(3L);
        when(reportingService.salesByMonth()).thenReturn(Map.of("UNKNOWN", 1L));
        when(reportingService.topCustomersBySales()).thenReturn(
                List.of(Map.of("customerId", 1L, "customerName", "Customer A", "salesCount", 1L))
        );
    }

    @Test
    void testGetSummaryReport() throws Exception {
        mockMvc.perform(get("/api/reports/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCustomers").value(2))
                .andExpect(jsonPath("$.totalSales").value(1))
                .andExpect(jsonPath("$.totalInteractions").value(3))
                .andExpect(jsonPath("$.salesByMonth.UNKNOWN").value(1))
                .andExpect(jsonPath("$.topCustomersBySales[0].customerName").value("Customer A"));
    }
}
