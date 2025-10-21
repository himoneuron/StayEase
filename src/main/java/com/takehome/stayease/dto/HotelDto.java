package com.takehome.stayease.dto;

import lombok.Data;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String location;
    private String description;
    private int availableRooms;
}