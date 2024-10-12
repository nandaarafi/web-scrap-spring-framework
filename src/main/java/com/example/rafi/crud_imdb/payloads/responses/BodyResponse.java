package com.example.rafi.crud_imdb.payloads.responses;

public record BodyResponse<Data>(
        String status,
        int code,
        String message,
        Data data
) {}

