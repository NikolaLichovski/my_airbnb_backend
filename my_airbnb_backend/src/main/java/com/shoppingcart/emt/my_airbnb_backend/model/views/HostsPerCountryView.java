package com.shoppingcart.emt.my_airbnb_backend.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * from public.hosts_per_country")
@Immutable
public class HostsPerCountryView {
    @Id
    @Column(name = "country_name")
    private String countryName;  // because group by country_name, it's unique

    @Column(name = "host_count")
    private Long hostCount;

}
