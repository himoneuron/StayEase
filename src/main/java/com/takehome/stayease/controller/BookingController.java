package com.takehome.stayease.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.takehome.stayease.dto.BookingDto;
import com.takehome.stayease.dto.BookingRequestDto;
import com.takehome.stayease.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/{hotelId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<BookingDto> createBooking(@PathVariable Long hotelId, @Valid @RequestBody BookingRequestDto bookingRequestDto) {
        BookingDto createdBooking = bookingService.createBooking(hotelId, bookingRequestDto);
        // FIX: Return HttpStatus.OK (200) as expected by the test case
        return new ResponseEntity<>(createdBooking, HttpStatus.OK);
    }

    //@GetMapping("/{bookingId}")
    @GetMapping("/{bookingId}")
    @PreAuthorize("isAuthenticated()") // Any authenticated user can view their booking
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasAuthority('HOTEL_MANAGER')")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}