package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long id;

    @JsonProperty("firstName")
    @Column(name = "customer_first_name", nullable = false)
    private String firstName;

    @JsonProperty("lastName")
    @Column(name = "customer_last_name", nullable = false)
    private String lastName;

    @JsonProperty("address")
    @Column(name = "address", nullable = false)
    private String address;

    @JsonProperty("postal_code")
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @JsonProperty("phone")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id", nullable = false)
    private Division division;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cart> carts = new HashSet<>();


    public Customer() {}


    public Customer(String firstName, String lastName, String address, String postalCode, String phone, Division division) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
        this.createDate = new Date();
        this.lastUpdate = new Date();
    }



    public void addCart(Cart cart) {
        carts.add(cart);
        cart.setCustomer(this);
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public void add(Cart cart) {
    }
}
