package com.example.crm.web;

import com.example.crm.service.ReportingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new HashMap<>();

        summary.put("totalCustomers", reportingService.totalCustomers());
        summary.put("totalSales", reportingService.totalSales());
        summary.put("totalInteractions", reportingService.totalInteractions());
        summary.put("topCustomersBySales", reportingService.topCustomersBySales());
        summary.put("salesByMonth", reportingService.salesByMonth());

        return summary;
    }
}
