package com.example.crm.service;

import com.example.crm.domain.Sale;
import com.example.crm.domain.Customer;
import com.example.crm.repo.SaleRepository;
import com.example.crm.repo.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SaleServiceTest {

    @Mock
    private SaleRepository saleRepo;

    @Mock
    private CustomerRepository customerRepo;

    @InjectMocks
    private SaleService saleService;

    private Customer customer;
    private Sale sale;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setId(1L);
        customer.setName("Test Customer");

        sale = new Sale();
        sale.setId(1L);
        sale.setProduct("Laptop");      // ✅ product field
        sale.setAmount(1500.0);         // ✅ amount field
        sale.setCustomer(customer);     // ✅ linked customer
        // Removed setSaleDate() → not in your entity
    }

    @Test
    void testListAll() {
        when(saleRepo.findAll()).thenReturn(Arrays.asList(sale));

        List<Sale> result = saleService.listAll();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getProduct());
        verify(saleRepo, times(1)).findAll();
    }

    @Test
    void testCreate() {
        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
        when(saleRepo.save(any(Sale.class))).thenReturn(sale);

        Sale createdSale = saleService.create(1L, sale);

        assertEquals("Laptop", createdSale.getProduct());
        assertEquals(1500.0, createdSale.getAmount());
        verify(saleRepo, times(1)).save(any(Sale.class));
    }
}




//package com.example.crm.service;
//
//import com.example.crm.domain.Sale;
//import com.example.crm.domain.Customer;
//import com.example.crm.repo.SaleRepository;
//import com.example.crm.repo.CustomerRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class SaleServiceTest {
//
//    @Mock
//    private SaleRepository saleRepo;
//
//    @Mock
//    private CustomerRepository customerRepo;
//
//    @InjectMocks
//    private SaleService saleService;
//
//    private Customer customer;
//    private Sale sale;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        customer = new Customer();
//        customer.setId(1L);
//        customer.setName("Test Customer");
//
//        sale = new Sale();
//        sale.setId(1L);
//        sale.setProduct("Laptop");          // ✅ using product instead of description
//        sale.setAmount(1500.0);             // ✅ assuming your entity has amount
//        sale.setSaleDate(LocalDateTime.now());
//        sale.setCustomer(customer);
//    }
//
//    @Test
//    void testListAll() {
//        when(saleRepo.findAll()).thenReturn(Arrays.asList(sale));
//
//        List<Sale> result = saleService.listAll();
//
//        assertEquals(1, result.size());
//        assertEquals("Laptop", result.get(0).getProduct());
//        verify(saleRepo, times(1)).findAll();
//    }
//
//    @Test
//    void testCreate() {
//        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
//        when(saleRepo.save(any(Sale.class))).thenReturn(sale);
//
//        Sale createdSale = saleService.create(1L, sale);
//
//        assertEquals("Laptop", createdSale.getProduct());
//        assertEquals(1500.0, createdSale.getAmount());
//        verify(saleRepo, times(1)).save(any(Sale.class));
//    }
//}
//





//package com.example.crm.service;
//import com.example.crm.domain.Customer;
//import com.example.crm.domain.Sale;
//import com.example.crm.repo.CustomerRepository;
//import com.example.crm.repo.SaleRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class SaleServiceTest {
//
//    @Mock
//    private SaleRepository saleRepo;
//
//    @Mock
//    private CustomerRepository customerRepo;
//
//    @InjectMocks
//    private SaleService saleService;
//
//    private Customer customer;
//    private Sale sale;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        customer = new Customer();
//        customer.setId(1L);
//
//        sale = new Sale();
//        sale.setId(1L);
//        sale.setAmount(500.0);
//        sale.setDescription("Product purchase");
//        sale.setCustomer(customer);
//    }
//
//    @Test
//    void testListAllSales() {
//        when(saleRepo.findAll()).thenReturn(Arrays.asList(sale));
//
//        List<Sale> sales = saleService.listAll();
//
//        assertEquals(1, sales.size());
//        assertEquals(500.0, sales.get(0).getAmount());
//    }
//
//    @Test
//    void testCreateSale() {
//        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
//        when(saleRepo.save(any(Sale.class))).thenReturn(sale);
//
//        Sale saved = saleService.create(1L, sale);
//
//        assertNotNull(saved);
//        assertEquals("Product purchase", saved.getDescription());
//    }
//}

