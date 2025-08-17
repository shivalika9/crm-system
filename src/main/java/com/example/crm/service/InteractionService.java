package com.example.crm.service;

import com.example.crm.domain.Customer;
import com.example.crm.domain.Interaction;
import com.example.crm.repo.CustomerRepository;
import com.example.crm.repo.InteractionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InteractionService {

    private final InteractionRepository interactionRepo;
    private final CustomerRepository customerRepo;

    public InteractionService(InteractionRepository interactionRepo, CustomerRepository customerRepo) {
        this.interactionRepo = interactionRepo;
        this.customerRepo = customerRepo;
    }

    public List<Interaction> listAll() {
        return interactionRepo.findAll();
    }

    public Interaction create(Long customerId, Interaction interaction) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
        interaction.setCustomer(customer);
        return interactionRepo.save(interaction);
    }
}


//------------------------------------------------------------------------------------------


//
//package com.example.crm.service;
//
//import com.example.crm.domain.Interaction;
//import com.example.crm.repo.InteractionRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional
//public class InteractionService {
//
//    private final InteractionRepository interactions;
//
//    public InteractionService(InteractionRepository interactions) {
//        this.interactions = interactions;
//    }
//
//    public List<Interaction> listAll() {
//        return interactions.findAll();
//    }
//
//    public Interaction create(Interaction interaction) {
//        return interactions.save(interaction);
//    }
//}
