package com.my_airbnb.web;

import com.my_airbnb.dto.DisplayTemporaryReservationDto;
import com.my_airbnb.service.application.TemporaryReservationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/temp-reservations")
@RequiredArgsConstructor
@Tag(name = "Temporary Reservation API", description = "Endpoints for managing temporary accommodation reservations")
public class TemporaryReservationController {

    private final TemporaryReservationApplicationService temporaryReservationApplicationService;

    @Operation(
            summary = "Add accommodation to temporary reservation list",
            description = "Adds an accommodation to the currently authenticated user's temporary reservation list"
    )
    @PostMapping("/add/{accommodationId}")
    public ResponseEntity<String> addAccommodationToTempList(@PathVariable Long accommodationId, Authentication auth) {
        String username = auth.getName();
        try {
            return this.temporaryReservationApplicationService.addAccommodationToTempList(username, accommodationId)
                    .map(tempRes -> ResponseEntity.ok().body("Accommodation added successfully"))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred: " + e.getMessage());
        }
    }


    @Operation(
            summary = "Remove accommodation from temporary reservation list",
            description = "Removes the accommodation with the given ID from the currently authenticated user's temporary reservation list"
    )
    @DeleteMapping("/remove/{accommodationId}")
    public ResponseEntity<DisplayTemporaryReservationDto> removeFromTempList(@PathVariable Long accommodationId, Authentication auth) {
        String username = auth.getName();
        List<DisplayTemporaryReservationDto> list = temporaryReservationApplicationService.getUserTempList(username);

        Optional<DisplayTemporaryReservationDto> match = list.stream()
                .filter(res -> res.accommodationId().equals(accommodationId))
                .findFirst();

        if (match.isPresent()) {
            temporaryReservationApplicationService.removeAccommodationFromTempList(username, accommodationId);
            return ResponseEntity.ok(match.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Get all temporary reservations",
            description = "Retrieves all accommodations in the currently authenticated user's temporary reservation list"
    )
    @GetMapping
    public ResponseEntity<List<DisplayTemporaryReservationDto>> getUserTempList(Authentication auth) {
        String username = auth.getName();
        List<DisplayTemporaryReservationDto> list = temporaryReservationApplicationService.getUserTempList(username);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Confirm all temporary reservations",
            description = "Confirms all accommodations in the currently authenticated user's temporary reservation list"
    )
    @PostMapping("/confirm")
    public ResponseEntity<List<DisplayTemporaryReservationDto>> confirmAll(Authentication auth) {
        String username = auth.getName();
        return temporaryReservationApplicationService.confirmAllReservations(username)
                .map(tempResList -> ResponseEntity.ok().body(tempResList))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
