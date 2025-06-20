package com.shoppingcart.emt.my_airbnb_backend.web;

import com.shoppingcart.emt.my_airbnb_backend.dto.CreateHostDto;
import com.shoppingcart.emt.my_airbnb_backend.dto.DisplayHostDto;
import com.shoppingcart.emt.my_airbnb_backend.model.projections.HostNameProjection;
import com.shoppingcart.emt.my_airbnb_backend.model.views.HostsPerCountryView;
import com.shoppingcart.emt.my_airbnb_backend.service.application.impl.HostApplicationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host API", description = "Endpoints for managing hosts")
public class HostController {

    private final HostApplicationServiceImpl hostApplicationService;

    public HostController(HostApplicationServiceImpl hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @Operation(summary = "Get all hosts",description = "Retrieves a list of all available hosts.")
    @GetMapping
    public List<DisplayHostDto> findAll(){
        return hostApplicationService.findAll();
    }

    @Operation(summary = "Get host by ID", description = "Finds a host by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id){
        return hostApplicationService.findById(id).map(host -> ResponseEntity.ok().body(host)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new host", description = "Creates a new host.")
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto createHostDto){
        return hostApplicationService.save(createHostDto)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing host", description = "Updates a host by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto createHostDto){
        return hostApplicationService.update(id, createHostDto)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a host", description = "Deletes a host by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DisplayHostDto> deleteById(@PathVariable Long id){
        if (hostApplicationService.findById(id).isPresent()){
            hostApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get number of hosts per country", description = "Returns the number of hosts grouped by countries.")
    @GetMapping("/by-country")
    public List<HostsPerCountryView> getHostsPerCountry() {
        return hostApplicationService.getHostsPerCountry();
    }

    @Operation(summary = "Get all host names", description = "Returns the first and last names of all hosts.")
    @GetMapping("/names")
    public List<HostNameProjection> showAllHostNamesByProjection() {
        return hostApplicationService.showAllHostNamesByProjection();
    }


}
