package com.shoppingcart.emt.my_airbnb_backend.repository;

import com.shoppingcart.emt.my_airbnb_backend.model.views.AccommodationsPerHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccommodationsPerHostRepository extends JpaRepository<AccommodationsPerHostView, Long> {

    AccommodationsPerHostView findByHostId(long hostId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.accommodations_per_host", nativeQuery = true)
    void refreshMaterializedView();

}
