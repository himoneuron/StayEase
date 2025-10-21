package com.takehome.stayease.repositpryService;

import com.takehome.stayease.dto.HotelDto;
import com.takehome.stayease.dto.UpdateHotelDto;
import com.takehome.stayease.exceptions.ResourceNotFoundException;
import com.takehome.stayease.model.Hotel;
import com.takehome.stayease.repository.HotelRepository;
import com.takehome.stayease.utils.Mapper;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelRepositoryServiceImpl implements HotelRepositoryService {
    private final HotelRepository hotelRepository;
    @Override
    public HotelDto createHotel(String hotelName, String location, String description, int availableRooms) {
        Hotel hotel = Hotel.builder()
                .name(hotelName)
                .location(location)
                .description(description)
                .availableRooms(availableRooms)
                .build();

        Hotel savedHotel = hotelRepository.save(hotel);
        return Mapper.mapToHotelDto(savedHotel);
    }

    @Override
    public void checkIfHotelExists(String hotelName) {
        hotelRepository.findByName(hotelName).ifPresent(h -> {
            throw new IllegalArgumentException("Hotel with name '" + hotelName + "' already exists.");
        });
    }
    @Override
    public void deleteHotelById(Long hotelId) { 
        if (!hotelRepository.existsById(hotelId)) {
            throw new ResourceNotFoundException("Hotel", "id", hotelId);
        }
        hotelRepository.deleteById(hotelId);
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(Mapper::mapToHotelDto)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDto updateHotel(Long hotelId, UpdateHotelDto updateHotelDto) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", hotelId));

        // Update fields if they are not null in the request
        if (updateHotelDto.getName() != null) {
            hotel.setName(updateHotelDto.getName());
        }
        if (updateHotelDto.getLocation() != null) {
            hotel.setLocation(updateHotelDto.getLocation());
        }
        if (updateHotelDto.getDescription() != null) {
            hotel.setDescription(updateHotelDto.getDescription());
        }
        if (updateHotelDto.getAvailableRooms() != null) {
            hotel.setAvailableRooms(updateHotelDto.getAvailableRooms());
        }

        Hotel updatedHotel = hotelRepository.save(hotel);
        return Mapper.mapToHotelDto(updatedHotel);
    }
}
