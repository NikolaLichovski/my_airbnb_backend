package com.shoppingcart.emt.my_airbnb_backend.events;

import com.shoppingcart.emt.my_airbnb_backend.model.domain.Host;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostModifiedEvent extends ApplicationEvent {
    private LocalDateTime when;

    public HostModifiedEvent(Host source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public HostModifiedEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
