package com.example.crm.repo;

import com.example.crm.domain.Customer;
import com.example.crm.domain.CustomerSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    List<Customer> findBySegment(CustomerSegment segment);
}
