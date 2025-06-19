package com.shoppingcart.emt.my_airbnb_backend.repository;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Host;
import com.shoppingcart.emt.my_airbnb_backend.model.projections.HostNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {

    @Query(value = "SELECT h.name AS firstName, h.surname AS lastName FROM hosts h", nativeQuery = true)
    List<HostNameProjection> findAllHostNames();

}
