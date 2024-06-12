package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "vacations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vacation {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id", nullable = false)
    private Long id;

    @JsonProperty("vacation_title")
    @Column(name = "vacation_title", nullable = false)
    private String vacationTitle;

    @JsonProperty("description")
    @Column(name = "description", nullable = false)
    private String description;

    @JsonProperty("travel_price")
    @Column(name = "travel_fare_price", nullable = false)
    private BigDecimal travelPrice;

    @JsonProperty("image_URL")
    @Column(name = "image_url", nullable = false)
    private String imageURL;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Excursion> excursions;


}
