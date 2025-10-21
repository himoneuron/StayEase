package com.takehome.stayease.dto;

import lombok.Data;
import java.time.LocalDate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

@Data
public class BookingRequestDto {
    @NotNull
    @Future
    private LocalDate checkInDate;

    @NotNull
    @Future
    private LocalDate checkOutDate;
}
