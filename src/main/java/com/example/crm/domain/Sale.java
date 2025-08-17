package com.example.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;
    private Double amount;
    private String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ✅ Fix for ByteBuddy proxy
    private Customer customer;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}




//package com.example.crm.domain;

//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "sales")
//public class Sale {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String product;
//    private Double amount;
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
//    public String getProduct() { return product; }
//    public void setProduct(String product) { this.product = product; }
//
//    public Double getAmount() { return amount; }
//    public void setAmount(Double amount) { this.amount = amount; }
//
//    public String getOwner() { return owner; }
//    public void setOwner(String owner) { this.owner = owner; }
//
//    public Customer getCustomer() { return customer; }
//    public void setCustomer(Customer customer) { this.customer = customer; }
//}


//---------------------------------------------------------------------------------------


//package com.example.crm.domain;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "sales")
//public class Sale {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private Customer customer;
//
//    @NotBlank private String product;
//    @Positive @NotNull private BigDecimal amount;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private SaleStatus status = SaleStatus.OPEN;
//
//    private LocalDate closedAt;
//
//    // Getters & Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public Customer getCustomer() { return customer; }
//    public void setCustomer(Customer customer) { this.customer = customer; }
//
//    public String getProduct() { return product; }
//    public void setProduct(String product) { this.product = product; }
//
//    public BigDecimal getAmount() { return amount; }
//    public void setAmount(BigDecimal amount) { this.amount = amount; }
//
//    public SaleStatus getStatus() { return status; }
//    public void setStatus(SaleStatus status) { this.status = status; }
//
//    public LocalDate getClosedAt() { return closedAt; }
//    public void setClosedAt(LocalDate closedAt) { this.closedAt = closedAt; }
//}
