package com.my_airbnb.repository;

import com.my_airbnb.model.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query("SELECT a.category, COUNT(a) FROM Accommodation a GROUP BY a.category")
    List<Object[]> countAccommodationsByCategory();
}
