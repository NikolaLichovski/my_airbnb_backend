package com.my_airbnb.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "temporary_reservations")
public class TemporaryReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Accommodation accommodation;

    public TemporaryReservation() {}

    public TemporaryReservation(User user, Accommodation accommodation) {
        this.user = user;
        this.accommodation = accommodation;
    }
}
