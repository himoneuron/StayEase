package com.takehome.stayease.dto;

import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class BookingDto {
    @JsonProperty("bookingId")
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Long userId;
    private Long hotelId;
}