package com.example.crm.web;

import com.example.crm.domain.Sale;
import com.example.crm.service.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.listAll();
    }

    @PostMapping
    public Sale createSale(@RequestBody SaleRequest request) {
        Sale sale = new Sale();
        sale.setProduct(request.getProduct());
        sale.setAmount(request.getAmount());
        sale.setOwner(request.getOwner());
        return saleService.create(request.getCustomerId(), sale);
    }

    // DTO
    static class SaleRequest {
        private Long customerId;
        private String product;
        private Double amount;
        private String owner;

        public Long getCustomerId() { return customerId; }
        public void setCustomerId(Long customerId) { this.customerId = customerId; }
        public String getProduct() { return product; }
        public void setProduct(String product) { this.product = product; }
        public Double getAmount() { return amount; }
        public void setAmount(Double amount) { this.amount = amount; }
        public String getOwner() { return owner; }
        public void setOwner(String owner) { this.owner = owner; }
    }
}







//package com.example.crm.web;
//
//import com.example.crm.domain.Sale;
//import com.example.crm.service.SaleService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/sales")
//public class SaleController {
//
//    private final SaleService saleService;
//
//    public SaleController(SaleService saleService) {
//        this.saleService = saleService;
//    }
//
//    @GetMapping
//    public List<Sale> getAllSales() {
//        return saleService.listAll();
//    }
//
//    @PostMapping
//    public Sale createSale(@RequestBody SaleRequest request) {
//        Sale sale = new Sale();
//        sale.setProduct(request.getProduct());
//        sale.setAmount(request.getAmount());
//        sale.setOwner(request.getOwner());
//        return saleService.create(request.getCustomerId(), sale);
//    }
//
//    // DTO
//    static class SaleRequest {
//        private Long customerId;
//        private String product;
//        private Double amount;
//        private String owner;
//
//        public Long getCustomerId() { return customerId; }
//        public void setCustomerId(Long customerId) { this.customerId = customerId; }
//        public String getProduct() { return product; }
//        public void setProduct(String product) { this.product = product; }
//        public Double getAmount() { return amount; }
//        public void setAmount(Double amount) { this.amount = amount; }
//        public String getOwner() { return owner; }
//        public void setOwner(String owner) { this.owner = owner; }
//    }
//}



//----------------------------------------------------------------------------------------------------


//package com.example.crm.web;
//
//import com.example.crm.domain.Sale;
//import com.example.crm.service.SaleService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/sales")
//public class SaleController {
//
//    private final SaleService service;
//
//    public SaleController(SaleService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public List<Sale> listAll() {
//        return service.listAll();
//    }
//
//    @PostMapping
//    public Sale create(@RequestBody Sale sale) {
//        return service.create(sale);
//    }
//}



//package com.example.crm.web;
//
//import com.example.crm.domain.Sale;
//import com.example.crm.repo.SaleRepository;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/sales")
//public class SaleController {
//
//    private final SaleRepository sales;
//
//    public SaleController(SaleRepository sales) {
//        this.sales = sales;
//    }
//
//    @GetMapping
//    public List<Sale> listAll() {
//        return sales.findAll();
//    }
//
//    @PostMapping
//    public Sale create(@RequestBody Sale sale) {
//        return sales.save(sale);
//    }
//}
