package com.takehome.stayease.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import java.util.List;
import com.takehome.stayease.dto.HotelCreationDto;
import com.takehome.stayease.dto.HotelDto;
import com.takehome.stayease.dto.UpdateHotelDto;
import com.takehome.stayease.repositpryService.HotelRepositoryService;
import com.takehome.stayease.services.HotelService;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepositoryService hotelRepositoryService;

    
     @Override
    public HotelDto createHotel(HotelCreationDto hotelCreationDto) {
        // First, perform business logic checks
        hotelRepositoryService.checkIfHotelExists(hotelCreationDto.getName());
        
        // Then, delegate the creation to the repository service
        return hotelRepositoryService.createHotel(
                hotelCreationDto.getName(),
                hotelCreationDto.getLocation(),
                hotelCreationDto.getDescription(),
                hotelCreationDto.getAvailableRooms()
        );
    }
    @Override
    public HotelDto updateHotel(Long hotelId, UpdateHotelDto updateHotelDto) {
        return hotelRepositoryService.updateHotel(hotelId, updateHotelDto);
    }

    @Override
    public void deleteHotel(Long hotelId) { 
        hotelRepositoryService.deleteHotelById(hotelId); // <-- Call the correct method name
    }
    
    @Override
    public List<HotelDto> getAllHotels() {
        // Now it correctly delegates to the repository service
        return hotelRepositoryService.getAllHotels();
    }
    
}