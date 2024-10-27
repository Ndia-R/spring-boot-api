package com.example.spring_boot_api.dto;

import com.example.spring_boot_api.shared.DtoBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto extends DtoBase {
    private Integer id;
    private String name;
}
