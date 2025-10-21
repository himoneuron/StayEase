package com.takehome.stayease.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/*
 * This DTO is used as the output after a successful registration or login.
 *  It provides the JWT token back to the client.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private String token;
}
