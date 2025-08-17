package com.example.crm.service;

import com.example.crm.domain.Customer;
import com.example.crm.domain.Sale;
import com.example.crm.repo.CustomerRepository;
import com.example.crm.repo.InteractionRepository;
import com.example.crm.repo.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReportingServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private InteractionRepository interactionRepository;

    @InjectMocks
    private ReportingService reportingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTotals() {
        // Mock customers
        when(customerRepository.count()).thenReturn(2L);

        // Mock sales
        when(saleRepository.count()).thenReturn(1L);

        // Mock interactions
        when(interactionRepository.count()).thenReturn(3L);

        // Verify
        assertEquals(2L, reportingService.totalCustomers());
        assertEquals(1L, reportingService.totalSales());
        assertEquals(3L, reportingService.totalInteractions());
    }

    @Test
    void testTopCustomersBySales() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Customer A");

        Sale sale1 = new Sale();
        sale1.setId(1L);
        sale1.setCustomer(customer1);

        when(saleRepository.findAll()).thenReturn(Collections.singletonList(sale1));

        var topCustomers = reportingService.topCustomersBySales();

        assertEquals(1, topCustomers.size());
        assertEquals("Customer A", topCustomers.get(0).get("customerName"));
        assertEquals(1L, topCustomers.get(0).get("salesCount"));
    }

    @Test
    void testSalesByMonth() {
        when(saleRepository.count()).thenReturn(5L);

        var result = reportingService.salesByMonth();

        assertEquals(1, result.size());
        assertEquals(5L, result.get("UNKNOWN"));
    }
}



//package com.example.crm.service;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.domain.Sale;
//import com.example.crm.repo.CustomerRepository;
//import com.example.crm.repo.InteractionRepository;
//import com.example.crm.repo.SaleRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//class ReportingServiceTest {
//
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @Mock
//    private SaleRepository saleRepository;
//
//    @Mock
//    private InteractionRepository interactionRepository;
//
//    @InjectMocks
//    private ReportingService reportingService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGenerateReportSummary() {
//        // Mock customers
//        when(customerRepository.count()).thenReturn(2L);
//
//        // Mock sales
//        Customer customer1 = new Customer();
//        customer1.setId(1L);
//        customer1.setName("Customer A");
//
//        Sale sale1 = new Sale();
//        sale1.setId(1L);
//        sale1.setCustomer(customer1);
//
//        when(saleRepository.findAll()).thenReturn(Collections.singletonList(sale1));
//
//        // Mock interactions
//        when(interactionRepository.count()).thenReturn(3L);
//
//        // Run
//        var report = reportingService.generateReportSummary();
//
//        // Verify
//        assertEquals(2L, report.get("totalCustomers"));
//        assertEquals(1, report.get("totalSales"));
//        assertEquals(3L, report.get("totalInteractions"));
//    }
//}



//package com.example.crm.service;
//
////import static org.junit.jupiter.api.Assertions.*;
//
//
//import com.example.crm.domain.Customer;
//import com.example.crm.domain.Interaction;
//import com.example.crm.domain.Sale;
//import com.example.crm.repo.CustomerRepository;
//import com.example.crm.repo.InteractionRepository;
//import com.example.crm.repo.SaleRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//class ReportingServiceTest {
//
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @Mock
//    private SaleRepository saleRepository;
//
//    @Mock
//    private InteractionRepository interactionRepository;
//
//    @InjectMocks
//    private ReportingService reportingService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGenerateSummaryReport() {
//        // Mock customers
//        when(customerRepository.count()).thenReturn(2L);
//
//        // Mock sales
//        Sale sale1 = new Sale();
//        sale1.setId(1L);
//        sale1.setDescription("Sale 1");
//        sale1.setSaleDate(new Date());
//        Customer customer1 = new Customer();
//        customer1.setId(1L);
//        customer1.setName("Customer A");
//        sale1.setCustomer(customer1);
//
//        Sale sale2 = new Sale();
//        sale2.setId(2L);
//        sale2.setDescription("Sale 2");
//        sale2.setSaleDate(new Date());
//        Customer customer2 = new Customer();
//        customer2.setId(2L);
//        customer2.setName("Customer B");
//        sale2.setCustomer(customer2);
//
//        when(saleRepository.findAll()).thenReturn(Arrays.asList(sale1, sale2));
//
//        // Mock interactions
//        when(interactionRepository.count()).thenReturn(3L);
//
//        // Run
//        var report = reportingService.generateSummaryReport();
//
//        // Verify
//        assertEquals(2L, report.get("totalCustomers"));
//        assertEquals(2, report.get("totalSales"));
//        assertEquals(3L, report.get("totalInteractions"));
//    }
//}
