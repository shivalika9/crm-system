package com.example.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interactions")
public class Interaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; 
    private String notes;
    private String owner;

    private LocalDateTime happenedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // 👈 Prevents ByteBuddy proxy issue
    private Customer customer;

    @PrePersist
    public void prePersist() {
        if (happenedAt == null) {
            happenedAt = LocalDateTime.now();
        }
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public LocalDateTime getHappenedAt() { return happenedAt; }
    public void setHappenedAt(LocalDateTime happenedAt) { this.happenedAt = happenedAt; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}





//package com.example.crm.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "interactions")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//public class Interaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String type;
//    private String notes;
//    private String owner;
//
//    @Column(name = "happened_at", nullable = false)
//    private LocalDateTime happenedAt; // <-- NEW
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//
//    @PrePersist
//    public void prePersist() {
//        if (happenedAt == null) {
//            happenedAt = LocalDateTime.now();
//        }
//    }
//
//    // getters & setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getType() { return type; }
//    public void setType(String type) { this.type = type; }
//
//    public String getNotes() { return notes; }
//    public void setNotes(String notes) { this.notes = notes; }
//
//    public String getOwner() { return owner; }
//    public void setOwner(String owner) { this.owner = owner; }
//
//    public LocalDateTime getHappenedAt() { return happenedAt; }
//    public void setHappenedAt(LocalDateTime happenedAt) { this.happenedAt = happenedAt; }
//
//    public Customer getCustomer() { return customer; }
//    public void setCustomer(Customer customer) { this.customer = customer; }
//}


//------------------------------------------------------------------------------------------------------
//post worked aove



//package com.example.crm.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "interactions")
//public class Interaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String type; // Keeping it as String to avoid enum hassle
//    private String notes;
//    private String owner;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private Customer customer;
//
//    // Getters & Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getType() { return type; }
//    public void setType(String type) { this.type = type; }
//
//    public String getNotes() { return notes; }
//    public void setNotes(String notes) { this.notes = notes; }
//
//    public String getOwner() { return owner; }
//    public void setOwner(String owner) { this.owner = owner; }
//
//    public Customer getCustomer() { return customer; }
//    public void setCustomer(Customer customer) { this.customer = customer; }
//}







//package com.example.crm.domain;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "interactions")
//public class Interaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String type; // Keeping it as String to avoid enum hassle
//    private String notes;
//    private String owner;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//
//    // Getters & Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getType() { return type; }
//    public void setType(String type) { this.type = type; }
//
//    public String getNotes() { return notes; }
//    public void setNotes(String notes) { this.notes = notes; }
//
//    public String getOwner() { return owner; }
//    public void setOwner(String owner) { this.owner = owner; }
//
//    public Customer getCustomer() { return customer; }
//    public void setCustomer(Customer customer) { this.customer = customer; }
//}


//-----------------------------------------------------------------------------------------------



//package com.example.crm.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//
//@Entity
//public class Interaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    private InteractionType type;
//
//    @NotBlank
//    private String notes;
//
//    @NotBlank
//    private String owner;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ✅ Fix for ByteBuddyInterceptor error
//    private Customer customer;
//
//    // ===== Constructors =====
//    public Interaction() {
//    }
//
//    public Interaction(InteractionType type, String notes, String owner, Customer customer) {
//        this.type = type;
//        this.notes = notes;
//        this.owner = owner;
//        this.customer = customer;
//    }
//
//    // ===== Getters & Setters =====
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public InteractionType getType() {
//        return type;
//    }
//
//    public void setType(InteractionType type) {
//        this.type = type;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//    public String getOwner() {
//        return owner;
//    }
//
//    public void setOwner(String owner) {
//        this.owner = owner;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//}







//package com.example.crm.domain;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import java.time.Instant;
//
//@Entity
//@Table(name = "interactions")
//public class Interaction {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private Customer customer;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private InteractionType type;
//
//    @NotBlank @Column(length = 2000)
//    private String notes;
//
//    @Column(nullable = false)
//    private Instant happenedAt = Instant.now();
//
//    @NotBlank
//    private String owner;
//
//    // Getters & Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public Customer getCustomer() { return customer; }
//    public void setCustomer(Customer customer) { this.customer = customer; }
//
//    public InteractionType getType() { return type; }
//    public void setType(InteractionType type) { this.type = type; }
//
//    public String getNotes() { return notes; }
//    public void setNotes(String notes) { this.notes = notes; }
//
//    public Instant getHappenedAt() { return happenedAt; }
//    public void setHappenedAt(Instant happenedAt) { this.happenedAt = happenedAt; }
//
//    public String getOwner() { return owner; }
//    public void setOwner(String owner) { this.owner = owner; }
//}
