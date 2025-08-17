package com.example.crm.service;

import com.example.crm.domain.Customer;
import com.example.crm.domain.Sale;
import com.example.crm.repo.CustomerRepository;
import com.example.crm.repo.InteractionRepository;
import com.example.crm.repo.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final InteractionRepository interactionRepository;

    public ReportingService(CustomerRepository customerRepository,
                            SaleRepository saleRepository,
                            InteractionRepository interactionRepository) {
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.interactionRepository = interactionRepository;
    }

    // --- Totals --------------------------------------------------------------

    public long totalCustomers() {
        return customerRepository.count();
    }

    public long totalSales() {
        return saleRepository.count();
    }

    public long totalInteractions() {
        return interactionRepository.count();
    }

    // --- Top customers by count of sales ------------------------------------

    public List<Map<String, Object>> topCustomersBySales() {
        List<Sale> sales = saleRepository.findAll();

        Map<Customer, Long> counts = sales.stream()
                .filter(s -> s.getCustomer() != null)
                .collect(Collectors.groupingBy(Sale::getCustomer, Collectors.counting()));

        return counts.entrySet().stream()
                .sorted(Map.Entry.<Customer, Long>comparingByValue().reversed())
                .limit(5)
                .map(e -> {
                    Map<String, Object> m = new LinkedHashMap<>();
                    Customer c = e.getKey();
                    m.put("customerId", c.getId());
                    m.put("customerName", c.getName());
                    m.put("salesCount", e.getValue());
                    return m;
                })
                .collect(Collectors.toList());
    }

    // --- Sales by "month" placeholder (no saleDate available) ---------------
    // Your Sale entity has no date field, so we return a safe summary that
    // won't break the controller. It aggregates everything under "UNKNOWN".
    public Map<String, Long> salesByMonth() {
        long total = saleRepository.count();
        Map<String, Long> map = new LinkedHashMap<>();
        map.put("UNKNOWN", total);
        return map;
    }
}



//package com.example.crm.service;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.domain.Sale;
//import com.example.crm.repo.CustomerRepository;
//import com.example.crm.repo.SaleRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class ReportingService {
//
//    private final CustomerRepository customerRepository;
//    private final SaleRepository saleRepository;
//
//    public ReportingService(CustomerRepository customerRepository, SaleRepository saleRepository) {
//        this.customerRepository = customerRepository;
//        this.saleRepository = saleRepository;
//    }
//
//    // Overall totals
//    public Map<String, Object> getTotalsReport() {
//        Map<String, Object> report = new HashMap<>();
//        report.put("totalCustomers", customerRepository.count());
//        report.put("totalSales", saleRepository.count());
//        return report;
//    }
//
//    // Sales grouped by month
//    public Map<String, Long> getSalesByMonth() {
//        List<Sale> sales = saleRepository.findAll();
//
//        return sales.stream()
//                .collect(Collectors.groupingBy(
//                        s -> {
//                            Object date = s.getSaleDate();
//                            if (date instanceof LocalDate) {
//                                return ((LocalDate) date).getMonth().toString();
//                            } else if (date instanceof LocalDateTime) {
//                                return ((LocalDateTime) date).getMonth().toString();
//                            } else if (date instanceof Date) {
//                                return ((Date) date)
//                                        .toInstant()
//                                        .atZone(ZoneId.systemDefault())
//                                        .getMonth()
//                                        .toString();
//                            } else {
//                                return "UNKNOWN";
//                            }
//                        },
//                        Collectors.counting()
//                ));
//    }
//
//    // Top 5 customers by total sales
//    public List<Map<String, Object>> getTopCustomers() {
//        List<Sale> sales = saleRepository.findAll();
//
//        return sales.stream()
//                .collect(Collectors.groupingBy(Sale::getCustomer, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .sorted(Map.Entry.<Customer, Long>comparingByValue().reversed())
//                .limit(5)
//                .map(entry -> {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("customerName", entry.getKey().getName());
//                    map.put("totalSales", entry.getValue());
//                    return map;
//                })
//                .collect(Collectors.toList());
//    }
//}



//package com.example.crm.service;
//
//import com.example.crm.domain.Customer;
//import com.example.crm.domain.Sale;
//import com.example.crm.domain.Interaction;
//import com.example.crm.repo.CustomerRepository;
//import com.example.crm.repo.SaleRepository;
//import com.example.crm.repo.InteractionRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.Month;
//import java.time.ZoneId;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class ReportingService {
//
//    private final CustomerRepository customerRepository;
//    private final SaleRepository saleRepository;
//    private final InteractionRepository interactionRepository;
//
//    public ReportingService(CustomerRepository customerRepository,
//                            SaleRepository saleRepository,
//                            InteractionRepository interactionRepository) {
//        this.customerRepository = customerRepository;
//        this.saleRepository = saleRepository;
//        this.interactionRepository = interactionRepository;
//    }
//
//    // Total customers
//    public long totalCustomers() {
//        return customerRepository.count();
//    }
//
//    // Total sales
//    public long totalSales() {
//        return saleRepository.count();
//    }
//
//    // Total interactions
//    public long totalInteractions() {
//        return interactionRepository.count();
//    }
//
//    // Top 3 customers by number of sales
//    public List<Map<String, Object>> topCustomersBySales() {
//        List<Sale> sales = saleRepository.findAll();
//
//        Map<Customer, Long> salesCount = sales.stream()
//                .collect(Collectors.groupingBy(Sale::getCustomer, Collectors.counting()));
//
//        return salesCount.entrySet().stream()
//                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
//                .limit(3)
//                .map(e -> {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("customerName", e.getKey().getName());
//                    map.put("salesCount", e.getValue());
//                    return map;
//                })
//                .collect(Collectors.toList());
//    }
//
//    // Sales grouped by month
//    public Map<String, Long> salesByMonth() {
//        List<Sale> sales = saleRepository.findAll();
//
//        return sales.stream()
//                .collect(Collectors.groupingBy(
//                        s -> s.getSaleDate().toInstant().atZone(ZoneId.systemDefault()).getMonth().toString(),
//                        Collectors.counting()
//                ));
//    }
//}
