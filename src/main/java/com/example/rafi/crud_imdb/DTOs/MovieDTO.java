package com.example.rafi.crud_imdb.DTOs;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor // Generates a no-argument constructor
public class MovieDTO {
    private Long id;
    private String title;
    private int year;
    private double rating;

    // Remove the custom constructor as @AllArgsConstructor already handles this
}