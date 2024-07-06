package com.microservice.auth.domain.dto;

import lombok.NonNull;

public record AuthRequestLogInDto(@NonNull String username,@NonNull String password) {
}
