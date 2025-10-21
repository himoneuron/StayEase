package com.takehome.stayease.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import com.takehome.stayease.dto.HotelCreationDto;
import com.takehome.stayease.dto.HotelDto;
import com.takehome.stayease.dto.UpdateHotelDto;
import com.takehome.stayease.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication; // <-- Add this import
import org.springframework.security.core.context.SecurityContextHolder; // <-- Add this import


@RestController
//@RequestMapping("/api/hotels")
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')") // This secures the endpoint
    @PreAuthorize("hasAuthority('ADMIN')") 
    public ResponseEntity<HotelDto> createHotel(@Valid @RequestBody HotelCreationDto hotelCreationDto) {

        // --- START DEBUGGING CODE ---
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("DEBUGGING - User: " + authentication.getName());
        System.out.println("DEBUGGING - Authorities: " + authentication.getAuthorities());
        // --- END DEBUGGING CODE ---



        HotelDto createdHotel = hotelService.createHotel(hotelCreationDto);
        return new ResponseEntity<>(createdHotel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        List<HotelDto> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }
    
    @PutMapping("/{hotelId}")
    //@PreAuthorize("hasRole('HOTEL_MANAGER')")
    @PreAuthorize("hasAuthority('HOTEL_MANAGER')") 
    public ResponseEntity<HotelDto> updateHotel(@PathVariable Long hotelId, @Valid @RequestBody UpdateHotelDto updateHotelDto) {
        HotelDto updatedHotel = hotelService.updateHotel(hotelId, updateHotelDto);
        return ResponseEntity.ok(updatedHotel);
    }
    
    @DeleteMapping("/{hotelId}")
    //@PreAuthorize("hasRole('ADMIN')")]
    @PreAuthorize("hasAuthority('ADMIN')") 
    public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.noContent().build();
    }
}