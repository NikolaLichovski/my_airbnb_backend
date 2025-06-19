package com.shoppingcart.emt.my_airbnb_backend.listeners;

import com.shoppingcart.emt.my_airbnb_backend.events.HostModifiedEvent;
import com.shoppingcart.emt.my_airbnb_backend.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostCreated(HostModifiedEvent event) {
        this.hostService.refreshMaterializedView();
    }

}
