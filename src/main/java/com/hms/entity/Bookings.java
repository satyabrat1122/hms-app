package com.hms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String emailId;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    @Column(name = "total_gst", nullable = false)
    private Double totalGST;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

}