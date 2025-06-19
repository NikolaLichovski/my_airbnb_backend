package com.shoppingcart.emt.my_airbnb_backend.model.domain;

import com.shoppingcart.emt.my_airbnb_backend.model.enumerations.AccommodationCategory;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "accommodations")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccommodationCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Host host;

    private Integer numRooms;

    private boolean isRented;


    public Accommodation() {
    }

    public Accommodation(String name, AccommodationCategory category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented = false;
    }

    public Accommodation(String name, AccommodationCategory category, Host host, Integer numRooms, boolean isRented) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented = isRented;
    }

    //    Во рамки на апликацијата се чуваат следните податоци за сместувањата:
//    id (Long), name (String), category (enum), host (Host), numRooms (Integer).
}
