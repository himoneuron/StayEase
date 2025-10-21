package com.takehome.stayease.services;

import java.util.List;
import com.takehome.stayease.dto.HotelCreationDto;
import com.takehome.stayease.dto.HotelDto;
import com.takehome.stayease.dto.UpdateHotelDto;


public interface HotelService {
     HotelDto createHotel(HotelCreationDto createHotelRequest);
     List<HotelDto> getAllHotels();
     HotelDto updateHotel(Long hotelId, UpdateHotelDto updateHotelDto);
     void deleteHotel(Long hotelId);
}