package com.example.crm.service;

import com.example.crm.domain.*;
import com.example.crm.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customers;
    private final InteractionRepository interactions;
    private final SaleRepository sales;

    public CustomerService(CustomerRepository customers, InteractionRepository interactions, SaleRepository sales) {
        this.customers = customers;
        this.interactions = interactions;
        this.sales = sales;
    }

    public Customer create(Customer c) {
        return customers.save(c);
    }

    public Customer update(Long id, Customer patch) {
        Customer c = get(id);
        c.setName(patch.getName());
        c.setPhone(patch.getPhone());
        c.setSegment(patch.getSegment());
        c.setEmail(patch.getEmail());
        return customers.save(c);
    }

    public void delete(Long id) {
        customers.delete(get(id));
    }

    public Customer get(Long id) {
        return customers.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found: " + id));
    }

    // ✅ New method to list all customers
    public List<Customer> listAll() {
        return customers.findAll();
    }

    public Interaction addInteraction(Long customerId, Interaction i) {
        i.setCustomer(get(customerId));
        return interactions.save(i);
    }

    public List<Interaction> listInteractions(Long customerId) {
        return interactions.findByCustomer(get(customerId));
    }

    public Sale addSale(Long customerId, Sale s) {
        s.setCustomer(get(customerId));
        return sales.save(s);
    }

    public List<Sale> listSales(Long customerId) {
        return sales.findByCustomer(get(customerId));
    }
}






//package com.example.crm.service;
//
//import com.example.crm.domain.*;
//import com.example.crm.repo.*;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
//
//@Service
//@Transactional
//public class CustomerService {
//
//    private final CustomerRepository customers;
//    private final InteractionRepository interactions;
//    private final SaleRepository sales;
//
//    public CustomerService(CustomerRepository customers, InteractionRepository interactions, SaleRepository sales) {
//        this.customers = customers;
//        this.interactions = interactions;
//        this.sales = sales;
//    }
//
//    public Customer create(Customer c) {
//        return customers.save(c);
//    }
//
//    public Customer update(Long id, Customer patch) {
//        Customer c = get(id);
//        c.setName(patch.getName());
//        c.setPhone(patch.getPhone());
//        c.setSegment(patch.getSegment());
//        return customers.save(c);
//    }
//
//    public void delete(Long id) {
//        customers.delete(get(id));
//    }
//
//    public Customer get(Long id) {
//        return customers.findById(id).orElseThrow(() -> new NotFoundException("Customer not found: " + id));
//    }
//
//    public Interaction addInteraction(Long customerId, Interaction i) {
//        i.setCustomer(get(customerId));
//        return interactions.save(i);
//    }
//
//    public List<Interaction> listInteractions(Long customerId) {
//        return interactions.findByCustomer(get(customerId));
//    }
//
//    public Sale addSale(Long customerId, Sale s) {
//        s.setCustomer(get(customerId));
//        return sales.save(s);
//    }
//
//    public List<Sale> listSales(Long customerId) {
//        return sales.findByCustomer(get(customerId));
//    }
//    
//}
