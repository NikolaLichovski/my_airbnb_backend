package com.shoppingcart.emt.my_airbnb_backend.jobs;

import com.shoppingcart.emt.my_airbnb_backend.service.domain.AccommodationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final AccommodationService accommodationService;

    public ScheduledTasks(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Scheduled(cron = "0 0 3 * * *")  // Runs daily at 3 AM
    public void refreshMaterializedView() {
         accommodationService.refreshMaterializedView();
    }
}
