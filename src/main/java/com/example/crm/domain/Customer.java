package com.example.crm.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

@Entity
@Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Customer {
	
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

     @NotBlank private String name;
     @Email @NotBlank private String email;
     @NotBlank private String phone;

     @Enumerated(EnumType.STRING)
     @Column(nullable = false)
     private CustomerSegment segment = CustomerSegment.STANDARD;

     @Column(nullable = false, updatable = false)
     private Instant createdAt = Instant.now();

     // Getters & Setters
     public Long getId() { return id; }
     public void setId(Long id) { this.id = id; }

     public String getName() { return name; }
     public void setName(String name) { this.name = name; }

     public String getEmail() { return email; }
     public void setEmail(String email) { this.email = email; }

     public String getPhone() { return phone; }
     public void setPhone(String phone) { this.phone = phone; }

     public CustomerSegment getSegment() { return segment; }
     public void setSegment(CustomerSegment segment) { this.segment = segment; }

     public Instant getCreatedAt() { return createdAt; }
     public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }   
	


}
