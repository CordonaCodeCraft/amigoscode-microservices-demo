package com.amigoscode.notification.dto;

public record NotificationRequest(Integer toCustomerId, String toCustomerEmail, String message) {}
