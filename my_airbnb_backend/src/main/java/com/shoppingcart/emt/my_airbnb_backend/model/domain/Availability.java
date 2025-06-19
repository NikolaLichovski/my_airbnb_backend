package com.shoppingcart.emt.my_airbnb_backend.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "availability")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Accommodation accommodation;

    public Availability() {
    }

    public Availability(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, Double price) {
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.price = price;
    }

    public Availability(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, Double price, Accommodation accommodation) {
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.price = price;
        this.accommodation = accommodation;
    }
}
