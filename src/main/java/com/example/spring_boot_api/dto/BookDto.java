package com.example.spring_boot_api.dto;

import java.sql.Date;
import java.util.List;
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
public class BookDto extends DtoBase {
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
