package com.example.spring_boot_api.dto.book;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private Integer page;
    private Integer totalPages;
    private Integer totalItems;
    private List<BookDto> books;
}