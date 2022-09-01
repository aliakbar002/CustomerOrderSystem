package com.test.customerorder.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "customer")
    private String customer;

    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>();


















//    @OneToOne(mappedBy = "customer",cascade = {CascadeType.ALL})
//    private Order order;
//
//       @OneToMany(cascade = CascadeType.ALL, mappedBy = "order",orphanRemoval = true)
//   //@JsonManagedReference
//   private List<Order> order = new ArrayList<>();
}
