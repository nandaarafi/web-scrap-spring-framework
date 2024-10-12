package com.example.rafi.crud_imdb.payloads.responses;

public record ExceptionResponse(
        String path,
        String ctx
) {}