package com.takehome.stayease.repositpryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import com.takehome.stayease.dto.BookingDto;
import com.takehome.stayease.exceptions.ResourceNotFoundException;
import com.takehome.stayease.model.Booking;
import com.takehome.stayease.model.Hotel;
import com.takehome.stayease.model.User;
import com.takehome.stayease.repository.BookingRepository;
import com.takehome.stayease.repository.HotelRepository;
import com.takehome.stayease.utils.Mapper;

@Service
@RequiredArgsConstructor
public class BookingRepositoryServiceImpl implements BookingRepositoryService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository; // Needed to update room count

    @Override
    @Transactional
    public BookingDto saveBooking(User user, Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate) {
        // Handle database state changes
        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
        hotelRepository.save(hotel);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setHotel(hotel);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);

        Booking savedBooking = bookingRepository.save(booking);
        return Mapper.mapToBookingDto(savedBooking);
    }

    @Override
    public BookingDto findBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", bookingId));
        return Mapper.mapToBookingDto(booking);
    }

    @Override
    @Transactional
    public void deleteBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", bookingId));

        // Handle database state changes
        Hotel hotel = booking.getHotel();
        hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);
        hotelRepository.save(hotel);

        bookingRepository.delete(booking);
    }

}