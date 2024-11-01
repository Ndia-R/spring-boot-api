package com.example.spring_boot_api.dto.genre;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreUpdateDto {
    @NotNull
    @Length(max = 50)
    private String name;

    @NotNull
    @Length(max = 255)
    private String description;
}