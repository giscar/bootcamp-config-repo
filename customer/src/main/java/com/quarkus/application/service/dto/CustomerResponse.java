package com.quarkus.application.service.dto;

public record CustomerResponse(
        Long id,
        String name,
        String email
) {}
