package com.example.crm.integration;

import com.example.crm.domain.Customer;
import com.example.crm.repo.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testAddAndFetchCustomer() {
        Customer c = new Customer();
        c.setName("Integration Customer");
        c.setEmail("int@test.com");
        c.setPhone("1234567890"); // ✅ required

        customerRepository.save(c);

        Customer found = customerRepository.findById(c.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("Integration Customer", found.getName());
        assertEquals("int@test.com", found.getEmail());
        assertEquals("1234567890", found.getPhone());
    }
}





//package com.example.crm.integration;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.repo.CustomerRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class CustomerIntegrationTest {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Test
//    void testAddAndFetchCustomer() {
//        Customer c = new Customer();
//        c.setName("Integration Customer");
//        c.setEmail("int@test.com");
//        customerRepository.save(c);
//
//        Customer found = customerRepository.findById(c.getId()).orElse(null);
//
//        assertNotNull(found);
//        assertEquals("Integration Customer", found.getName());
//        assertEquals("int@test.com", found.getEmail());
//    }
//}
