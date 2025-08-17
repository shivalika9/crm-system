package com.example.crm.integration;

import com.example.crm.domain.Interaction;
import com.example.crm.domain.Customer;
import com.example.crm.repo.InteractionRepository;
import com.example.crm.repo.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class InteractionIntegrationTest {

    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testAddAndFetchInteraction() {
        // Create and save customer
        Customer customer = new Customer();
        customer.setName("Interaction Customer");
        customer.setEmail("interaction@test.com");
        customer.setPhone("5556667777");
        customer = customerRepository.saveAndFlush(customer);

        // Create and save interaction
        Interaction interaction = new Interaction();
        interaction.setCustomer(customer);
        interaction.setType("CALL");   // ✅ Added required field
        interaction.setOwner("Admin"); // ✅ Added required field
        interaction.setNotes("Phone call regarding product demo");
        interaction = interactionRepository.saveAndFlush(interaction);

        // Fetch interaction by ID
        Interaction found = interactionRepository.findById(interaction.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("Phone call regarding product demo", found.getNotes());
        assertEquals("Interaction Customer", found.getCustomer().getName());
        assertEquals("CALL", found.getType());   // ✅ Verify type
        assertEquals("Admin", found.getOwner()); // ✅ Verify owner
    }
}




//package com.example.crm.integration;
//
//import com.example.crm.domain.Interaction;
//import com.example.crm.domain.Customer;
//import com.example.crm.repo.InteractionRepository;
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
//class InteractionIntegrationTest {
//
//    @Autowired
//    private InteractionRepository interactionRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Test
//    void testAddAndFetchInteraction() {
//        // Create and save customer
//        Customer customer = new Customer();
//        customer.setName("Interaction Customer");
//        customer.setEmail("interaction@test.com");
//        customer.setPhone("5556667777");
//        customer = customerRepository.saveAndFlush(customer);  // ✅ flush ensures ID is generated
//
//        // Create and save interaction
//        Interaction interaction = new Interaction();
//        interaction.setCustomer(customer);
//        interaction.setNotes("Phone call regarding product demo");
//        interaction = interactionRepository.saveAndFlush(interaction);  // ✅ flush ensures persistence
//
//        // Fetch interaction by ID
//        Interaction found = interactionRepository.findById(interaction.getId()).orElse(null);
//
//        assertNotNull(found);
//        assertEquals("Phone call regarding product demo", found.getNotes());
//        assertEquals("Interaction Customer", found.getCustomer().getName());
//    }
//}




//package com.example.crm.integration;
//
//import com.example.crm.domain.Interaction;
//import com.example.crm.domain.Customer;
//import com.example.crm.repo.InteractionRepository;
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
//class InteractionIntegrationTest {
//
//    @Autowired
//    private InteractionRepository interactionRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Test
//    void testAddAndFetchInteraction() {
//        // Create a customer first
//        Customer customer = new Customer();
//        customer.setName("Interaction Customer");
//        customer.setEmail("interaction@test.com");
//        customer.setPhone("5556667777");
//        customerRepository.save(customer);
//
//        // Create interaction
//        Interaction interaction = new Interaction();
//        interaction.setCustomer(customer);
//        interaction.setNotes("Phone call regarding product demo");
//        interactionRepository.save(interaction);
//
//        Interaction found = interactionRepository.findById(interaction.getId()).orElse(null);
//
//        assertNotNull(found);
//        assertEquals("Phone call regarding product demo", found.getNotes());
//        assertEquals("Interaction Customer", found.getCustomer().getName());
//    }
//}

//---------------


//package com.example.crm.integration;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.domain.Interaction;
//import com.example.crm.repo.CustomerRepository;
//import com.example.crm.repo.InteractionRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class InteractionIntegrationTest {
//
//    @Autowired
//    private InteractionRepository interactionRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Test
//    void testAddAndFetchInteraction() {
//        Customer c = new Customer();
//        c.setName("Interaction Customer");
//        c.setEmail("intcust@test.com");
//        customerRepository.save(c);
//
//        Interaction i = new Interaction();
//        i.setCustomer(c);
//        i.setNotes("Test interaction");
//        interactionRepository.save(i);
//
//        Interaction found = interactionRepository.findById(i.getId()).orElse(null);
//
//        assertNotNull(found);
//        assertEquals("Test interaction", found.getNotes());
//        assertEquals(c.getId(), found.getCustomer().getId());
//    }
//}
