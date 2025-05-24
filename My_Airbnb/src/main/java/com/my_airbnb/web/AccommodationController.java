package com.my_airbnb.web;

import com.my_airbnb.dto.CreateAccommodationDto;
import com.my_airbnb.dto.DisplayAccommodationDto;
import com.my_airbnb.service.application.AccommodationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation API", description = "Endpoints for managing accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @Operation(summary = "Get all accommodations",description = "Retrieves a list of all available accommodations.")
    @GetMapping
    public List<DisplayAccommodationDto> findAll(){
        return accommodationApplicationService.findAll();
    }

    @Operation(summary = "Get accommodation by ID", description = "Finds an accommodation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id){
        return accommodationApplicationService.findById(id).map(accommodation -> ResponseEntity.ok().body(accommodation)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new accommodation", description = "Creates a new accommodation.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto createAccommodationDto){
        return accommodationApplicationService.save(createAccommodationDto)
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing accommodation", description = "Updates an accommodation by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto createAccommodationDto){
        return accommodationApplicationService.update(id, createAccommodationDto)
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an accommodation", description = "Deletes an accommodation by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DisplayAccommodationDto> deleteById(@PathVariable Long id){
        if (accommodationApplicationService.findById(id).isPresent()){
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Mark accommodation as rented by ID", description = "Marks an accommodation as rented by its ID.")
    @GetMapping("/{id}/mark-rented")
    public ResponseEntity<DisplayAccommodationDto> markAsRented(@PathVariable Long id){
        return accommodationApplicationService.markAsRented(id).map(accommodation -> ResponseEntity.ok().body(accommodation)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Mark accommodation as free by ID", description = "Marks an accommodation as free by its ID.")
    @GetMapping("/{id}/mark-free")
    public ResponseEntity<DisplayAccommodationDto> markAsFree(@PathVariable Long id){
        return accommodationApplicationService.markAsFree(id).map(accommodation -> ResponseEntity.ok().body(accommodation)).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
