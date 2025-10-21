package com.takehome.stayease.services;

import com.takehome.stayease.dto.BookingDto;
import com.takehome.stayease.dto.BookingRequestDto;


public interface BookingService {

    BookingDto createBooking(Long hotelId, BookingRequestDto bookingRequestDto);
    BookingDto getBookingById(Long bookingId);
    void cancelBooking(Long bookingId);
}