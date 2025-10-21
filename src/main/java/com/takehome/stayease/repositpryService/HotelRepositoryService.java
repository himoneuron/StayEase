package com.takehome.stayease.repositpryService;

import java.util.List;
import com.takehome.stayease.dto.HotelDto;
import com.takehome.stayease.dto.UpdateHotelDto;

public interface HotelRepositoryService {
    HotelDto createHotel(String hotelName, String location, String description, int availableRooms);
    void checkIfHotelExists(String hotelName);
    List<HotelDto> getAllHotels(); 
    HotelDto updateHotel(Long hotelId, UpdateHotelDto updateHotelDto);
    void deleteHotelById(Long hotelId); 
}
