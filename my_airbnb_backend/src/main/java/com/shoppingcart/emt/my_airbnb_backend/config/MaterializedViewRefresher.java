package com.shoppingcart.emt.my_airbnb_backend.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MaterializedViewRefresher {

    @PersistenceContext
    private EntityManager entityManager;

    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void refreshViewsAfterStartup(ContextRefreshedEvent event) {
        entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW accommodations_per_host").executeUpdate();
        entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW hosts_per_country").executeUpdate();

        System.out.println("✅ Materialized view refreshed after startup");
    }
}
