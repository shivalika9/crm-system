package com.example.crm.web;

import com.example.crm.domain.Sale;
import com.example.crm.service.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SaleControllerTest {

    @Mock
    private SaleService saleService;

    @InjectMocks
    private SaleController saleController;

    private Sale sale;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sale = new Sale();
        sale.setId(1L);
        sale.setProduct("Laptop");   // ✅ matches Sale entity
        sale.setAmount(1500.0);
    }

    @Test
    void testGetAllSales() {
        when(saleService.listAll()).thenReturn(Arrays.asList(sale));

        List<Sale> result = saleController.getAllSales();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getProduct());
        verify(saleService, times(1)).listAll();
    }

    @Test
    void testCreateSale() {
        when(saleService.create(eq(1L), any(Sale.class))).thenReturn(sale);

        SaleController.SaleRequest request = new SaleController.SaleRequest();
        request.setCustomerId(1L);
        request.setProduct("Laptop");
        request.setAmount(1500.0);

        // ✅ No ResponseEntity wrapping, directly getting Sale
        Sale response = saleController.createSale(request);

        assertEquals("Laptop", response.getProduct());
        assertEquals(1500.0, response.getAmount());
        verify(saleService, times(1)).create(eq(1L), any(Sale.class));
    }
}





//package com.example.crm.web;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.example.crm.domain.Sale;
//import com.example.crm.service.SaleService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class SaleControllerTest {
//
//    @Mock
//    private SaleService saleService;
//
//    @InjectMocks
//    private SaleController saleController;
//
//    private Sale sale;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        sale = new Sale();
//        sale.setId(1L);
//        sale.setProduct("Laptop");   // ✅ matches Sale entity
//        sale.setAmount(1500.0);
//    }
//
//    @Test
//    void testGetAllSales() {
//        when(saleService.listAll()).thenReturn(Arrays.asList(sale));
//
//        List<Sale> result = saleController.getAllSales();
//
//        assertEquals(1, result.size());
//        assertEquals("Laptop", result.get(0).getProduct());
//        verify(saleService, times(1)).listAll();
//    }
//
//    @Test
//    void testCreateSale() {
//        when(saleService.create(eq(1L), any(Sale.class))).thenReturn(sale);
//
//        SaleController.SaleRequest request = new SaleController.SaleRequest();
//        request.setCustomerId(1L);
//        request.setProduct("Laptop");
//        request.setAmount(1500.0);
//
//        ResponseEntity<Sale> response = saleController.createSale(request);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals("Laptop", response.getBody().getProduct());
//        verify(saleService, times(1)).create(eq(1L), any(Sale.class));
//    }
//}

