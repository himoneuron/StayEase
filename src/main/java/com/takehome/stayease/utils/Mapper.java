package com.takehome.stayease.utils;

import com.takehome.stayease.dto.BookingDto;
import com.takehome.stayease.dto.HotelCreationDto;
import com.takehome.stayease.dto.HotelDto;
import com.takehome.stayease.dto.UserDto;
import com.takehome.stayease.model.Booking;
import com.takehome.stayease.model.Hotel;
import com.takehome.stayease.model.User;

public class Mapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getRole()
        );
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        return user;
    }

    public static HotelDto mapToHotelDto(Hotel hotel) {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());
        hotelDto.setLocation(hotel.getLocation());
        hotelDto.setDescription(hotel.getDescription());
        hotelDto.setAvailableRooms(hotel.getAvailableRooms());
        return hotelDto;
    }

    /**
     * Maps a HotelCreationDto to a Hotel entity.
     */
    public static Hotel mapToHotel(HotelCreationDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotel.setLocation(hotelDto.getLocation());
        hotel.setDescription(hotelDto.getDescription());
        hotel.setAvailableRooms(hotelDto.getAvailableRooms());
        return hotel;
    }

    public static BookingDto mapToBookingDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setUserId(booking.getUser().getId());
        dto.setHotelId(booking.getHotel().getId());
        return dto;
    }
}