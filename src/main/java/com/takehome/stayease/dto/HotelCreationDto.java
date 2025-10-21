package com.takehome.stayease.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HotelCreationDto {
    @NotBlank
    private String name;
    @NotBlank
    private String location;
    @NotBlank
    private String description;
    @NotNull @Min(0)
    private Integer availableRooms;
}
