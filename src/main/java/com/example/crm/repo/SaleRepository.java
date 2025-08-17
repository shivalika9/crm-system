package com.example.crm.repo;
import com.example.crm.domain.Sale;
import com.example.crm.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByCustomer(Customer customer);
}



//package com.example.crm.repo;
//
//import com.example.crm.domain.Sale;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface SaleRepository extends JpaRepository<Sale, Long> {
//}
