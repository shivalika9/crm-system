package com.example.crm.integration;

import com.example.crm.domain.Customer;
import com.example.crm.domain.Sale;
import com.example.crm.repo.CustomerRepository;
import com.example.crm.repo.SaleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SaleIntegrationTest {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testAddAndFetchSale() {
        Customer c = new Customer();
        c.setName("Sale Customer");
        c.setEmail("salecust@test.com");
        c.setPhone("9876543210");
        customerRepository.save(c);

        Sale sale = new Sale();
        sale.setCustomer(c);
        sale.setProduct("Laptop");
        sale.setAmount(2500.00); // ✅ Use Double, not BigDecimal
        sale.setOwner("John Doe");
        saleRepository.save(sale);

        Sale found = saleRepository.findById(sale.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("Laptop", found.getProduct());
        assertEquals(2500.00, found.getAmount());
        assertEquals("John Doe", found.getOwner());
        assertEquals(c.getId(), found.getCustomer().getId());
    }
}




//package com.example.crm.integration;
//
//import com.example.crm.domain.Sale;
//import com.example.crm.domain.Customer;
//import com.example.crm.repo.SaleRepository;
//import com.example.crm.repo.CustomerRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class SaleIntegrationTest {
//
//    @Autowired
//    private SaleRepository saleRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Test
//    void testAddAndFetchSale() {
//        // First add a customer (sale requires a customer)
//        Customer customer = new Customer();
//        customer.setName("Sale Customer");
//        customer.setEmail("sale@test.com");
//        customer.setPhone("9876543210");
//        customerRepository.save(customer);
//
//        // Now create a sale
//        Sale sale = new Sale();
//        sale.setCustomer(customer);
//        sale.setAmount(new BigDecimal("2500.00"));
//        saleRepository.save(sale);
//
//        Sale found = saleRepository.findById(sale.getId()).orElse(null);
//
//        assertNotNull(found);
//        assertEquals(new BigDecimal("2500.00"), found.getAmount());
//        assertEquals("Sale Customer", found.getCustomer().getName());
//    }
//}





//package com.example.crm.integration;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.domain.Sale;
//import com.example.crm.repo.CustomerRepository;
//import com.example.crm.repo.SaleRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class SaleIntegrationTest {
//
//    @Autowired
//    private SaleRepository saleRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Test
//    void testAddAndFetchSale() {
//        Customer c = new Customer();
//        c.setName("Sale Customer");
//        c.setEmail("salecust@test.com");
//        customerRepository.save(c);
//
//        Sale sale = new Sale();
//        sale.setCustomer(c);
//        saleRepository.save(sale);
//
//        Sale found = saleRepository.findById(sale.getId()).orElse(null);
//
//        assertNotNull(found);
//        assertEquals(c.getId(), found.getCustomer().getId());
//    }
//}
