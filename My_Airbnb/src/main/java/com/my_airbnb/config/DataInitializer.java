package com.my_airbnb.config;

import com.my_airbnb.model.domain.*;
import com.my_airbnb.model.enumerations.*;
import com.my_airbnb.model.enumerations.AccommodationCategory;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.my_airbnb.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final AvailabilityRepository availabilityRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(CountryRepository countryRepository,
                           AccommodationRepository accommodationRepository,
                           HostRepository hostRepository,
                           AvailabilityRepository availabilityRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.availabilityRepository = availabilityRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        // Populating Countries
        Country japan = new Country("Japan", "Asia");
        Country usa = new Country("United States", "North America");
        Country france = new Country("France", "Europe");

        countryRepository.saveAll(List.of(japan, usa, france));

        // Populating Hosts
        Host blake = hostRepository.save(new Host("Blake", "Black", japan));
        Host alice = hostRepository.save(new Host("Alice", "Johnson", japan));
        Host john = hostRepository.save(new Host("John", "Doe", usa));
        Host emma = hostRepository.save(new Host("Emma", "Stone", usa));
        Host pierre = hostRepository.save(new Host("Pierre", "Dupont", france));
        Host marie = hostRepository.save(new Host("Marie", "Lemoine", france));

        // Populating Accommodations
        Accommodation tokyoApt = accommodationRepository.save(new Accommodation("Tokyo Apartment", AccommodationCategory.APARTMENT, blake, 3, false));
        Accommodation kyotoHouse = accommodationRepository.save(new Accommodation("Kyoto Traditional House", AccommodationCategory.HOUSE, alice, 5, false));
        Accommodation nyLoft = accommodationRepository.save(new Accommodation("NY Loft", AccommodationCategory.APARTMENT, john, 2, false));
        Accommodation laVilla = accommodationRepository.save(new Accommodation("LA Villa", AccommodationCategory.HOTEL, emma, 4, false));
        Accommodation parisFlat = accommodationRepository.save(new Accommodation("Paris Flat", AccommodationCategory.APARTMENT, pierre, 3, false));
        Accommodation lyonChalet = accommodationRepository.save(new Accommodation("Lyon Chalet", AccommodationCategory.HOTEL, marie, 6, false));

        // Populating Availability (More Entries Added)
        availabilityRepository.saveAll(List.of(
                // Tokyo Apartment
                new Availability(LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(10), 100.0, tokyoApt),
                new Availability(LocalDateTime.now().plusDays(12), LocalDateTime.now().plusDays(18), 110.0, tokyoApt),
                new Availability(LocalDateTime.now().plusDays(20), LocalDateTime.now().plusDays(25), 105.0, tokyoApt),

                // Kyoto Traditional House
                new Availability(LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(7), 120.0, kyotoHouse),
                new Availability(LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(15), 130.0, kyotoHouse),
                new Availability(LocalDateTime.now().plusDays(18), LocalDateTime.now().plusDays(22), 115.0, kyotoHouse),

                // NY Loft
                new Availability(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(8), 200.0, nyLoft),
                new Availability(LocalDateTime.now().plusDays(9), LocalDateTime.now().plusDays(14), 190.0, nyLoft),
                new Availability(LocalDateTime.now().plusDays(16), LocalDateTime.now().plusDays(21), 195.0, nyLoft),

                // LA Villa
                new Availability(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(6), 250.0, laVilla),
                new Availability(LocalDateTime.now().plusDays(8), LocalDateTime.now().plusDays(13), 240.0, laVilla),
                new Availability(LocalDateTime.now().plusDays(15), LocalDateTime.now().plusDays(20), 260.0, laVilla),

                // Paris Flat
                new Availability(LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(9), 150.0, parisFlat),
                new Availability(LocalDateTime.now().plusDays(11), LocalDateTime.now().plusDays(17), 140.0, parisFlat),
                new Availability(LocalDateTime.now().plusDays(19), LocalDateTime.now().plusDays(24), 155.0, parisFlat),

                // Lyon Chalet
                new Availability(LocalDateTime.now().plusDays(6), LocalDateTime.now().plusDays(12), 180.0, lyonChalet),
                new Availability(LocalDateTime.now().plusDays(14), LocalDateTime.now().plusDays(19), 175.0, lyonChalet),
                new Availability(LocalDateTime.now().plusDays(21), LocalDateTime.now().plusDays(26), 185.0, lyonChalet)
        ));

        userRepository.save(new User(
                "nl",
                passwordEncoder.encode("nl"),
                "Nikola",
                "Lichovski",
                Role.ROLE_ADMIN
        ));

        userRepository.save(new User(
                "host1",
                passwordEncoder.encode("host1"),
                "host1Name",
                "host1Surname",
                Role.ROLE_HOST
        ));

        userRepository.save(new User(
                "user1",
                passwordEncoder.encode("user1"),
                "user1Name",
                "user1Surname",
                Role.ROLE_USER
        ));

    }
}
