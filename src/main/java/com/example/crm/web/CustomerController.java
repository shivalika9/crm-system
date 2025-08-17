package com.example.crm.web;

import com.example.crm.domain.*;
import com.example.crm.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService svc;

    public CustomerController(CustomerService svc) {
        this.svc = svc;
    }

    // ✅ Create new customer
    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer c) {
        Customer saved = svc.create(c);
        return ResponseEntity.created(URI.create("/api/customers/" + saved.getId())).body(saved);
    }

    // ✅ NEW: Get all customers
    @GetMapping
    public List<Customer> listAll() {
        return svc.listAll();
    }

    // ✅ Get customer by ID
    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id) {
        return svc.get(id);
    }

    // ✅ Update customer
    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @Valid @RequestBody Customer c) {
        return svc.update(id, c);
    }

    // ✅ Delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Add interaction to a customer
    @PostMapping("/{id}/interactions")
    public Interaction addInteraction(@PathVariable Long id, @Valid @RequestBody Interaction i) {
        return svc.addInteraction(id, i);
    }

    // ✅ List all interactions for a customer
    @GetMapping("/{id}/interactions")
    public List<Interaction> listInteractions(@PathVariable Long id) {
        return svc.listInteractions(id);
    }

    // ✅ Add sale to a customer
    @PostMapping("/{id}/sales")
    public Sale addSale(@PathVariable Long id, @Valid @RequestBody Sale s) {
        return svc.addSale(id, s);
    }

    // ✅ List all sales for a customer
    @GetMapping("/{id}/sales")
    public List<Sale> listSales(@PathVariable Long id) {
        return svc.listSales(id);
    }
}









//package com.example.crm.web;
//
//import com.example.crm.domain.*;
//import com.example.crm.service.CustomerService;
//import jakarta.validation.Valid;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//import java.net.URI;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/customers")
//public class CustomerController {
//
//    private final CustomerService svc;
//
//    public CustomerController(CustomerService svc) {
//        this.svc = svc;
//    }
//
//    @PostMapping
//    public ResponseEntity<Customer> create(@Valid @RequestBody Customer c) {
//        Customer saved = svc.create(c);
//        return ResponseEntity.created(URI.create("/api/customers/" + saved.getId())).body(saved);
//    }
//
//    @GetMapping("/{id}")
//    public Customer get(@PathVariable Long id) {
//        return svc.get(id);
//    }
//
//    @PutMapping("/{id}")
//    public Customer update(@PathVariable Long id, @Valid @RequestBody Customer c) {
//        return svc.update(id, c);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        svc.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PostMapping("/{id}/interactions")
//    public Interaction addInteraction(@PathVariable Long id, @Valid @RequestBody Interaction i) {
//        return svc.addInteraction(id, i);
//    }
//
//    @GetMapping("/{id}/interactions")
//    public List<Interaction> listInteractions(@PathVariable Long id) {
//        return svc.listInteractions(id);
//    }
//
//    @PostMapping("/{id}/sales")
//    public Sale addSale(@PathVariable Long id, @Valid @RequestBody Sale s) {
//        return svc.addSale(id, s);
//    }
//
//    @GetMapping("/{id}/sales")
//    public List<Sale> listSales(@PathVariable Long id) {
//        return svc.listSales(id);
//    }
//}
