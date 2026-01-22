package com.company.application.dto;

public record CustomerResponse(
        Long id,
        String fullName,
        String email
) {}
