package com.example.crm.web;

import com.example.crm.domain.Interaction;
import com.example.crm.service.InteractionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @GetMapping
    public List<Interaction> getAllInteractions() {
        return interactionService.listAll();
    }

    @PostMapping
    public Interaction createInteraction(@RequestBody InteractionRequest request) {
        Interaction interaction = new Interaction();
        interaction.setType(request.getType());
        interaction.setNotes(request.getNotes());
        interaction.setOwner(request.getOwner());
        return interactionService.create(request.getCustomerId(), interaction);
    }

    // DTO for request
    static class InteractionRequest {
        private Long customerId;
        private String type;
        private String notes;
        private String owner;

        public Long getCustomerId() { return customerId; }
        public void setCustomerId(Long customerId) { this.customerId = customerId; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }
        public String getOwner() { return owner; }
        public void setOwner(String owner) { this.owner = owner; }
    }
}


//--------------------------------------------------------------------------------


//package com.example.crm.web;
//
//import com.example.crm.domain.Interaction;
//import com.example.crm.service.CustomerService;
//import com.example.crm.service.InteractionService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/interactions")
//public class InteractionController {
//
//    private final InteractionService interactionService;
//    private final CustomerService customerService;
//
//    public InteractionController(InteractionService interactionService, CustomerService customerService) {
//        this.interactionService = interactionService;
//        this.customerService = customerService;
//    }
//
//    // GET all interactions
//    @GetMapping
//    public List<Interaction> getAllInteractions() {
//        return interactionService.listAll();
//    }
//
//    // POST new interaction with customerId
////    @PostMapping
////    public Interaction createInteraction(@RequestBody InteractionRequest request) {
////        Interaction interaction = new Interaction();
////        interaction.setType(request.getType());
////        interaction.setNotes(request.getNotes());
////        interaction.setOwner(request.getOwner());
////        return customerService.addInteraction(request.getCustomerId(), interaction);
////    }
//    
//    
//    @PostMapping
//    public Interaction createInteraction(@RequestBody InteractionRequest request) {
//        Interaction interaction = new Interaction();
//
//        // Convert string to enum
//        interaction.setType(Interaction.InteractionType.valueOf(request.getType().toUpperCase()));
//
//        interaction.setNotes(request.getNotes());
//        interaction.setOwner(request.getOwner());
//        return customerService.addInteraction(request.getCustomerId(), interaction);
//    }
//
//
//    // DTO to read JSON body
//    static class InteractionRequest {
//        private Long customerId;
//        private String type;
//        private String notes;
//        private String owner;
//
//        public Long getCustomerId() { return customerId; }
//        public void setCustomerId(Long customerId) { this.customerId = customerId; }
//        public String getType() { return type; }
//        public void setType(String type) { this.type = type; }
//        public String getNotes() { return notes; }
//        public void setNotes(String notes) { this.notes = notes; }
//        public String getOwner() { return owner; }
//        public void setOwner(String owner) { this.owner = owner; }
//    }
//}
//



//package com.example.crm.web;
//
//import com.example.crm.domain.Interaction;
//import com.example.crm.service.InteractionService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/interactions")
//public class InteractionController {
//
//    private final InteractionService interactionService;
//
//    public InteractionController(InteractionService interactionService) {
//        this.interactionService = interactionService;
//    }
//
//    // GET all interactions
//    @GetMapping
//    public List<Interaction> getAllInteractions() {
//        return interactionService.listAll();
//    }
//
//    // POST a new interaction (optional if you already create via CustomerService)
//    @PostMapping
//    public Interaction createInteraction(@RequestBody Interaction interaction) {
//        return interactionService.create(interaction);
//    }
//}





//package com.example.crm.web;
//
//import com.example.crm.domain.Interaction;
//import com.example.crm.service.InteractionService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/interactions")
//public class InteractionController {
//
//    private final InteractionService service;
//
//    public InteractionController(InteractionService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public List<Interaction> listAll() {
//        return service.listAll();
//    }
//
//    @PostMapping
//    public Interaction create(@RequestBody Interaction interaction) {
//        return service.create(interaction);
//    }
//}



//package com.example.crm.web;
//
//import com.example.crm.domain.Interaction;
//import com.example.crm.repo.InteractionRepository;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/interactions")
//public class InteractionController {
//
//    private final InteractionRepository interactions;
//
//    public InteractionController(InteractionRepository interactions) {
//        this.interactions = interactions;
//    }
//
//    @GetMapping
//    public List<Interaction> listAll() {
//        return interactions.findAll();
//    }
//
//    @PostMapping
//    public Interaction create(@RequestBody Interaction interaction) {
//        return interactions.save(interaction);
//    }
//}







//package com.example.crm.web;
//
//import com.example.crm.domain.Interaction;
//import com.example.crm.repo.InteractionRepository;
//import com.example.crm.service.CustomerService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/interactions")
//public class InteractionController {
//
//    private final CustomerService customerService;
//    private final InteractionRepository interactionRepo;
//
//    public InteractionController(CustomerService customerService, InteractionRepository interactionRepo) {
//        this.customerService = customerService;
//        this.interactionRepo = interactionRepo;
//    }
//
//    @PostMapping
//    public Interaction create(@RequestBody InteractionRequest req) {
//        Interaction interaction = new Interaction();
//        interaction.setType(req.getType());
//        interaction.setNotes(req.getNotes());
//        interaction.setOwner(req.getOwner());
//        return customerService.addInteraction(req.getCustomerId(), interaction);
//    }
//
//    @GetMapping
//    public List<Interaction> listAll() {
//        return interactionRepo.findAll();
//    }
//
//    // DTO for request body
//    public static class InteractionRequest {
//        private Long customerId;
//        private com.example.crm.domain.InteractionType type;
//        private String notes;
//        private String owner;
//
//        public Long getCustomerId() { return customerId; }
//        public void setCustomerId(Long customerId) { this.customerId = customerId; }
//
//        public com.example.crm.domain.InteractionType getType() { return type; }
//        public void setType(com.example.crm.domain.InteractionType type) { this.type = type; }
//
//        public String getNotes() { return notes; }
//        public void setNotes(String notes) { this.notes = notes; }
//
//        public String getOwner() { return owner; }
//        public void setOwner(String owner) { this.owner = owner; }
//    }
//}




//package com.example.crm.web;

//
//import com.example.crm.domain.Interaction;
//import com.example.crm.domain.InteractionType;
//import com.example.crm.service.CustomerService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/interactions")
//public class InteractionController {
//
//    private final CustomerService customerService;
//
//    public InteractionController(CustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//    @PostMapping
//    public Interaction create(@RequestBody InteractionRequest req) {
//        Interaction interaction = new Interaction();
//        interaction.setType(req.getType());
//        interaction.setNotes(req.getNotes());
//        interaction.setOwner(req.getOwner());
//        return customerService.addInteraction(req.getCustomerId(), interaction);
//    }
//
//    // DTO for request body
//    public static class InteractionRequest {
//        private Long customerId;
//        private InteractionType type;
//        private String notes;
//        private String owner;
//
//        public Long getCustomerId() { return customerId; }
//        public void setCustomerId(Long customerId) { this.customerId = customerId; }
//
//        public InteractionType getType() { return type; }
//        public void setType(InteractionType type) { this.type = type; }
//
//        public String getNotes() { return notes; }
//        public void setNotes(String notes) { this.notes = notes; }
//
//        public String getOwner() { return owner; }
//        public void setOwner(String owner) { this.owner = owner; }
//    }
//}
