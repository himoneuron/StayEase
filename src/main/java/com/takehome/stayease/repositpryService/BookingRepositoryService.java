package com.takehome.stayease.repositpryService;

import java.time.LocalDate;
import com.takehome.stayease.dto.BookingDto;
import com.takehome.stayease.model.Hotel;
import com.takehome.stayease.model.User;

public interface BookingRepositoryService {
    BookingDto saveBooking(User user, Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate);
    BookingDto findBookingById(Long bookingId);
    void deleteBookingById(Long bookingId);
}
