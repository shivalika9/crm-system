package com.example.crm.integration;

import com.example.crm.CrmSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = CrmSystemApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetSummaryReport() {
        String url = "http://localhost:" + port + "/api/reports/summary";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        // Verify status
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify keys exist in response
        Map<String, Object> body = response.getBody();
        assertTrue(body.containsKey("totalCustomers"));
        assertTrue(body.containsKey("totalSales"));
        assertTrue(body.containsKey("totalInteractions"));
        assertTrue(body.containsKey("topCustomersBySales"));
        assertTrue(body.containsKey("salesByMonth"));
    }
}



//package com.example.crm.integration;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.domain.Sale;
//import com.example.crm.repo.CustomerRepository;
//import com.example.crm.repo.SaleRepository;
//import com.example.crm.service.ReportingService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class ReportingIntegrationTest {
//
//    @Autowired
//    private ReportingService reportingService;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    private SaleRepository saleRepository;
//
//    @Test
//    void testGenerateReportSummary() {
//        Customer c = new Customer();
//        c.setName("Report Customer");
//        c.setEmail("report@test.com");
//        customerRepository.save(c);
//
//        Sale sale = new Sale();
//        sale.setCustomer(c);
//        saleRepository.save(sale);
//
//        Map<String, Object> report = reportingService.generateReportSummary();
//
//        assertTrue(report.containsKey("totalCustomers"));
//        assertTrue(report.containsKey("totalSales"));
//        assertTrue(report.containsKey("totalInteractions"));
//
//        assertEquals(1L, report.get("totalCustomers"));
//        assertEquals(1L, report.get("totalSales"));
//    }
//}
