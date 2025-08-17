package com.example.crm.service;

import com.example.crm.domain.Customer;
import com.example.crm.domain.Sale;
import com.example.crm.repo.CustomerRepository;
import com.example.crm.repo.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepo;
    private final CustomerRepository customerRepo;

    public SaleService(SaleRepository saleRepo, CustomerRepository customerRepo) {
        this.saleRepo = saleRepo;
        this.customerRepo = customerRepo;
    }

    public List<Sale> listAll() {
        return saleRepo.findAll();
    }

    public Sale create(Long customerId, Sale sale) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
        sale.setCustomer(customer);
        return saleRepo.save(sale);
    }
}







//package com.example.crm.service;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.domain.Sale;
//import com.example.crm.repo.CustomerRepository;
//import com.example.crm.repo.SaleRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//public class SaleService {
//
//    private final SaleRepository saleRepo;
//    private final CustomerRepository customerRepo;
//
//    public SaleService(SaleRepository saleRepo, CustomerRepository customerRepo) {
//        this.saleRepo = saleRepo;
//        this.customerRepo = customerRepo;
//    }
//
//    public List<Sale> listAll() {
//        return saleRepo.findAll();
//    }
//
//    public Sale create(Long customerId, Sale sale) {
//        Customer customer = customerRepo.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
//        sale.setCustomer(customer);
//        return saleRepo.save(sale);
//    }
//}


//------------------------------------------------------------------------------------------------


//
//package com.example.crm.service;
//
//import com.example.crm.domain.Sale;
//import com.example.crm.repo.SaleRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//public class SaleService {
//
//    private final SaleRepository sales;
//
//    public SaleService(SaleRepository sales) {
//        this.sales = sales;
//    }
//
//    public List<Sale> listAll() {
//        return sales.findAll();
//    }
//
//    public Sale create(Sale sale) {
//        return sales.save(sale);
//    }
//}
