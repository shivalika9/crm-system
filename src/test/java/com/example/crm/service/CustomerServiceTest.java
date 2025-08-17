package com.example.crm.service;
import com.example.crm.domain.Customer;
import com.example.crm.domain.CustomerSegment;
import com.example.crm.repo.CustomerRepository;
import com.example.crm.repo.InteractionRepository;
import com.example.crm.repo.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepo;

    @Mock
    private InteractionRepository interactionRepo;

    @Mock
    private SaleRepository saleRepo;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setId(1L);
        customer.setName("Alice");
        customer.setEmail("alice@test.com");
        customer.setPhone("1234567890");
        customer.setSegment(CustomerSegment.PREMIUM);
    }

    @Test
    void testCreateCustomer() {
        when(customerRepo.save(any(Customer.class))).thenReturn(customer);

        Customer saved = customerService.create(customer);

        assertNotNull(saved);
        assertEquals("Alice", saved.getName());
        verify(customerRepo, times(1)).save(customer);
    }

    @Test
    void testGetCustomer() {
        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));

        Customer found = customerService.get(1L);

        assertNotNull(found);
        assertEquals("alice@test.com", found.getEmail());
    }

    @Test
    void testListAllCustomers() {
        when(customerRepo.findAll()).thenReturn(Arrays.asList(customer));

        List<Customer> customers = customerService.listAll();

        assertEquals(1, customers.size());
        assertEquals("Alice", customers.get(0).getName());
    }
}
