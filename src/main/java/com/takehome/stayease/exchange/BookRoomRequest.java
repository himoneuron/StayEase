package com.takehome.stayease.exchange;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookRoomRequest {
    @NotNull(message = "Check-in date is required.")
    @Future(message = "Check-in date must be in the future.")
    private LocalDate checkInDate;

    @NotNull(message = "Check-out date is required.")
    @Future(message = "Check-out date must be in the future.")
    private LocalDate checkOutDate;
}