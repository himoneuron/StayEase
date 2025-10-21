package com.takehome.stayease.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        // Set the response status to 401 Unauthorized.
        // This is the core change to satisfy the test requirements.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        // Set the content type to application/json for a structured error response.
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Create a structured error message body.
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", Instant.now().toString());
        errorDetails.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        errorDetails.put("error", "Unauthorized");
        errorDetails.put("message", "Access Denied: You do not have the necessary permissions to access this resource.");
        errorDetails.put("path", request.getRequestURI());

        // Write the JSON error response to the output stream.
        try (OutputStream out = response.getOutputStream()) {
            objectMapper.writeValue(out, errorDetails);
        }
    }
}