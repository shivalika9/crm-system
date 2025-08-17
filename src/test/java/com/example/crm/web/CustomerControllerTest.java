package com.example.crm.web;

import com.example.crm.domain.Customer;
import com.example.crm.domain.CustomerSegment;
import com.example.crm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void testGetAllCustomers() throws Exception {
        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setName("Alice");
        c1.setEmail("alice@example.com");
        c1.setPhone("1111111111");
        c1.setSegment(CustomerSegment.STANDARD);

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setName("Bob");
        c2.setEmail("bob@example.com");
        c2.setPhone("2222222222");
        c2.setSegment(CustomerSegment.PREMIUM);

        when(customerService.listAll()).thenReturn(List.of(c1, c2));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].segment").value("PREMIUM"));
    }

    @Test
    void testCreateCustomer() throws Exception {
        Customer saved = new Customer();
        saved.setId(10L);
        saved.setName("Charlie");
        saved.setEmail("charlie@example.com");
        saved.setPhone("9999999999");
        saved.setSegment(CustomerSegment.STANDARD);

        when(customerService.create(any(Customer.class))).thenReturn(saved);

        String payload = """
        {
          "name": "Charlie",
          "email": "charlie@example.com",
          "phone": "9999999999",
          "segment": "STANDARD"
        }
        """;

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.email").value("charlie@example.com"))
                .andExpect(jsonPath("$.segment").value("STANDARD"));
    }
}





//package com.example.crm.web;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.service.CustomerService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//public class CustomerControllerTest {
//
//    @Mock
//    private CustomerService customerService;
//
//    @InjectMocks
//    private CustomerController customerController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAllCustomers() {
//        // Mock customers
//        Customer customer1 = new Customer();
//        customer1.setId(1L);
//        customer1.setName("Alice");
//
//        Customer customer2 = new Customer();
//        customer2.setId(2L);
//        customer2.setName("Bob");
//
//        List<Customer> customers = Arrays.asList(customer1, customer2);
//
//        // Mock service call
//        when(customerService.listAll()).thenReturn(customers);
//
//        // Call controller
//        List<Customer> result = customerController.getAllCustomers();
//
//        // Assertions
//        assertEquals(2, result.size());
//        assertEquals("Alice", result.get(0).getName());
//        assertEquals("Bob", result.get(1).getName());
//    }
//
//    @Test
//    void testCreateCustomer() {
//        // Mock saved customer
//        Customer savedCustomer = new Customer();
//        savedCustomer.setId(3L);
//        savedCustomer.setName("Charlie");
//        savedCustomer.setEmail("charlie@example.com");
//
//        when(customerService.create(any(Customer.class))).thenReturn(savedCustomer);
//
//        // Use inner static request DTO properly
//        com.example.crm.web.CustomerController.CustomerRequest request =
//                new com.example.crm.web.CustomerController.CustomerRequest();
//        request.setName("Charlie");
//        request.setEmail("charlie@example.com");
//
//        Customer result = customerController.createCustomer(request);
//
//        // Assertions
//        assertEquals(3L, result.getId());
//        assertEquals("Charlie", result.getName());
//        assertEquals("charlie@example.com", result.getEmail());
//    }
//}





//package com.example.crm.web;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.service.CustomerService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//public class CustomerControllerTest {
//
//    @Mock
//    private CustomerService customerService;
//
//    @InjectMocks
//    private CustomerController customerController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAllCustomers() {
//        Customer customer1 = new Customer();
//        customer1.setId(1L);
//        customer1.setName("Alice");
//
//        Customer customer2 = new Customer();
//        customer2.setId(2L);
//        customer2.setName("Bob");
//
//        List<Customer> customers = Arrays.asList(customer1, customer2);
//
//        // mock service
//        when(customerService.listAll()).thenReturn(customers);
//
//        // call actual controller method
//        List<Customer> result = customerController.getAllCustomers();
//
//        assertEquals(2, result.size());
//        assertEquals("Alice", result.get(0).getName());
//    }
//
//    @Test
//    void testCreateCustomer() {
//        // Create a mock Customer to return
//        Customer savedCustomer = new Customer();
//        savedCustomer.setId(3L);
//        savedCustomer.setName("Charlie");
//        savedCustomer.setEmail("charlie@example.com");
//
//        when(customerService.create(any(Customer.class))).thenReturn(savedCustomer);
//
//        // Use the controller’s request DTO
//        CustomerController.CustomerRequest request = new CustomerController.CustomerRequest();
//        request.setName("Charlie");
//        request.setEmail("charlie@example.com");
//
//        Customer result = customerController.createCustomer(request);
//
//        assertEquals(3L, result.getId());
//        assertEquals("Charlie", result.getName());
//        assertEquals("charlie@example.com", result.getEmail());
//    }
//}




//package com.example.crm.web;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.service.CustomerService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//public class CustomerControllerTest {
//
//    @Mock
//    private CustomerService customerService;
//
//    @InjectMocks
//    private CustomerController customerController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAllCustomers() {
//        Customer customer1 = new Customer();
//        customer1.setId(1L);
//        customer1.setName("Alice");
//
//        Customer customer2 = new Customer();
//        customer2.setId(2L);
//        customer2.setName("Bob");
//
//        List<Customer> customers = Arrays.asList(customer1, customer2);
//
//        // mock service
//        when(customerService.listAll()).thenReturn(customers);
//
//        // call the actual controller method (make sure method name matches your controller!)
//        List<Customer> result = customerController.getAllCustomers();
//
//        assertEquals(2, result.size());
//        assertEquals("Alice", result.get(0).getName());
//    }
//
//    @Test
//    void testCreateCustomer() {
//        // Instead of using CustomerRequest (to avoid access issues), directly test with a Customer
//        Customer inputCustomer = new Customer();
//        inputCustomer.setName("Charlie");
//        inputCustomer.setEmail("charlie@example.com");
//
//        Customer savedCustomer = new Customer();
//        savedCustomer.setId(3L);
//        savedCustomer.setName("Charlie");
//        savedCustomer.setEmail("charlie@example.com");
//
//        when(customerService.create(any(Customer.class))).thenReturn(savedCustomer);
//
//        // call controller’s createCustomer() directly
//        Customer result = customerController.createCustomer(inputCustomer);
//
//        assertEquals(3L, result.getId());
//        assertEquals("Charlie", result.getName());
//        assertEquals("charlie@example.com", result.getEmail());
//    }
//}





//package com.example.crm.web;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.service.CustomerService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//public class CustomerControllerTest {
//
//    @Mock
//    private CustomerService customerService;
//
//    @InjectMocks
//    private CustomerController customerController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAllCustomers() {
//        Customer customer1 = new Customer();
//        customer1.setId(1L);
//        customer1.setName("Alice");
//
//        Customer customer2 = new Customer();
//        customer2.setId(2L);
//        customer2.setName("Bob");
//
//        List<Customer> customers = Arrays.asList(customer1, customer2);
//
//        when(customerService.listAll()).thenReturn(customers);
//
//        List<Customer> result = customerController.getAllCustomers();
//
//        assertEquals(2, result.size());
//        assertEquals("Alice", result.get(0).getName());
//    }
//
//    @Test
//    void testCreateCustomer() {
//        CustomerController.CustomerRequest request = new CustomerController.CustomerRequest();
//        request.setName("Charlie");
//        request.setEmail("charlie@example.com");
//
//        Customer savedCustomer = new Customer();
//        savedCustomer.setId(3L);
//        savedCustomer.setName("Charlie");
//        savedCustomer.setEmail("charlie@example.com");
//
//        // mock service call
//        when(customerService.create(any(Customer.class))).thenReturn(savedCustomer);
//
//        Customer result = customerController.createCustomer(request);
//
//        assertEquals(3L, result.getId());
//        assertEquals("Charlie", result.getName());
//        assertEquals("charlie@example.com", result.getEmail());
//    }
//}





//package com.example.crm.web;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//import com.example.crm.domain.Customer;
//import com.example.crm.domain.CustomerSegment;
//import com.example.crm.service.CustomerService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//class CustomerControllerTest {
//
//    @Mock
//    private CustomerService customerService;
//
//    @InjectMocks
//    private CustomerController customerController;
//
//    private MockMvc mockMvc;
//    private ObjectMapper objectMapper;
//    private Customer customer;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
//        objectMapper = new ObjectMapper();
//
//        customer = new Customer();
//        customer.setId(1L);
//        customer.setName("Alice");
//        customer.setEmail("alice@test.com");
//        customer.setPhone("1234567890");
//        customer.setSegment(CustomerSegment.PREMIUM);
//    }
//
//    @Test
//    void testGetAllCustomers() throws Exception {
//        when(customerService.listAll()).thenReturn(Arrays.asList(customer));
//
//        mockMvc.perform(get("/api/customers"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("Alice"))
//                .andExpect(jsonPath("$[0].email").value("alice@test.com"));
//    }
//
//    @Test
//    void testCreateCustomers() throws Exception {
//        when(customerService.create(customer)).thenReturn(customer);
//
//        mockMvc.perform(post("/api/customers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(customer)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Alice"));
//    }
//}
