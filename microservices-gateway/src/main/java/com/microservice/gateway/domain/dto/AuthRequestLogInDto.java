package com.microservice.gateway.domain.dto;

import lombok.NonNull;

public record AuthRequestLogInDto(@NonNull String username,@NonNull String password) {
}
