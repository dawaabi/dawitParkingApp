package com.parkingApp.exception;

import java.time.LocalDateTime;

public record ApiError(String message, int statusCode, String path, LocalDateTime timestamp) {}
