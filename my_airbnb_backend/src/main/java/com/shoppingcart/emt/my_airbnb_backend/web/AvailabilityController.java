package com.shoppingcart.emt.my_airbnb_backend.web;

import com.shoppingcart.emt.my_airbnb_backend.dto.CreateAvailabilityDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayAvailabilityDto;
import com.shoppingcart.emt.my_airbnb_backend.service.application.AvailabilityApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@Tag(name = "Availability API", description = "Endpoints for managing availability periods")
public class AvailabilityController {
    private final AvailabilityApplicationService availabilityApplicationService;

    public AvailabilityController(AvailabilityApplicationService availabilityApplicationService) {
        this.availabilityApplicationService = availabilityApplicationService;
    }

    @Operation(summary = "Get all availability periods", description = "Retrieves a list of all availability periods.")
    @GetMapping
    public List<DisplayAvailabilityDto> findAll() {
        return availabilityApplicationService.findAll();
    }

    @Operation(summary = "Get availability by ID", description = "Finds an availability period by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAvailabilityDto> findById(@PathVariable Long id) {
        return availabilityApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new availability period", description = "Creates a new availability period for an accommodation.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAvailabilityDto> save(@RequestBody CreateAvailabilityDto createAvailabilityDto) {
        return availabilityApplicationService.save(createAvailabilityDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing availability period", description = "Updates an availability period by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAvailabilityDto> update(@PathVariable Long id, @RequestBody CreateAvailabilityDto createAvailabilityDto) {
        return availabilityApplicationService.update(id, createAvailabilityDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an availability period", description = "Deletes an availability period by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (availabilityApplicationService.findById(id).isPresent()) {
            availabilityApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/accommodation/{accommodationId}")
    public ResponseEntity<List<DisplayAvailabilityDto>> findByAccommodationId(@PathVariable Long accommodationId) {
        List<DisplayAvailabilityDto> availabilities = availabilityApplicationService.findByAccommodationId(accommodationId);
        return ResponseEntity.ok(availabilities);
    }
}
