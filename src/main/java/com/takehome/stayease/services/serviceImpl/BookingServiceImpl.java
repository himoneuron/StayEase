package com.takehome.stayease.services.serviceImpl;


import com.takehome.stayease.dto.BookingDto;
import com.takehome.stayease.dto.BookingRequestDto;
import com.takehome.stayease.exceptions.BadRequestException;
import com.takehome.stayease.exceptions.ResourceNotFoundException;
import com.takehome.stayease.model.Hotel;
import com.takehome.stayease.model.User;

import com.takehome.stayease.repository.HotelRepository;
import com.takehome.stayease.repositpryService.BookingRepositoryService;
import com.takehome.stayease.services.BookingService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepositoryService bookingRepositoryService;
    private final HotelRepository hotelRepository;
    @Override
    public BookingDto createBooking(Long hotelId, BookingRequestDto bookingRequestDto) {
        // 1. Business logic validation
        if (bookingRequestDto.getCheckOutDate().isBefore(bookingRequestDto.getCheckInDate())) {
            throw new BadRequestException("Check-out date must be after check-in date.");
        }

        Hotel hotel = hotelRepository.findById(hotelId)
               .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", hotelId));

        if (hotel.getAvailableRooms() < 1) {
            // FIX: Throw ResourceNotFoundException to produce a 404 status as per the test
            throw new ResourceNotFoundException("No rooms available at this hotel.", "id", hotelId);
        }

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 2. Delegate to the repository service
        return bookingRepositoryService.saveBooking(
                currentUser,
                hotel,
                bookingRequestDto.getCheckInDate(),
                bookingRequestDto.getCheckOutDate()
        );
    }

    
    @Override
    public BookingDto getBookingById(Long bookingId) {
        return bookingRepositoryService.findBookingById(bookingId);
    }

    
    @Override
    public void cancelBooking(Long bookingId) {
        bookingRepositoryService.deleteBookingById(bookingId);
    }
}