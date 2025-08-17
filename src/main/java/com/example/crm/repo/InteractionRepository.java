package com.example.crm.repo;
import com.example.crm.domain.Interaction;
import com.example.crm.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    List<Interaction> findByCustomer(Customer customer);
}

//------------------------------------------------------------------------------------------

//
//package com.example.crm.repo;
//
//import com.example.crm.domain.Interaction;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface InteractionRepository extends JpaRepository<Interaction, Long> {
//	
//}
