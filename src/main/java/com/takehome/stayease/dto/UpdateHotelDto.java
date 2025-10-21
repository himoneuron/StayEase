package com.takehome.stayease.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;
@Data
public class UpdateHotelDto {
    // Fields are optional for an update
    private String name;
    private String location;
    private String description;
    
    @Min(value = 0, message = "Available rooms cannot be negative")
    private Integer availableRooms;
}
