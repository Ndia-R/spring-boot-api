package com.example.spring_boot_api.dto.book;

import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String id;
    private String title;
    private String description;
    private List<Integer> genreIds;
    private List<String> authors;
    private String publisher;
    private Date publishedDate;
    private Integer price;
    private Integer pageCount;
    private String isbn;
    private String imageUrl;
}