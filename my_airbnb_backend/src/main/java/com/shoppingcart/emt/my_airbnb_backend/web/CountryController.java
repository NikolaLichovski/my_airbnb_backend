package com.shoppingcart.emt.my_airbnb_backend.web;

import com.shoppingcart.emt.my_airbnb_backend.dto.CreateCountryDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayCountryDto;
import com.shoppingcart.emt.my_airbnb_backend.service.application.CountryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "Endpoints for managing countries")
public class CountryController {

    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(
            summary = "Get all countries",
            description = "Retrieves a list of all available countries."
    )
    @GetMapping
    public List<DisplayCountryDto> findAll() {
        return countryApplicationService.findAll();
    }

    @Operation(summary = "Get country by ID", description = "Finds a country by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new country", description = "Creates a new country.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto createCountryDto){
        return countryApplicationService.save(createCountryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing country", description = "Updates a country by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto createCountryDto){
        return countryApplicationService.update(id, createCountryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a country", description = "Deletes a country by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DisplayCountryDto> deleteById(@PathVariable Long id){
        if (countryApplicationService.findById(id).isPresent()){
            countryApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
