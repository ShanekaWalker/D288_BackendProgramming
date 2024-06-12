package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "divisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id", nullable = false)
    private Long id;

    @Column(name = "division", nullable = false)
    private String division_name;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Country_ID", insertable = false, updatable = false, nullable = false)
    private Country country;

    @Column(name = "country_id")
    private Long country_ID;

    /*@OneToMany(mappedBy = "division", cascade = CascadeType.ALL)
    private Set<Customer> customers;


    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.setDivision(this);
    }*/


}
